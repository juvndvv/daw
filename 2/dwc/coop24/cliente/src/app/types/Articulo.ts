export type Articulo = {
  id: number;
  nombre: string;
  descripcion: string;
  precio: number;
  img: string;
  categoria: number;
  vendedor: number;
  estado: EstadoArticulo;
};

export enum EstadoArticulo {
  Disponible = 'D',
  Vendido = 'V',
}
