import { Injectable, inject } from '@angular/core';
import { Venta, VentaDetalle } from '../types/Venta';

@Injectable({
  providedIn: 'root',
})
export class VentaMapper {
  mapVenta(venta: any): Venta {
    return {
      id: parseInt(venta.idventa),
      idarticulo: parseInt(venta.articulo),
      idsocio: parseInt(venta.socio),
      idcomprador: parseInt(venta.comprador),
      fecha: venta.fecha,
      precio: parseFloat(venta.precio),
    };
  }
}
