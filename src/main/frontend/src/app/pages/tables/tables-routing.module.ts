import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TablesComponent } from './tables.component';
import { FirstnameTableComponent } from './firstname-table/firstname-table.component';
import { FirstnameTranslationTableComponent } from './firstname-translation-table/firstname-translation-table.component';

const routes: Routes = [{
  path: '',
  component: TablesComponent,
  children: [
    {
      path: 'firstnames',
      component: FirstnameTableComponent,
    },
    {
      path: 'firstname-translations',
      component: FirstnameTranslationTableComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TablesRoutingModule { }

export const routedComponents = [
  TablesComponent,
  FirstnameTableComponent,
  FirstnameTranslationTableComponent,
];
