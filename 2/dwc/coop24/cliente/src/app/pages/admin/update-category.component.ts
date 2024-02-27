import { Component, Input, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { getImageUrl, uploadImage } from '../../lib/cloudinary';
import { UserSignal } from '../../signals/user.signal';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriasService } from '../../services/categorias.service';
import { fireSwal } from '../../lib/swal';
import { Categoria } from '../../types/Categoria';
import { User } from '../../types/User';

@Component({
  selector: 'update-category',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  template: `
    <main>
      <h2>Modificar una categoria</h2>
      <form (ngSubmit)="onSubmit()" [formGroup]="categoryForm">
        <div>
          <label for="nombre">Nombre</label>
          <input
            [placeholder]="categoria?.nombre"
            formControlName="nombre"
            type="text"
            placeholder="Nombre"
          />
          @if (hasError) {
          <small>{{ getNombreErrorMessage() }}</small>
          }
        </div>
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
        @if (hasError) {
        <small>{{ getImagenErrorMessage() }}</small>
        }
        <button class="primary">Crear</button>
      </form>
    </main>
  `,
  styles: `
    main {
      max-width: 600px;
    }
  `,
})
export class UpdateCategoryComponent {
  private _userSignal: UserSignal = inject(UserSignal);
  private _router: Router = inject(Router);
  private _activeRoute: ActivatedRoute = inject(ActivatedRoute);
  constructor() {
    if (this._userSignal.userSignal().tipo !== 'admin') {
      this._router.navigate(['/comprar']);
    }
  }

  categoria: Categoria | undefined;
  showImageUrl = '';
  async ngOnInit() {
    // Obtiene la categoria
    try {
      const id = this._activeRoute.snapshot.params['id'];
      await this._categoryService.getById(id).then((categoria) => {
        this.categoria = categoria;
        this.showImageUrl = getImageUrl(this.categoria.imagen, 200);
      });
    } catch (error) {
      console.log('no existe');
    }
  }

  getShowImageUrl() {
    return this.showImageUrl;
  }

  hasError = false;
  categoryForm = new FormGroup({
    nombre: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(20),
    ]),
    imagen: new FormControl(''),
  });

  onChange(event: any) {
    const file = event.target.files[0];
    uploadImage(file).then((response: any) => {
      this.showImageUrl = getImageUrl(response, 200);

      this.categoryForm.controls.imagen.setValue(response);
    });
  }

  private _categoryService: CategoriasService = inject(CategoriasService);
  onSubmit() {
    const formValue = this.categoryForm.value;

    this.hasError = false;

    if (formValue.nombre === '') {
      formValue.nombre = this.categoria?.nombre;
    } else if (
      this.categoryForm.controls.nombre.hasError('minlength') ||
      this.categoryForm.controls.nombre.hasError('maxlength')
    ) {
      this.hasError = true;
      return;
    }

    if (formValue.imagen === '') {
      formValue.imagen = this.categoria?.imagen;
    }

    this._categoryService
      .update(
        this.categoria?.id || 0,
        formValue.nombre || '',
        formValue.imagen || ''
      )
      .then((response) => response.text())
      .then((_) => {
        fireSwal('Categoria actualizada', 'success', 1000);
        this._router.navigate(['/dashboard']);
      });
  }

  getNombreErrorMessage() {
    if (this.categoryForm.controls.nombre.hasError('required')) {
      return 'El nombre es requerido';
    }
    if (this.categoryForm.controls.nombre.hasError('minlength')) {
      return 'El nombre debe tener al menos 3 caracteres';
    }

    return this.categoryForm.controls.nombre.hasError('maxlength')
      ? 'El nombre debe tener menos de 20 caracteres'
      : '';
  }

  getImagenErrorMessage() {
    if (this.categoryForm.controls.imagen.hasError('required')) {
      return 'La imagen es requerida';
    }
    return '';
  }
}
