import { Component, Input } from '@angular/core';
import { ArticuloComponent } from './articulo.component';

@Component({
  selector: 'app-paginable',
  standalone: true,
  imports: [ArticuloComponent],
  template: `
    <div class="items">
      @for (item of visibleItems; track $index) {
      <app-articulo [articulo]="item"></app-articulo>
      } @empty {
      <p>No hay artículos</p>
      }
    </div>
    <div class="pages">
      <button (click)="previous()" [disabled]="!hasPrevious">Anterior</button>
      @for (page of getPages(); track $index) {
      <button
        [className]="getClass($index)"
        (click)="goTo($index + 1)"
        [disabled]="page + 1 === currentPage"
      >
        {{ $index + 1 }}
      </button>
      }
      <button (click)="next()" [disabled]="!hasNext">Siguiente</button>
    </div>
  `,
  styles: `

  :host {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  .items {
    flex: 1;
    width: 100%;
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
    place-items: center;
    gap: 1rem;
    }

    .pages {
        align-self: flex-end;
    }

    button {
        border: none;
        padding: 0.5rem;
        margin: 0 0.5rem;
        cursor: pointer;
    }

    button:disabled {
        opacity: 0.5;
        cursor: not-allowed;
    }

    button.active {
        background-color: var(--accent-color);
        color: white;
    }

    .active {
        background-color: var(--accent-color);
    }
    `,
})
export class PaginableComponent {
  @Input() items: any[] = [];
  @Input() itemsPerPage: number = 10;
  @Input() currentPage: number = 1;

  get totalPages(): number {
    return Math.ceil(this.items.length / this.itemsPerPage);
  }

  get visibleItems(): any[] {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    const end = start + this.itemsPerPage;
    return this.items.slice(start, end);
  }

  get hasPrevious(): boolean {
    return this.currentPage > 1;
  }

  get hasNext(): boolean {
    return this.currentPage < this.totalPages;
  }

  previous(): void {
    if (this.hasPrevious) {
      this.currentPage--;
    }
  }

  next(): void {
    if (this.hasNext) {
      this.currentPage++;
    }
  }

  goTo(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }

  getPages(): number[] {
    return new Array(this.totalPages);
  }

  getClass(page: number): string {
    return page + 1 === this.currentPage ? 'active' : '';
  }
}
