import { Component, Input, OnInit, inject } from '@angular/core';
import { Articulo } from '../types/Articulo';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Categoria } from '../types/Categoria';
import { CategoriasService } from '../services/categorias.service';
import { getImageUrl, uploadImage } from '../lib/cloudinary';
import { ArticulosService } from '../services/articulos.service';
import { ActivatedRoute } from '@angular/router';
import { ArticuloMapper } from '../mappers/articulo.mapper';

@Component({
  selector: 'app-edicion',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  template: `
    <main>
      <h1>Modificar articulo</h1>
      <p>Modifica los campos que necesites</p>
      <hr />
      <form [formGroup]="articleForm" (ngSubmit)="onSubmit()">
        <div class="image">
          <div>
            <label for="imagen">Imagen</label>
            <input
              formControlName="imagen"
              type="file"
              id="imagen"
              accept="image/*"
              (change)="onChange($event)"
            />
          </div>
          <img [src]="showImageUrl" alt="imagen usuario" />
        </div>
        <div>
          <label for="nombre">Nombre</label>
          <input
            [placeholder]="articulo.nombre"
            formControlName="nombre"
            type="text"
            id="nombre"
            name="nombre"
          />
          @if(hasError) {
          <small>{{ getNombreErrorMessage() }}</small>
          }
        </div>
        <div>
          <label for="descripcion">Descripcion</label>
          <textarea
            [placeholder]="articulo.descripcion"
            formControlName="descripcion"
            id="descripcion"
            name="descripcion"
          ></textarea>
          @if(hasError) {
          <small>{{ getDescripcionErrorMessage() }}</small>
          }
        </div>
        <div>
          <label for="nombre">Precio</label>
          <input
            [placeholder]="articulo.precio"
            formControlName="precio"
            type="float"
            id="precio"
            name="precio"
          />
          @if(hasError) {
          <small>{{ getPrecioErrorMessage() }}</small>
          }
        </div>
        <div>
          <label for="categoria">Categoria</label>
          <select formControlName="categoria" id="categoria" name="categoria">
            <option disabled>Selecciona una categoria</option>
            @for (categoria of categorias; track $index) {
            <option [value]="categoria.id">{{ categoria.nombre }}</option>
            }
          </select>
        </div>
        <div>
          <button class="secondary">Cancelar</button>
          <button class="primary">Registrar</button>
        </div>
      </form>
    </main>
  `,
  styles: `
  main {
      max-width: 600px;
    }
    p {
        margin-bottom: 2rem;
    }
    form {
        margin-top: 2rem;
    }
    `,
})
export class EdicionPage implements OnInit {
  categorias: Categoria[] = [];
  _categoriaService: CategoriasService = inject(CategoriasService);
  articulo: Articulo = {} as Articulo;
  _articuloService: ArticulosService = inject(ArticulosService);
  _articuloMapper: ArticuloMapper = inject(ArticuloMapper);
  _activatedRoute: ActivatedRoute = inject(ActivatedRoute);
  async ngOnInit() {
    // Obtiene las categorias
    await this._categoriaService.getAll().then((categorias) => {
      this.categorias = categorias;
    });

    // Obtiene el articulo
    const id = this._activatedRoute.snapshot.params['id'];
    await this._articuloService.getArticuloById(id).then((articulo) => {
      this.articulo = this._articuloMapper.mapArticle(articulo[0]);
      this.showImageUrl = this.articulo.img;
      this.articulo.img = articulo[0].imagen;
      console.log(this.articulo);
    });
  }

  showImageUrl = '';
  onChange(event: any) {
    const file = event.target.files[0];

    uploadImage(file).then((response: any) => {
      this.showImageUrl = getImageUrl(response, 200);
      this.articleForm.controls.imagen.setValue(response);
    });
  }

  hasError = false;

  articleForm = new FormGroup({
    nombre: new FormControl('', [
      Validators.minLength(3),
      Validators.maxLength(50),
    ]),
    descripcion: new FormControl('', [
      Validators.minLength(3),
      Validators.maxLength(200),
    ]),
    precio: new FormControl(0, [
      Validators.min(0),
      Validators.max(1000000),
      Validators.pattern(/^\d+(\.\d{1,2})?$/),
    ]),
    imagen: new FormControl('', [Validators.required]),
    categoria: new FormControl('Selecciona una categoria'),
  });

  onSubmit() {
    // Comprueba los campos que se han modificado
    const formValue = this.articleForm.value;

    this.hasError = false;

    if (formValue.nombre === '') {
      formValue.nombre = this.articulo.nombre;
    } else if (
      this.articleForm.controls.nombre.hasError('minlength') ||
      this.articleForm.controls.nombre.hasError('maxlength')
    ) {
      this.hasError = true;
      return;
    }

    if (formValue.descripcion === '') {
      formValue.descripcion = this.articulo.descripcion;
    } else if (
      this.articleForm.controls.descripcion.hasError('minlength') ||
      this.articleForm.controls.descripcion.hasError('maxlength')
    ) {
      this.hasError = true;
      return;
    }

    if (formValue.precio === 0) {
      formValue.precio = this.articulo.precio;
    } else if (
      this.articleForm.controls.precio.hasError('min') ||
      this.articleForm.controls.precio.hasError('max') ||
      this.articleForm.controls.precio.hasError('pattern')
    ) {
      this.hasError = true;
      return;
    }

    if (formValue.imagen === '') {
      formValue.imagen = this.articulo.img;
    }

    if (formValue.categoria === 'Selecciona una categoria') {
      formValue.categoria = this.articulo.categoria.toString();
    }

    this._articuloService
      .updateArticulo(
        this.articulo.id,
        formValue.nombre || '',
        formValue.descripcion || '',
        formValue.precio || 0,
        formValue.imagen || '',
        formValue.categoria || ''
      )
      .then(() => {
        console.log('Articulo modificado');

        // TODO Swal
      });
  }

  getNombreErrorMessage() {
    return this.articleForm.controls.nombre.hasError('minlength')
      ? 'El nombre debe tener al menos 3 caracteres'
      : this.articleForm.controls.nombre.hasError('maxlength')
      ? 'El nombre debe tener menos de 50 caracteres'
      : '';
  }

  getPrecioErrorMessage() {
    if (this.articleForm.controls.precio.hasError('pattern')) {
      return 'Introduce un precio valido';
    }

    return this.articleForm.controls.precio.hasError('min')
      ? 'El precio debe ser mayor a 0'
      : this.articleForm.controls.precio.hasError('max')
      ? 'El precio debe ser menor a 1000000'
      : '';
  }

  getDescripcionErrorMessage() {
    return this.articleForm.controls.descripcion.hasError('minlength')
      ? 'La descripcion debe tener al menos 3 caracteres'
      : this.articleForm.controls.descripcion.hasError('maxlength')
      ? 'La descripcion debe tener menos de 200 caracteres'
      : '';
  }
}
