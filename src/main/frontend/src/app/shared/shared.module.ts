import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbButtonModule } from '@nebular/theme';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ImportFormComponent } from './import-form/import-form.component';
import { NgxFilterDelayComponent } from './ngx-filter-delay/ngx-filter-delay.component';



@NgModule({
  declarations: [
    ImportFormComponent,
    NgxFilterDelayComponent
  ],
    imports: [
        CommonModule,
        NbButtonModule,
        FormsModule,
        ReactiveFormsModule
    ],
  exports: [
    ImportFormComponent
  ]
})
export class SharedModule { }
