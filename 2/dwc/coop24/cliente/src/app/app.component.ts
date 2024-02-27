import { Component, OnInit, inject } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header.component';
import { UserSignal } from './signals/user.signal';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent],
  template: `
    <app-header></app-header>
    <router-outlet></router-outlet>
  `,
  styles: `
  `,
})
export class AppComponent implements OnInit {
  private _router: Router = inject(Router);
  private _userSignal: UserSignal = inject(UserSignal);

  ngOnInit(): void {
    if (!this._userSignal.userSignal().id) {
      this._router.navigate(['/login']);
    }
  }
}
