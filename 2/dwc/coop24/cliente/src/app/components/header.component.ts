import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faUser,
  faCartShopping,
  faMoneyBill,
  faLock,
  faRightFromBracket,
  faUserTie,
  faBars as faHamburger,
} from '@fortawesome/free-solid-svg-icons';

import { UserSignal } from '../signals/user.signal';
import { getImageUrl } from '../lib/cloudinary';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, FontAwesomeModule],
  template: `
    <header>
      <h2>[COOPbyDWC]</h2>
      <nav>
        @if(_userSignal.userSignal().id) {
        <a routerLink="/comprar">
          <fa-icon [icon]="faCartShopping"></fa-icon>
          <span [className]="getClass('/comprar')">Comprar</span>
        </a>
        <a routerLink="vender">
          <fa-icon [icon]="faMoneyBill"></fa-icon>
          <span [className]="getClass('/vender')">Vender</span>
        </a>
        <a routerLink="articulos">
          <fa-icon [icon]="faLock"></fa-icon>
          <span [className]="getClass('/articulos')">Mis artículos</span>
        </a>
        <a routerLink="perfil">
          <fa-icon [icon]="faUser"></fa-icon>
          <span [className]="getClass('/perfil')">Mi perfil</span>
        </a>
        @if(_userSignal.userSignal().tipo === 'admin') {
        <a routerLink="dashboard">
          <fa-icon [icon]="faUserTie"></fa-icon>
          <span [className]="getClass('/dashboard')">Administración</span>
        </a>
        }
        <a (click)="logout()">
          <fa-icon [icon]="faRightFromBracket"></fa-icon>
          <span>Logout</span>
        </a>
        <small>{{ _userSignal.userSignal().nombre }}</small>
        <img [src]="imageUrl" alt="" />
        } @else {
        <a routerLink="login">
          <fa-icon [icon]="faUser"></fa-icon>
          <span [className]="getClass('/login')">Login</span>
        </a>
        <a routerLink="registro">
          <fa-icon [icon]="faRightFromBracket"></fa-icon>
          <span [className]="getClass('/registro')">Registro</span>
        </a>
        }
      </nav>
    </header>
  `,
  styles: `
  header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-inline: 16px;
    height: 64px;
    border-bottom: 1px solid var(--accent-color);
  }

  h2 {
    opacity: 0.7;
  }

  nav {
    display: flex;
    gap: 16px;
  }

  nav > div {
    height: 100%;
    flex: 1;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    gap: 16px;
  }

  nav  img {
    width: 32px;
    height: 32px;
    border-radius: 50%;
  }

  nav  a, small {
    opacity: 0.7;
    cursor: pointer;
    display: inline-block;
  }

  nav  a:hover {
    text-decoration: underline;
  }

  .current {
    text-decoration: underline;
  }

  nav > a > span {
    margin-left: 8px;
  }

  small {
    border-left: 1px solid var(--accent-color);
    padding-inline-start: 8px;
    line-height: 32px;
  }
  `,
})
export class HeaderComponent {
  _userSignal: UserSignal = inject(UserSignal);
  _router: Router = inject(Router);

  imageUrl = getImageUrl(this._userSignal.userSignal().imagen, 200);

  faUser = faUser;
  faCartShopping = faCartShopping;
  faMoneyBill = faMoneyBill;
  faLock = faLock;
  faRightFromBracket = faRightFromBracket;
  faUserTie = faUserTie;
  faHamburger = faHamburger;

  isCurrentRoute(route: string): boolean {
    return this._router.url === route;
  }

  getClass(route: string): string {
    return this.isCurrentRoute(route) ? 'current' : '';
  }

  logout() {
    this._userSignal.clearUser();
    this._router.navigate(['/login']);
  }
}
