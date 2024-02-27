import { Component, OnInit, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { getImageUrl, uploadImage } from '../lib/cloudinary';
import { CategoriasService } from '../services/categorias.service';
import { Categoria } from '../types/Categoria';
import { UserSignal } from '../signals/user.signal';
import { ArticulosService } from '../services/articulos.service';
import { fireSwal } from '../lib/swal';
import { Router } from '@angular/router';

@Component({
  selector: 'page-vender',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  template: `
    <main>
      <h1>Registrar articulo</h1>
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
        @if(hasError) {
        <small>{{ getImagenErrorMessage() }}</small>
        }
        <div>
          <label for="nombre">Nombre</label>
          <input
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
          @if(hasError) {
          <small>{{ getCategoriaErrorMessage() }}</small>
          }
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
  .image {
    margin-top: 20px;
  }
  `,
})
export class VenderPage implements OnInit {
  canSend = false;
  _userSignal: UserSignal = inject(UserSignal);
  _articuloService: ArticulosService = inject(ArticulosService);

  categorias: Categoria[] = [];
  _categoriaService: CategoriasService = inject(CategoriasService);
  async ngOnInit() {
    await this._categoriaService.getAll().then((categorias) => {
      this.categorias = categorias;
    });
  }

  articleForm = new FormGroup({
    nombre: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
    ]),
    descripcion: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(200),
    ]),
    precio: new FormControl(0, [
      Validators.required,
      Validators.min(0),
      Validators.max(1000000),
      Validators.pattern(/^\d+(\.\d{1,2})?$/),
    ]),
    imagen: new FormControl('', [Validators.required]),
    categoria: new FormControl('Selecciona una categoria', [
      Validators.required,
    ]),
    vendedor: new FormControl(this._userSignal.userSignal().id),
  });

  showImageUrl =
    'https://kinsta.com/wp-content/uploads/2023/03/angular-component-library-clarity.png';

  onChange(event: any) {
    const file = event.target.files[0];

    uploadImage(file).then((response: any) => {
      this.showImageUrl = getImageUrl(response, 200);
      this.articleForm.controls.imagen.setValue(response);
    });
  }

  hasError = false;
  private _router: Router = inject(Router);
  onSubmit() {
    const formValue = this.articleForm.value;

    this.hasError = false;

    if (this.articleForm.invalid) {
      this.hasError = true;
      return;
    }

    this._articuloService
      .createArticulo(
        formValue.nombre || '',
        formValue.descripcion || '',
        formValue.precio || 0,
        formValue.imagen || '',
        formValue.categoria || '',
        formValue.vendedor || 0
      )
      .then((register) => {
        fireSwal('Tu artículo ya esta disponible', 'success', 1000);

        setTimeout(() => {
          this._router.navigate(['/articulos']);
        }, 1000);
      });
  }

  getNombreErrorMessage() {
    if (this.articleForm.controls.nombre.hasError('required')) {
      return 'El nombre es requerido';
    }
    return this.articleForm.controls.nombre.hasError('minlength')
      ? 'El nombre debe tener al menos 3 caracteres'
      : this.articleForm.controls.nombre.hasError('maxlength')
      ? 'El nombre debe tener menos de 50 caracteres'
      : '';
  }

  getPrecioErrorMessage() {
    if (this.articleForm.controls.precio.hasError('required')) {
      return 'El precio es requerido';
    }

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
    if (this.articleForm.controls.descripcion.hasError('required')) {
      return 'La descripcion es requerida';
    }
    return this.articleForm.controls.descripcion.hasError('minlength')
      ? 'La descripcion debe tener al menos 3 caracteres'
      : this.articleForm.controls.descripcion.hasError('maxlength')
      ? 'La descripcion debe tener menos de 200 caracteres'
      : '';
  }

  getCategoriaErrorMessage() {
    if (this.articleForm.controls.categoria.hasError('required')) {
      return 'La categoria es requerida';
    }
    return this.articleForm.controls.categoria.value ===
      'Selecciona una categoria'
      ? 'La categoria es requerida'
      : '';
  }

  getImagenErrorMessage() {
    if (this.articleForm.controls.imagen.hasError('required')) {
      return 'La imagen es requerida';
    }
    return '';
  }
}
