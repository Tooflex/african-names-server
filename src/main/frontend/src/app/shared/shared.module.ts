import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NbButtonModule } from '@nebular/theme';
import { FormsModule } from '@angular/forms';
import { ImportFormComponent } from './import-form/import-form.component';



@NgModule({
  declarations: [
    ImportFormComponent
  ],
  imports: [
    CommonModule,
    NbButtonModule,
    FormsModule
  ],
  exports: [
    ImportFormComponent
  ]
})
export class SharedModule { }
