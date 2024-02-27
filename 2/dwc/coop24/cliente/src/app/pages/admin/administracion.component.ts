import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserSignal } from '../../signals/user.signal';
import { AdminArticulosPage } from './articulos.component';
import { AdminVentasPage } from './ventas.component';
import { AdminUsuariosPage } from './usuarios.component';
import { AdminCategoriasPage } from './categorias.component';

@Component({
  selector: 'page-administracion',
  standalone: true,
  imports: [
    AdminArticulosPage,
    AdminVentasPage,
    AdminUsuariosPage,
    AdminCategoriasPage,
  ],
  template: `
    <nav>
      <div class="triangulo"></div>
      <button
        (click)="changeContent('articulos')"
        [className]="getClass('articulos')"
      >
        Articulos
        <div></div>
      </button>
      <button
        (click)="changeContent('ventas')"
        [className]="getClass('ventas')"
      >
        Ventas
        <div></div>
      </button>
      <button
        (click)="changeContent('usuarios')"
        [className]="getClass('usuarios')"
      >
        Usuarios
        <div></div>
      </button>
      <button
        (click)="changeContent('categorias')"
        [className]="getClass('categorias')"
      >
        Categorias
        <div></div>
      </button>
    </nav>
    <main>
      @if(current === 'articulos') {
      <admin-articulos></admin-articulos>
      } @else if(current === 'ventas') {
      <admin-ventas></admin-ventas>
      } @else if (current === 'usuarios') {
      <admin-usuarios></admin-usuarios>
      } @else if (current === 'categorias') {
      <admin-categorias></admin-categorias>
      }
    </main>
  `,
  styles: `
  :host{
    display: grid;
    grid-template-columns: 200px 1fr;
    height: calc(100vh - 64px);
    min-height: 800px;
  }
        main {
            width: 100%;
            max-width: 1000px;
        }

        nav {
          top: 0;
            display: flex;
            flex-direction: column;
            border-right: 1px solid #ccc;
        }

        nav button {
            padding: 1rem;
            border: none;
            cursor: pointer;
            background-color: var(--accent-color);
            color: white;
            font-size: 1.3rem;
            font-weight: bold;
        }

        nav button:hover {
            opacity: 0.7;
        }

        nav button.selected {
            text-decoration: underline;
            position: relative;
        }

        .selected div {
            position: absolute;
            top: 0;
            right: -10px;
          width: 1px; 
          height: 1px; 
          border-left: 10px solid var(--accent-color);
          border-top: 28px solid transparent;
          border-bottom: 28px solid transparent; 
        }
    `,
})
export class AdministracionPage {
  private _router: Router = inject(Router);
  private _userSignal: UserSignal = inject(UserSignal);

  constructor() {
    if (this._userSignal.userSignal().tipo === 'user') {
      this._router.navigate(['/comprar']);
    }
  }

  current = 'articulos';
  changeContent(page: string) {
    this.current = page;
  }

  getClass(page: string) {
    return this.current === page ? 'selected' : '';
  }
}
