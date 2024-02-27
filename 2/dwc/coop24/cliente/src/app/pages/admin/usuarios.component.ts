import { Component, inject } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../types/User';
import { UserMapper } from '../../mappers/user.mapper';
import { DataTableComponent } from '../../components/data-table.component';

@Component({
  selector: 'admin-usuarios',
  standalone: true,
  imports: [DataTableComponent],
  template: `
    <h1>Usuarios</h1>
    <data-table
      type="user"
      [columns]="['id', 'nombre', 'apellidos', 'email']"
      [data]="usuarios"
    >
    </data-table>
  `,
  styles: `
      h1 {
        margin-bottom: 20px;
      }
    `,
})
export class AdminUsuariosPage {
  private _userService: UserService = inject(UserService);
  private _userMapper: UserMapper = inject(UserMapper);

  usuarios: User[] = [];
  ngOnInit() {
    this._userService.getAll().then((users) => {
      this.usuarios = users.map((user) => this._userMapper.mapUser(user));
    });
  }
}
