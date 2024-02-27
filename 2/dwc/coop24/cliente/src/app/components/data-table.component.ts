import { Component, Input, OnInit, inject } from '@angular/core';
import { getImageUrl } from '../lib/cloudinary';
import { ArticulosService } from '../services/articulos.service';
import { confirmSwal, fireSwal } from '../lib/swal';
import { UserService } from '../services/user.service';
import { CategoriasService } from '../services/categorias.service';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'data-table',
  imports: [RouterLink, RouterLinkActive],
  standalone: true,
  template: `
    @if (type === 'categorias') {
    <a routerLink="/dashboard/newcategory"> Crear categoría </a>
    }
    <p>Pulsa sobre una fila para mostrar las acciones</p>
    <table>
      <thead>
        <tr>
          @for (column of columns; track $index) {
          <th>{{ column }}</th>
          }
        </tr>
      </thead>
      <tbody>
        @for (row of data; track $index) {
        <tr (click)="selectRow($event)" data-id="{{ row.id }}">
          @for (column of columns; track $index) { @if (column === 'imagen' ||
          column === 'foto' || column === 'img') { @if (row[column] ===
          undefined) {
          <td>Sin imagen</td>
          } @else {
          <td>
            <img [src]="getImageUrl(row[column])" [alt]="row[column]" />
          </td>
          } } @else {
          <td>{{ row[column] }}</td>
          } }
        </tr>
        }
      </tbody>
    </table>
    @if (selectedRow !== -1) {
    <div class="actions">
      <h3>
        Acciones sobre <i>{{ selectedRepresentation }}</i>
      </h3>
      <div>
        @if (type === 'user') {
        <button (click)="blockUser()" class="danger">Bloquear</button>
        } @else if (type === 'categorias'){
        <button class="danger" (click)="deleteCategory()">Eliminar</button>
        <a
          routerLink="{{ 'updatecategory/' + selectedRow }}"
          routerLinkActive="active"
          >Editar</a
        >
        } @else {
        <button class="danger" disabled>Sin implementar</button>
        }
      </div>
    </div>
    }
  `,
  styles: `
  a {
    background-color: var(--accent-color);
    color: white;
    padding: 10px;
  }

  a + p {
    margin-top: 10px;
  }

  .actions {
    min-width: 300px;
    position: fixed;
    bottom: 0;
    right: 0;
    padding: 10px;
    background-color: white;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  .actions div {
    flex: 1;
    display: flex;
    gap: 10px;
    justify-content: center;
  }

    table {
        width: 100%;
        max-width: 1000px;
        min-width: 800px;
        margin: 32px auto 0 auto;
        font-size: .8em;
        animation: fadeIn 1s;
    }

    tr {
        height: 60px;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #ddd;
    }

    th,
    td {
        padding: 10px;
        height: 60px;
    }

    th {
        background-color: #f2f2f2;
    }

    td {
      cursor: pointer;
    }

    td img {
        margin: 0 auto;
        width: 50px;
        height: 50px;
        display: block;
        object-fit: cover;
        border-radius: 50%;
    }

    td:has(button) {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    button {
        padding: 5px 10px;
        margin: 5px;
    }

    .selected-row {
        background-color: var(--accent-color2) !important;
        color: white;
        font-weight: bold;
    }
    `,
})
export class DataTableComponent {
  @Input() type = 'table';
  @Input() data: any[] = [];
  @Input() columns: string[] = [];

  creatingCategory: boolean = false;

  getImageUrl(img: string) {
    return getImageUrl(img, 100);
  }

  selectedRow: number = -1;
  selectedData: any = {};
  selectedRepresentation: string = '';
  selectRow(event: Event) {
    const data = event.target as HTMLElement;
    const row = data.parentElement as HTMLElement;

    if (this.selectedRow !== -1) {
      data.parentElement?.classList.remove('selected-row');
    }
    document.querySelector('.selected-row')?.classList.remove('selected-row');
    row.classList.add('selected-row');

    this.selectedRow = parseInt(row.getAttribute('id') as string);

    this.data.find((d) => {
      if (parseInt(d.id) === this.selectedRow) {
        this.selectedData = d;
      }
    });

    if (this.selectedData.nombre) {
      this.selectedRepresentation = this.selectedData.nombre;
    } else if (this.selectedData.articulo) {
      this.selectedRepresentation = this.selectedData.articulo;
    }
  }

  private _articuloService: ArticulosService = inject(ArticulosService);
  deleteArticle() {
    if (this.selectedData.estado === 'V') {
      fireSwal(
        'No puedes eliminar un artículo vendido',
        'error',
        2000,
        'center'
      );
    } else {
      confirmSwal(
        '¿Estás seguro de que quieres eliminar este artículo?',
        'warning'
      ).then((result) => {
        if (result.isConfirmed) {
          this._articuloService.deleteArticulo(this.selectedRow);
          document.querySelector('.selected-row')?.remove();
          this.selectedRow = -1;
        }
      });
    }
  }

  editArticle() {
    console.log('Editando artículo');
  }

  // Usuarios
  private _router: Router = inject(Router);
  private _userService: UserService = inject(UserService);
  blockUser() {
    this._userService.block(this.selectedRow).then((response) => {
      fireSwal('Usuario bloqueado', 'success', 1000);
    });
  }

  // Categorias
  private _categoriaService: CategoriasService = inject(CategoriasService);
  editCategory() {
    console.log('Editando categoría', this.selectedRow);
  }

  deleteCategory() {
    console.log('Eliminando categoría', this.selectedRow);
    confirmSwal(
      '¿Estás seguro de que quieres eliminar esta categoría?',
      'warning'
    ).then((result) => {
      if (result.isConfirmed) {
        this._categoriaService.delete(this.selectedRow);
        document.querySelector('.selected-row')?.remove();
        this.selectedRow = -1;
      }
    });
  }
}
