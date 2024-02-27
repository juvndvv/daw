import { Injectable } from '@angular/core';
import { User } from '../types/User';
import { getImageUrl } from '../lib/cloudinary';

@Injectable({
  providedIn: 'root',
})
export class UserMapper {
  mapUser(user: any): User {
    let rol;
    switch (user.rol) {
      case 'B':
        rol = 'bloqueado';
        break;
      case 'U':
        rol = 'user';
        break;
      case 'A':
        rol = 'admin';
        break;
    }

    user = {
      id: parseInt(user.id),
      nombre: user.nombre,
      apellidos: user.apellidos,
      email: user.email,
      password: user.password,
      imagen:
        user.foto === ''
          ? 'user'
          : user.foto.replace('.jpg', '').replace('.png', ''),
      tipo: rol,
    };

    return user;
  }
}
