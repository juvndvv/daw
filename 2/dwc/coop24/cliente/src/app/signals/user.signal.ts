import { Injectable, effect, inject, signal } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { User } from '../types/User';
import { UserMapper } from '../mappers/user.mapper';

@Injectable({
  providedIn: 'root',
})
export class UserSignal {
  private _cookieService: CookieService = inject(CookieService);
  private _userMapper: UserMapper = inject(UserMapper);
  userSignal = signal({} as User);

  constructor() {
    // Comprueba si el usuario está logueado
    const userCookie: User = this.getUserCookie();

    // Si el usuario está logueado
    if (userCookie.id) {
      this.userSignal.set(userCookie);
    }

    effect(() => {
      // Actualiza la cookie del usuario
      if (this.userSignal().id) {
        this._cookieService.set('user', JSON.stringify(this.userSignal()));
      }
    });
  }

  updateUser(user: any, sync = false) {
    user = this._userMapper.mapUser(user);
    this.userSignal.set(user);
  }

  clearUser() {
    this.userSignal.set({} as User);
    this._cookieService.delete('user');
    console.log(this.userSignal());
  }

  private getUserCookie(): any {
    const userCookie = this._cookieService.get('user');
    if (userCookie) {
      return JSON.parse(userCookie);
    }
    return {};
  }
}
