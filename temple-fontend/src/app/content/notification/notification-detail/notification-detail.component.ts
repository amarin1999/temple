import { Component, OnInit } from '@angular/core';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';

@Component({
  selector: 'app-notification-detail',
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.scss']
})
export class NotificationDetailComponent implements OnInit {

  constructor(private breadCrumbService: BreadcrumbService) { }

  ngOnInit() {
    this.setBreadCrumb();
  }

  private setBreadCrumb() {
    this.breadCrumbService.setPath([{ label: 'แจ้งเตือน', routerLink: '/notification' }]);
  }
}
