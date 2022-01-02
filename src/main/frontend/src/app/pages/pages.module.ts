import { NgModule } from '@angular/core';
import { NbMenuModule } from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { AnalyticsModule } from './analytics/analytics.module';
import { PagesRoutingModule } from './pages-routing.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import { SharedModule } from '../shared/shared.module';
import { AuthModule} from '../auth/auth.module';
import { ProfileComponent } from './profile/profile.component';
import { LandingPageComponent } from '../landing-page/landing-page.component';

@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    NbMenuModule,
    DashboardModule,
    AnalyticsModule,
    MiscellaneousModule,
    SharedModule,
    AuthModule,
  ],
  declarations: [
    PagesComponent,
    ProfileComponent,
    LandingPageComponent,
  ],
})
export class PagesModule {
}
