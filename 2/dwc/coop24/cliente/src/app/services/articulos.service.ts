import { Injectable, inject } from '@angular/core';
import { env } from '../../env';
import { Articulo } from '../types/Articulo';
import { VentaMapper } from '../mappers/venta.mapper';
import { Venta } from '../types/Venta';
import { response } from 'express';

@Injectable({
  providedIn: 'root',
})
export class ArticulosService {
  getArticulos() {
    const url = env.articulosVentaURL;

    return fetch(url, {
      method: 'GET',
    });
  }

  getArticuloById(id: number) {
    const url = env.articuloConsultaURL + `&idarticulo=${id}`;

    return fetch(url, {
      method: 'GET',
    }).then((response) => response.json());
  }

  getArticulosSocio(id: number) {
    const url = env.socioArticulosURL + `&idsocio=${id}`;

    return fetch(url, {
      method: 'GET',
    });
  }

  createArticulo(
    nombre: string,
    descripcion: string,
    precio: number,
    imagen: string,
    categoria: string,
    vendedor: number
  ) {
    const url = env.registroArticuloURL;

    const formData = new FormData();

    formData.append('nombre', nombre);
    formData.append('descripcion', descripcion);
    formData.append('precio', precio.toString());
    formData.append('imagen', imagen);
    formData.append('categoria', categoria.toString());
    formData.append('vendedor', vendedor.toString());

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }

  deleteArticulo(id: number) {
    const url = env.borradoArticuloURL + `&idarticulo=${id}`;

    return fetch(url);
  }

  updateArticulo(
    id: number,
    nombre: string,
    descripcion: string,
    precio: number,
    imagen: string,
    categoria: string
  ) {
    const url = env.modificarArticuloURL;

    const formData = new FormData();

    formData.append('idarticulo', id.toString());
    formData.append('nombre', nombre);
    formData.append('descripcion', descripcion);
    formData.append('precio', precio.toString());
    formData.append('imagen', imagen);
    formData.append('categoria', categoria);

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }

  comprarArticulo(id: number, comprador: number) {
    const url = env.comprarArticuloURL;

    const formData = new FormData();

    formData.append('idarticulo', id.toString());
    formData.append('idsocio', comprador.toString());

    return fetch(url, {
      method: 'POST',
      body: formData,
    });
  }

  private _ventaMapper: VentaMapper = inject(VentaMapper);
  getVentaById(id: number) {
    const url = env.consultaVentaURL + `&idarticulo=${id}`;

    return fetch(url, {
      method: 'GET',
    })
      .then((response) => response.json())
      .then((venta) => {
        return this._ventaMapper.mapVenta(venta[0]);
      });
  }

  getAllVentas(): Promise<Venta[]> {
    const url = env.todasVentasURL;

    return fetch(url, {
      method: 'GET',
    })
      .then((response) => response.json())
      .then((ventas) => {
        return ventas.map((venta: any) => this._ventaMapper.mapVenta(venta));
      });
  }

  getAll(): Promise<Articulo[]> {
    const url = env.todosArticulosURL;

    return fetch(url, {
      method: 'GET',
    })
      .then((response) => response.json())
      .then((articulos) => {
        return articulos.map((articulo: any) => {
          return {
            id: articulo.id,
            nombre: articulo.nombre,
            descripcion: articulo.descripcion,
            precio: articulo.precio,
            img: articulo.imagen,
            categoria: articulo.categoria,
            vendedor: articulo.vendedor,
            estado: articulo.estado,
          };
        });
      });
  }
}
