import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ImportFormComponent } from './components/import-form/import-form.component';
import { ThemeModule } from '../@theme/theme.module';



@NgModule({
  declarations: [
    ImportFormComponent,
  ],
  imports: [
    CommonModule,
    ThemeModule,
  ],
})
export class SharedModule { }
