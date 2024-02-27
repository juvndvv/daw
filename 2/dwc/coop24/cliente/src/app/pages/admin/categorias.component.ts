import { Component, inject } from '@angular/core';
import { CategoriasService } from '../../services/categorias.service';
import { Categoria } from '../../types/Categoria';
import { DataTableComponent } from '../../components/data-table.component';

@Component({
  selector: 'admin-categorias',
  standalone: true,
  imports: [DataTableComponent],
  template: `
    <h1>Categorias</h1>
    <data-table
      type="categorias"
      [data]="categorias"
      [columns]="['nombre']"
    ></data-table>
  `,
  styles: `
    h1 {
      margin-bottom: 20px;
    }
  `,
})
export class AdminCategoriasPage {
  private _categoriasService: CategoriasService = inject(CategoriasService);

  categorias: Categoria[] = [];
  async ngOnInit() {
    this.categorias = await this._categoriasService.getAll();
  }
}
