import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {AuthRoutingModule, routedComponents} from './auth-routing.module';
import {
  NbAlertModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbIconModule,
  NbInputModule,
  NbLayoutModule,
} from '@nebular/theme';
import {ReactiveFormsModule} from '@angular/forms';


@NgModule({
  imports: [
    CommonModule,
    AuthRoutingModule,
    NbInputModule,
    ReactiveFormsModule,
    NbAlertModule,
    NbIconModule,
    NbCheckboxModule,
    NbCardModule,
    NbButtonModule,
    NbLayoutModule,
  ],
  declarations: [
    ...routedComponents,
  ],
})
export class AuthModule { }
