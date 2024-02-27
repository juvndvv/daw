import { Routes } from '@angular/router';
import { ComprarPage } from './pages/comprar.component';
import { VenderPage } from './pages/vender.component';
import { ArticulosPage } from './pages/articulos.component';
import { PerfilPage } from './pages/perfil.component';
import { LoginPage } from './pages/login.component';
import { RegistroPage } from './pages/registro.component';
import { EdicionPage } from './pages/edicion.component';
import { AdministracionPage } from './pages/admin/administracion.component';
import { CreateCategoryComponent } from './pages/admin/create-category.component';
import { UpdateCategoryComponent } from './pages/admin/update-category.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/comprar',
    pathMatch: 'full',
  },
  {
    path: 'comprar',
    component: ComprarPage,
  },
  {
    path: 'vender',
    component: VenderPage,
  },
  {
    path: 'articulos',
    component: ArticulosPage,
  },
  {
    path: 'articulos/:id',
    component: EdicionPage,
  },
  {
    path: 'login',
    component: LoginPage,
  },
  {
    path: 'registro',
    component: RegistroPage,
  },
  {
    path: 'perfil',
    component: PerfilPage,
  },
  {
    path: 'dashboard',
    component: AdministracionPage,
  },
  {
    path: 'dashboard/newcategory',
    component: CreateCategoryComponent,
  },
  {
    path: 'dashboard/updatecategory/:id',
    component: UpdateCategoryComponent,
  },
];
