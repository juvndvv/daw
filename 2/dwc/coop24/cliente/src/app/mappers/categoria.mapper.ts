import { Injectable } from '@angular/core';
import { Categoria } from '../types/Categoria';

@Injectable({
  providedIn: 'root',
})
export class CategoriaMapper {
  mapCategoria(categoria: any): Categoria {
    return {
      id: parseInt(categoria.id),
      nombre: categoria.nombre,
      imagen: categoria.imagen,
    };
  }
}
