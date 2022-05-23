import { Component, OnDestroy, OnInit } from '@angular/core';
import {TokenStorageService} from '../../@core/services/token-storage.service';
import {AuthResourceService} from '../../api/services';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import { JwtResponse, LoginRequest } from 'src/app/api/models';
import { Subject, takeUntil } from 'rxjs';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  submitted = false;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  private destroy$: Subject<void> = new Subject<void>();

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthResourceService,
    private tokenStorage: TokenStorageService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }

    this.form = this.formBuilder.group(
      {
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
          ],
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
          ],
        ],
      },
    );
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    let loginRequest: LoginRequest = {
      username: this.form.value.username,
      password: this.form.value.password,
    };

    this.authService.authenticateUser({ body: loginRequest })
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => {
          const mData: JwtResponse = data;
          if (mData.jwt !== undefined) {
            this.tokenStorage.saveToken(mData.jwt);
            this.tokenStorage.saveUser(mData);
            this.isLoginFailed = false;
            this.isLoggedIn = true;
            this.roles = this.tokenStorage.getUser().roles;
            this.router.navigate(['/pages']);
          }

        },
        error: (err) => {
          this.errorMessage = err;
          this.isLoginFailed = true;
        },
      })
  }

  reloadPage(): void {
    window.location.reload();
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}
