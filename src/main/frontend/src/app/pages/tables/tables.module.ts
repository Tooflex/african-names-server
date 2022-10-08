import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FirstnameTableComponent } from './firstname-table/firstname-table.component';
import { FirstnameTranslationTableComponent } from './firstname-translation-table/firstname-translation-table.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { TablesRoutingModule, routedComponents } from './tables-routing.module';
import { NbButtonModule, NbCardModule, NbIconModule, NbPopoverModule } from '@nebular/theme';
import {SharedModule} from "../../shared/shared.module";


@NgModule({
  declarations: [
    FirstnameTableComponent,
    FirstnameTranslationTableComponent,
    ...routedComponents,
  ],
  imports: [
    CommonModule,
    Ng2SmartTableModule,
    NbCardModule,
    NbPopoverModule,
    NbButtonModule,
    NbIconModule,
    TablesRoutingModule,
    SharedModule]
})
export class TablesModule { }
