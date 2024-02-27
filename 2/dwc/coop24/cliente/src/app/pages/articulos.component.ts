import { Component, inject } from '@angular/core';
import { PaginableComponent } from '../components/paginable.component';
import { ArticulosService } from '../services/articulos.service';
import { Articulo } from '../types/Articulo';
import { UserSignal } from '../signals/user.signal';
import { ArticuloMapper } from '../mappers/articulo.mapper';

@Component({
  selector: 'page-articulos',
  standalone: true,
  imports: [PaginableComponent],
  template: `
    <main>
      <h1>Mis artículos</h1>
      <section>
        <h2>En venta</h2>
        <app-paginable
          class="paginable"
          [items]="articulosDisponibles"
          [itemsPerPage]="5"
        ></app-paginable>
      </section>
      <section>
        <h2>Vendidos</h2>
        <app-paginable
          class="paginable"
          [items]="articulosVendidos"
          [itemsPerPage]="5"
        ></app-paginable>
      </section>
    </main>
  `,
  styles: `
  main {
    height: none;
    display: flex;
    flex-direction: column;
    gap: 1rem;
  }

  section {
    display: flex;
    flex-direction: column;
    border: 1px solid #ccc;
    border-radius: 0.5rem;
    padding: 1rem;
  }

  app-paginable {
    flex: 1;
  }
  `,
})
export class ArticulosPage {
  private _articuloMapper: ArticuloMapper = inject(ArticuloMapper);
  private _articuloService: ArticulosService = inject(ArticulosService);
  private _userSignal: UserSignal = inject(UserSignal);

  articulosDisponibles: Articulo[] = [];
  articulosVendidos: Articulo[] = [];

  ngOnInit() {
    this._articuloService
      .getArticulosSocio(this._userSignal.userSignal().id)
      .then((response) => response.json())
      .then((data) => {
        this.articulosDisponibles = data
          .filter((articulo: Articulo) => articulo.estado === 'D')
          .map((articulo: any) => this._articuloMapper.mapArticle(articulo));

        this.articulosVendidos = data
          .filter((articulo: Articulo) => articulo.estado === 'V')
          .map((articulo: any) => this._articuloMapper.mapArticle(articulo));
      })
      .catch((error) => {
        console.log('sin articulos');
      });
  }
}
