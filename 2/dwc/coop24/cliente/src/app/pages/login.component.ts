import { Component, inject } from '@angular/core';
import { UserService } from '../services/user.service';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { UserSignal } from '../signals/user.signal';
import { Router } from '@angular/router';
import { fireSwal } from '../lib/swal';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  template: `
    <main>
      <h1>Iniciar sesión</h1>
      <form [formGroup]="loginForm" (ngSubmit)="login()">
        <div>
          <label for="email">Email</label>
          <input formControlName="email" type="email" id="email" name="email" />
          @if(hasError) {
          <small>{{ getEmailErrorMessage() }}</small>
          }
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
          <button class="primary">Iniciar sesión</button>
        </div>
        @if(invalidLogin) {
        <small>Usuario o contraseña incorrectos</small>
        }
      </form>
    </main>
  `,
  styles: `
  main {
    max-width: 600px;
  }
  `,
})
export class LoginPage {
  private _userService: UserService = inject(UserService);
  private _userSignal: UserSignal = inject(UserSignal);
  private _router: Router = inject(Router);

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  });

  hasError = false;
  invalidLogin = false;

  login() {
    const formValue = this.loginForm.value;

    if (this.loginForm.invalid) {
      this.hasError = true;
      return;
    }

    this._userService
      .login(formValue.email || '', formValue.password || '')
      .then((response) => response.json())
      .then((response) => {
        // checkea si esta bloqueado
        if (response[0].rol === 'B') {
          fireSwal('Usuario bloqueado', 'error', 1000);
          return;
        }

        // Aqui devuelve el usuario
        this._userSignal.updateUser(response[0]);

        fireSwal('¡Bienvenido!', 'success', 1000);

        setTimeout(() => {
          this._router.navigate(['/comprar']);
        }, 1000);
      })
      .catch((error) => {
        // Aqui error de login
        console.error('Login error', error);
        this.invalidLogin = true;
      });
  }

  getEmailErrorMessage() {
    if (this.loginForm.controls.email.hasError('required')) {
      return 'Debes introducir un email';
    }

    return this.loginForm.controls.email.hasError('email')
      ? 'Email no válido'
      : '';
  }

  getPasswordErrorMessage() {
    return this.loginForm.controls.password.hasError('required')
      ? 'Debes introducir una contraseña'
      : '';
  }
}
