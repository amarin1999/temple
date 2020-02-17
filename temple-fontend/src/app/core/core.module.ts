import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { SidebarComponent } from './sidebar/sidebar.component';
import { TopbarComponent } from './topbar/topbar.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarService } from './service/sidebar.service';
import { ManageUserService } from '../shared/service/manage-user.service';

@NgModule({
  declarations: [
    SidebarComponent,
    TopbarComponent,
    FooterComponent
  ],
  imports: [
    SharedModule
  ],
  providers: [SidebarService, ManageUserService],
  exports: [
    SidebarComponent,
    TopbarComponent,
    FooterComponent
  ]
})
export class CoreModule { }
