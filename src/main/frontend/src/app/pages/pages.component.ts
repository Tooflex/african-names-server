import { Component, OnInit } from '@angular/core';
import {MENU_ITEMS_ADMIN, MENU_ITEMS_MODERATOR, MENU_ITEMS_NOT_LOGGED} from './pages-menu';
import { TokenStorageService } from '../@core/services/token-storage.service';
import { NbMenuItem } from '@nebular/theme';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.scss']
})
export class PagesComponent implements OnInit {
  menu: NbMenuItem[] = [];

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
