export type Venta = {
  id: number;
  idarticulo: number;
  idsocio: number;
  idcomprador: number;
  fecha: string;
  precio: number;
};

export type VentaDetalle = {
  id: number;
  articulo: string;
  comprador: string;
  vendedor: string;
  fecha: string;
  precio: number;
};
