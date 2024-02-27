import { Component, inject } from '@angular/core';
import { ArticulosService } from '../../services/articulos.service';
import { Venta, VentaDetalle } from '../../types/Venta';
import { UserService } from '../../services/user.service';
import { DataTableComponent } from '../../components/data-table.component';

@Component({
  selector: 'admin-ventas',
  standalone: true,
  imports: [DataTableComponent],
  template: `
    <h1>Ventas</h1>
    @defer () {
    <data-table
      type="venta"
      [columns]="['fecha', 'comprador', 'vendedor', 'articulo', 'precio']"
      [data]="ventas"
    >
    </data-table>
    } @placeholder {
    <span class="loader"></span>
    }
  `,
  styles: `
        h1 {
        margin-bottom: 20px;
      }`,
})
export class AdminVentasPage {
  private _articuloService: ArticulosService = inject(ArticulosService);
  private _userService: UserService = inject(UserService);
  ventas: VentaDetalle[] = [];

  ngOnInit() {
    this._articuloService.getAllVentas().then((ventas: Venta[]) => {
      this.ventas = ventas
        .map((venta) => this.getInformacionVenta(venta))
        .reverse();
      // TODO ordenar por fecha
    });
  }

  getInformacionVenta(venta: Venta): VentaDetalle {
    const ventaDetalle: VentaDetalle = {} as VentaDetalle;
    // Obtiene el detalle de la venta
    this._articuloService.getVentaById(venta.idarticulo).then(async (venta) => {
      ventaDetalle.id = venta.id;
      ventaDetalle.fecha = venta.fecha;
      ventaDetalle.precio = venta.precio;

      Promise.all([
        this._userService.getById(venta.idcomprador),
        this._userService.getById(venta.idsocio),
        this._articuloService.getArticuloById(venta.idarticulo),
      ]).then((values) => {
        ventaDetalle.comprador = values[0].nombre + ' ' + values[0].apellidos;
        ventaDetalle.vendedor = values[1].nombre + ' ' + values[1].apellidos;
        ventaDetalle.articulo = values[2][0].nombre;
      });
    });

    return ventaDetalle;
  }
}
