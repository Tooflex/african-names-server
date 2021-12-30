import {Component, OnInit} from '@angular/core';

import {MENU_ITEMS_ADMIN, MENU_ITEMS_MODERATOR, MENU_ITEMS_NOT_LOGGED} from './pages-menu';
import {TokenStorageService} from '../@core/services/token-storage.service';

@Component({
  selector: 'ngx-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <ngx-one-column-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})
export class PagesComponent implements OnInit {
  menu = [];

  constructor(private tokenStorageService: TokenStorageService) {

  }

  ngOnInit() {
    if (this.tokenStorageService.isLoggedIn()) {
      if (this.tokenStorageService.isAdmin()) {
        this.menu = MENU_ITEMS_ADMIN;
      } else if (this.tokenStorageService.isModerator()) {
        this.menu = MENU_ITEMS_MODERATOR;
      }
    } else {
      this.menu = MENU_ITEMS_NOT_LOGGED;
    }
  }
}
