import { Component, OnInit } from '@angular/core';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { FirebaseService } from 'src/app/shared/service/firebase.service';
import { Notifications } from 'src/app/shared/interfaces/notification';

@Component({
  selector: 'app-notification-detail',
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.scss']
})
export class NotificationDetailComponent implements OnInit {
  notices: Notifications[];
  constructor(private breadCrumbService: BreadcrumbService, private firebase: FirebaseService) { }

  ngOnInit() {
    this.setBreadCrumb();
    this.setDataFromFirebase();

  }

  private setBreadCrumb() {
    this.breadCrumbService.setPath([{ label: 'แจ้งเตือน', routerLink: '/notification' }]);
  }
  private setDataFromFirebase() {
    const userID = localStorage.getItem('userId');
    this.firebase.getDataNoticeByUserID(+userID).subscribe(res => {
    this.notices = res
      console.log(this.notices[0].notificationTime.toDate());
    }
    )
  }
}
