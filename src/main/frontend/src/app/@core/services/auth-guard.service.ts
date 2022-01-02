/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { TokenStorageService } from './token-storage.service';

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(public authService: TokenStorageService, public router: Router) {}
  canActivate(): boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['auth/login']).then();
      return false;
    }
    return true;
  }
}
