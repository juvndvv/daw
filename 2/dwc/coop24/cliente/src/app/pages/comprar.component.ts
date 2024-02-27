import { Component, inject } from '@angular/core';
import { PaginableComponent } from '../components/paginable.component';
import { ArticulosService } from '../services/articulos.service';
import { ArticuloMapper } from '../mappers/articulo.mapper';
import { Articulo } from '../types/Articulo';
import { UserSignal } from '../signals/user.signal';
import { Categoria } from '../types/Categoria';
import { CategoriasService } from '../services/categorias.service';

@Component({
  selector: 'page-comprar',
  standalone: true,
  imports: [PaginableComponent],
  template: `
    <main>
      <h1>Artículos en venta</h1>
      <p>Haz click en la imagen para ver el detalle del articulo</p>
      <select (change)="filtrarCategoria($event)">
        <option selected value="0">Todas las categorias</option>
        @for (categoria of categorias; track $index) {
        <option value="{{ categoria.id }}">{{ categoria.nombre }}</option>
        }
      </select>
      <app-paginable
        [items]="articulosFiltrados"
        [itemsPerPage]="10"
      ></app-paginable>
    </main>
  `,
  styles: `
    main {
    display: flex;
    flex-direction: column;
    gap: 1rem;
  }

  app-paginable {
    flex: 1;
    border: 1px solid #ccc;
    border-radius: 0.5rem;
    padding: 1rem;
  }
  `,
})
export class ComprarPage {
  private _articuloService: ArticulosService = inject(ArticulosService);
  private _categoriaService: CategoriasService = inject(CategoriasService);

  private _articuloMapper: ArticuloMapper = inject(ArticuloMapper);
  private _userSignal: UserSignal = inject(UserSignal);

  articulos: Articulo[] = [];

  categorias: Categoria[] = [];
  articulosFiltrados: Articulo[] = [];

  async ngOnInit() {
    const response = await this._articuloService.getArticulos();
    const data = await response.json();
    this.articulos = data
      .map((articulo: any) => {
        return this._articuloMapper.mapArticle(articulo);
      })
      .filter((articulo: Articulo) => {
        return articulo.vendedor !== this._userSignal.userSignal().id;
      });

    this.articulosFiltrados = this.articulos;

    this.categorias = await this._categoriaService.getAll();
  }

  async filtrarCategoria(event: any) {
    const categoria = event.target.value;
    if (categoria === '0') {
      this.articulosFiltrados = this.articulos;
    } else {
      this.articulosFiltrados = this.articulos.filter((articulo) => {
        return articulo.categoria === parseInt(categoria);
      });
    }
  }
}
