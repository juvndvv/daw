import { Component, inject } from '@angular/core';
import { ArticulosService } from '../../services/articulos.service';
import { Articulo } from '../../types/Articulo';
import { DataTableComponent } from '../../components/data-table.component';

@Component({
  selector: 'admin-articulos',
  standalone: true,
  imports: [DataTableComponent],
  template: `
    <h1>Articulos</h1>
    <data-table
      type="articulos"
      [data]="articulos"
      [columns]="[
        'id',
        'nombre',
        'descripcion',
        'precio',
        'img',
        'categoria',
        'vendedor',
        'estado'
      ]"
    ></data-table>
  `,
  styles: `
    h1 {
      margin-bottom: 20px;
    }
  `,
})
export class AdminArticulosPage {
  private _articuloService: ArticulosService = inject(ArticulosService);

  articulos: Articulo[] = [];
  async ngOnInit() {
    this.articulos = await this._articuloService.getAll();
  }
}
