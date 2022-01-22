import { NgModule } from '@angular/core';
import { NbLayoutModule, NbMenuModule, NbSidebarModule } from '@nebular/theme';

import { PagesComponent } from './pages.component';
import { AnalyticsModule } from './analytics/analytics.module';
import { SharedModule } from '../shared/shared.module';
import { AuthModule} from '../auth/auth.module';
import { ProfileComponent } from './profile/profile.component';
import { LandingPageComponent } from '../landing-page/landing-page.component';
import {LottieModule} from "ngx-lottie";
import { PagesRoutingModule } from './pages-rounting.module';

@NgModule({
    imports: [
      PagesRoutingModule,
      NbMenuModule,
      NbLayoutModule,
      NbSidebarModule,
      AnalyticsModule,
      SharedModule,
      AuthModule,
      LottieModule,
    ],
  declarations: [
    PagesComponent,
    ProfileComponent,
    LandingPageComponent,
  ],
})
export class PagesModule {
}
