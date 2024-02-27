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
import { Router } from '@angular/router';
import { CategoriasService } from '../../services/categorias.service';
import { fireSwal } from '../../lib/swal';
import { Categoria } from '../../types/Categoria';

@Component({
  selector: 'create-category',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  template: `
    <main>
      <h2>Crear una categoria</h2>
      <form (ngSubmit)="onSubmit()" [formGroup]="categoryForm">
        <div>
          <label for="nombre">Nombre</label>
          <input formControlName="nombre" type="text" placeholder="Nombre" />
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
export class CreateCategoryComponent {
  private _userSignal: UserSignal = inject(UserSignal);
  private _router: Router = inject(Router);
  constructor() {
    if (this._userSignal.userSignal().tipo !== 'admin') {
      this._router.navigate(['/comprar']);
    }
  }

  hasError = false;
  categoryForm = new FormGroup({
    nombre: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(20),
    ]),
    imagen: new FormControl('', Validators.required),
  });

  showImageUrl =
    'https://kinsta.com/wp-content/uploads/2023/03/angular-component-library-clarity.png';

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

    if (this.categoryForm.invalid) {
      this.hasError = true;
      return;
    }

    this._categoryService
      .create(formValue.nombre || '', formValue.imagen || '')
      .then((response) => response.text())
      .then((_) => {
        fireSwal('Categoria creada', 'success', 1000);
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
