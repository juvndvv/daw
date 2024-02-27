import { Injectable, inject } from '@angular/core';
import { env } from '../../env';
import { User } from '../types/User';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  login(email: string, password: string) {
    const url = env.loginURL;

    const formData = new FormData();
    formData.append('email', email);
    formData.append('password', password);

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }

  register(
    nombre: string,
    apellidos: string,
    email: string,
    password: string,
    imagen: string
  ) {
    const url = env.registroURL;

    const formData = new FormData();
    formData.append('nombre', nombre);
    formData.append('apellidos', apellidos);
    formData.append('email', email);
    formData.append('password', password);
    formData.append('foto', imagen);

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }

  update(
    id: number,
    nombre: string,
    apellidos: string,
    email: string,
    password: string,
    imagen: string
  ) {
    const url = env.modificarSocioURL;

    const formData = new FormData();

    formData.append('idsocio', id.toString());
    formData.append('nombre', nombre);
    formData.append('apellidos', apellidos);
    formData.append('email', email);
    formData.append('password', password);
    formData.append('foto', imagen);

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }

  existsEmail(email: string) {
    const url = env.existeEmailURL;

    const formData = new FormData();
    formData.append('email', email);

    return fetch(url, {
      method: 'POST',
      body: formData,
    })
      .then((response) => response.json())
      .then((response) => {
        return response.exist;
      });
  }

  getById(id: number): Promise<User> {
    return fetch(env.socioConstultaURL + `&idsocio=${id}`)
      .then((response) => response.json())
      .then((response) => response[0]);
  }

  getAll(): Promise<User[]> {
    return fetch(env.todosSociosURL).then((response) => response.json());
  }

  block(id: number) {
    const url = env.bloquearSocioURL;

    const formData = new FormData();
    formData.append('idsocio', id.toString());

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }
}
