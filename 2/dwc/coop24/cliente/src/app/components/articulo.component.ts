import { Component, Input, Renderer2, inject } from '@angular/core';
import { Articulo, EstadoArticulo } from '../types/Articulo';
import { UserSignal } from '../signals/user.signal';
import { ArticulosService } from '../services/articulos.service';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { VentaDetalle } from '../types/Venta';
import { confirmSwal, fireSwal } from '../lib/swal';

@Component({
  selector: 'app-articulo',
  standalone: true,
  imports: [],
  template: ` <article>
      <img
        id="{{ articulo.id }}"
        [src]="articulo.img"
        [alt]="articulo.nombre"
      />
      <h3>{{ articulo.nombre }}</h3>
      <p class="money">{{ articulo.precio + ' €' }}</p>
      <div>
        @if( (_userSignal.userSignal().id === articulo.vendedor &&
        articulo.estado === 'D') ){
        <button (click)="borrarArticulo()" class="danger">Eliminar</button>
        <button (click)="modificarArticulo()" class="primary">Modificar</button>
        } @else if (_userSignal.userSignal().id === articulo.vendedor &&
        articulo.estado === 'V') {
        <button (click)="getInformacionVenta()" class="secondary">
          Vendido
        </button>
        } @else {
        <button (click)="comprarArticulo()" class="primary">Comprar</button>
        }
      </div>
    </article>
    @if (showDialog) { @if (articulo.estado === 'V') {
    <dialog>
      <h3>Información de la venta</h3>
      <p>Artículo: {{ ventaDetalle.articulo }}</p>
      <p>Comprador: {{ ventaDetalle.comprador }}</p>
      <p>Vendedor: {{ ventaDetalle.vendedor }}</p>
      <p>Fecha: {{ ventaDetalle.fecha }}</p>
      <p>Precio: {{ ventaDetalle.precio + ' €' }}</p>
    </dialog>
    } @else {
    <dialog>
      <img [src]="articulo.img" [alt]="articulo.nombre" />
      <h3>{{ articulo.nombre }}</h3>
      <p>{{ articulo.descripcion }}</p>
      <small>{{ articulo.precio + ' €' }}</small>
      @if(_userSignal.userSignal().id === articulo.vendedor && articulo.estado
      === 'D' ){
      <div>
        <button (click)="borrarArticulo()" class="danger">Eliminar</button>
        <button (click)="modificarArticulo()" class="primary">Modificar</button>
      </div>
      } @else {
      <button (click)="comprarArticulo()" class="primary">Comprar</button>
      }
    </dialog>
    }
    <div class="backdrop"></div>
    }`,
  styles: `

dialog {
  display: none;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 80%;
    max-width: 300px;
    padding: 2rem;
    background-color: white;
    border : 1px solid #ccc;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    gap: .6rem;
    z-index: 101;
  }

  .backdrop {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(0, 0, 0, 0.1);
    z-index: 100;
  }

  dialog img {
    width: 100%;
    height: auto;
    object-fit: cover;
}

dialog h3 {
  font-size: 1.2rem;
  font-weight: 500;
  text-align: center;
}

dialog p {
  text-align: left;
  text-wrap: pretty;
  font-size: 0.9rem;
  opacity: 0.8;
}
dialog small {
  font-size: 1.2rem;
  font-weight: 500;
  text-align: center;
  opacity: 0.8;
}

dialog div:has(button) {
  display: flex;
  justify-content: center;
  gap: .3rem;
}

  article {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: .6rem;
    padding: 1rem;
  }
    h3,
    p {
        text-align : center;
        font-size: 1rem;
        font-weight: 400;
        text-overflow: ellipsis;
    }
    h3 {
        font-weight: 500;
    }
    div {
        display: flex;
        gap: .3rem;
        justify-content: center;
    }
    button {
        padding: .4rem .6rem;
        font-size: .8rem;
        cursor: pointer;
    }
    img {
        height: 100px;
        width: 100px;
        object-fit: cover;
      cursor: pointer;
    }
    `,
})
export class ArticuloComponent {
  @Input() articulo: Articulo = {} as Articulo;
  _userSignal: UserSignal = inject(UserSignal);
  _articuloService: ArticulosService = inject(ArticulosService);
  _userService: UserService = inject(UserService);
  renderer: Renderer2 = inject(Renderer2);

  showDialog = false;

  toggleDialog() {
    if (this.articulo.estado === EstadoArticulo.Vendido) {
      this.getInformacionVenta();
    }
    this.showDialog = !this.showDialog;
  }

  ngAfterViewInit() {
    this.renderer.listen('window', 'click', (event) => {
      if (
        event.target !== document.querySelector('dialog') &&
        this.showDialog === true
      ) {
        this.toggleDialog();
      } else if (
        event.target === document.getElementById(this.articulo.id.toString())
      ) {
        this.toggleDialog();
      }
    });
  }

  borrarArticulo() {
    confirmSwal(
      '¿Estás seguro de que quieres eliminar este artículo?',
      'warning'
    ).then((result) => {
      if (result.isConfirmed) {
        this._articuloService.deleteArticulo(this.articulo.id).then(() => {
          location.reload();
        });
      }
    });
  }

  userSignal: UserSignal = inject(UserSignal);
  comprarArticulo() {
    confirmSwal('Por favor, confirme su compra', 'warning').then((result) => {
      if (result.isConfirmed) {
        this._articuloService
          .comprarArticulo(this.articulo.id, this._userSignal.userSignal().id)
          .then(() => {
            fireSwal('¡Compra realizada con éxito!', 'success', 1000);

            setTimeout(() => {
              location.reload();
            }, 1000);
          });
      }
    });
  }

  private _router: Router = inject(Router);
  modificarArticulo() {
    console.log('hola');
    // redirigir a la pagina de edicion
    this._router.navigate(['/articulos/' + this.articulo.id]);
  }

  ventaDetalle: VentaDetalle = {} as VentaDetalle;
  getInformacionVenta() {
    // Obtiene el detalle de la venta
    this._articuloService.getVentaById(this.articulo.id).then(async (venta) => {
      this.ventaDetalle.fecha = venta.fecha;
      this.ventaDetalle.precio = venta.precio;

      const comprador = await this._userService.getById(venta.idcomprador);
      this.ventaDetalle.comprador =
        comprador.nombre + ' ' + comprador.apellidos;

      const vendedor = await this._userService.getById(venta.idsocio);
      this.ventaDetalle.vendedor = vendedor.nombre + ' ' + vendedor.apellidos;

      const articulo = await this._articuloService.getArticuloById(
        venta.idarticulo
      );
      this.ventaDetalle.articulo = articulo[0].nombre;
    });
  }
}
