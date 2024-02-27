import { Injectable, inject } from '@angular/core';
import { env } from '../../env';
import { CategoriaMapper } from '../mappers/categoria.mapper';

@Injectable({
  providedIn: 'root',
})
export class CategoriasService {
  private _categoriaMapper: CategoriaMapper = inject(CategoriaMapper);

  getAll() {
    const url = env.categoriasURL;

    return fetch(url)
      .then((response) => response.json())
      .then((data) => {
        return data.map((categoria: any) =>
          this._categoriaMapper.mapCategoria(categoria)
        );
      });
  }

  getById(id: number) {
    const url = env.obtenerCategoriaURL;

    const formData = new FormData();
    formData.append('idarticulo', id.toString());

    return fetch(url, {
      method: 'POST',
      body: formData,
    })
      .then((response) => response.json())
      .then((data) => {
        return this._categoriaMapper.mapCategoria(data[0]);
      });
  }

  create(nombre: string, imagen: string) {
    const url = env.crearCategoriaURL;

    const formData = new FormData();
    formData.append('categoria', nombre);
    formData.append('imagen', imagen);

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }

  update(id: number, nombre: string, imagen: string) {
    const url = env.modificarCategoruaURL;

    const formData = new FormData();
    formData.append('idarticulo', id.toString());
    formData.append('categoria', nombre);
    formData.append('imagen', imagen);

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }

  delete(id: number) {
    const url = env.eliminarCategoriaURL;

    const formData = new FormData();
    formData.append('idarticulo', id.toString());

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }
}
