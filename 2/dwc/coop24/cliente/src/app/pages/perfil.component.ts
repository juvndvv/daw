import { Component, inject } from '@angular/core';
import { UserSignal } from '../signals/user.signal';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { getImageUrl, uploadImage } from '../lib/cloudinary';
import { UserService } from '../services/user.service';
import { fireSwal } from '../lib/swal';

@Component({
  selector: 'page-perfil',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  template: `
    <main>
      <h1>Modificar socio</h1>
      <form (ngSubmit)="register()" [formGroup]="registerForm">
        <div>
          <label for="nombre">Nombre</label>
          <input
            [placeholder]="_userSignal.userSignal().nombre"
            type="text"
            id="nombre"
            name="nombre"
            formControlName="nombre"
          />
          @if(hasError) {
          <small>{{ getNombreErrorMessage() }}</small>
          }
        </div>
        <div>
          <label for="apellidos">Apellidos</label>
          <input
            [placeholder]="_userSignal.userSignal().apellidos"
            type="text"
            id="apellidos"
            name="apellidos"
            formControlName="apellidos"
          />
          @if(hasError) {
          <small>{{ getApellidosErrorMessage() }}</small>
          }
        </div>
        <div>
          <label for="email">Email</label>
          <input
            [placeholder]="_userSignal.userSignal().email"
            type="email"
            id="email"
            name="email"
            formControlName="email"
          />
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
          <button class="secondary">Cancelar</button>
          <button class="primary">Enviar</button>
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
export class PerfilPage {
  _userSignal: UserSignal = inject(UserSignal);
  _userService: UserService = inject(UserService);

  showImageUrl = getImageUrl(this._userSignal.userSignal().imagen, 200);
  hasError = false;
  existEmail = false;

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
      Validators.minLength(4),
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

    if (this.registerForm.invalid || !this.passwordMatch()) {
      this.hasError = true;
      return;
    }

    // Check if email exists
    if (formValue.email) {
      this.existEmail = await this._userService.existsEmail(formValue.email);
      if (
        this.existEmail &&
        formValue.email !== this._userSignal.userSignal().email
      ) {
        this.hasError = true;
      }
    }

    if (this.hasError) {
      return;
    }

    this._userService
      .update(
        this._userSignal.userSignal().id,
        formValue.nombre || this._userSignal.userSignal().nombre,
        formValue.apellidos || this._userSignal.userSignal().apellidos,
        formValue.email || this._userSignal.userSignal().email,
        formValue.password || this._userSignal.userSignal().password,
        formValue.imagen || this._userSignal.userSignal().imagen
      )
      // Actualizar el usuario en el signal
      .then((_) => {
        this._userSignal.updateUser({
          id: this._userSignal.userSignal().id,
          nombre: formValue.nombre || this._userSignal.userSignal().nombre,
          apellidos:
            formValue.apellidos || this._userSignal.userSignal().apellidos,
          email: formValue.email || this._userSignal.userSignal().email,
          foto: formValue.imagen || this._userSignal.userSignal().imagen,
          rol: this._userSignal.userSignal().tipo === 'user' ? 'U' : 'A',
        });

        fireSwal('¡Usuario modificado!', 'success', 1000);
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

  passwordMatch() {
    return (
      this.registerForm.controls.password.value ===
      this.registerForm.controls.password2.value
    );
  }

  getPassword2ErrorMessage() {
    return !this.passwordMatch() ? 'Las contraseñas no coinciden' : '';
  }
}
