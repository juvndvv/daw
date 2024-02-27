import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { uploadImage, getImageUrl } from '../lib/cloudinary';
import { fireSwal } from '../lib/swal';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  template: `
    <main>
      <h1>Registro</h1>
      <form [formGroup]="registerForm" (ngSubmit)="register()">
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
          <label for="apellidos">Apellidos</label>
          <input
            formControlName="apellidos"
            type="text"
            id="apellidos"
            name="apellidos"
          />
          @if(hasError) {
          <small>{{ getApellidosErrorMessage() }}</small>
          }
        </div>
        <div>
          <label for="email">Email</label>
          <input formControlName="email" type="email" id="email" name="email" />
          @if(hasError) {
          <small>{{ getEmailErrorMessage() }}</small>
          }
        </div>
        <div class="image">
          <div>
            <label for="imagen">Foto de perfil</label>
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
          <label for="password">Contraseña</label>
          <input
            formControlName="password"
            type="password"
            id="password"
            name="password"
          />
          @if(hasError) {
          <small>{{ getPasswordErrorMessage() }}</small>
          }
        </div>
        <div>
          <label for="password2">Repetir contraseña</label>
          <input
            formControlName="password2"
            type="password"
            id="password2"
            name="password2"
          />
          @if(hasError) {
          <small>{{ getPassword2ErrorMessage() }}</small>
          }
        </div>
        <div>
          <button class="primary">Registrarse</button>
        </div>
      </form>
    </main>
  `,
  styles: `
    main {
      max-width: 600px;
    }
    `,
})
export class RegistroPage {
  private _router: Router = inject(Router);
  private _userService: UserService = inject(UserService);

  existEmail = false;
  hasError = false;
  showImageUrl = getImageUrl('user', 200);

  registerForm = new FormGroup({
    nombre: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
    ]),
    apellidos: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
    ]),
    email: new FormControl('', [Validators.required, Validators.email]),
    imagen: new FormControl(''),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(50),
    ]),
    password2: new FormControl('', [Validators.required]),
  });

  onChange(event: any) {
    const file = event.target.files[0];

    uploadImage(file).then((response: any) => {
      this.showImageUrl = getImageUrl(response, 200);
      this.registerForm.controls.imagen.setValue(response);
    });
  }

  async register() {
    const formValue = this.registerForm.value;

    this.hasError = false;

    // Check if form is valid
    if (this.registerForm.invalid) {
      this.hasError = true;
    }

    // Check if email exists
    if (formValue.email) {
      this.existEmail = await this._userService.existsEmail(formValue.email);
      if (this.existEmail) {
        this.hasError = true;
      }
    }

    if (this.hasError) {
      return;
    }

    this._userService
      .register(
        formValue.nombre || '',
        formValue.apellidos || '',
        formValue.email || '',
        formValue.password || '',
        formValue.imagen || ''
      )
      .then((response) => {
        fireSwal('¡Usuario registrado!', 'success', 1000);

        setTimeout(() => {
          this._router.navigate(['/login']);
        }, 1000);
      });
  }

  getNombreErrorMessage() {
    if (this.registerForm.controls.nombre.hasError('required')) {
      return 'Debes introducir un nombre';
    }

    return this.registerForm.controls.nombre.hasError('minlength')
      ? 'El nombre debe tener al menos 3 caracteres'
      : this.registerForm.controls.nombre.hasError('maxlength')
      ? 'El nombre debe tener menos de 50 caracteres'
      : '';
  }

  getApellidosErrorMessage() {
    if (this.registerForm.controls.apellidos.hasError('required')) {
      return 'Debes introducir un apellido';
    }

    return this.registerForm.controls.apellidos.hasError('minlength')
      ? 'El apellido debe tener al menos 3 caracteres'
      : this.registerForm.controls.apellidos.hasError('maxlength')
      ? 'El apellido debe tener menos de 50 caracteres'
      : '';
  }

  getEmailErrorMessage() {
    if (this.registerForm.controls.email.hasError('required')) {
      return 'Debes introducir un email';
    }

    if (this.existEmail) {
      return 'El email ya existe';
    }

    return this.registerForm.controls.email.hasError('email')
      ? 'Email no válido'
      : '';
  }

  getPasswordErrorMessage() {
    if (this.registerForm.controls.password.hasError('required')) {
      return 'Debes introducir una contraseña';
    }

    return this.registerForm.controls.password.hasError('minlength')
      ? 'La contraseña debe tener al menos 6 caracteres'
      : this.registerForm.controls.password.hasError('maxlength')
      ? 'La contraseña debe tener menos de 50 caracteres'
      : '';
  }

  getPassword2ErrorMessage() {
    return this.registerForm.controls.password.value !==
      this.registerForm.controls.password2.value
      ? 'Las contraseñas no coinciden'
      : '';
  }
}
