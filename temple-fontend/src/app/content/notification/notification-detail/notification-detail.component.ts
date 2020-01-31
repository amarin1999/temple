import { Component, OnInit, OnChanges } from '@angular/core';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { FirebaseService } from 'src/app/shared/service/firebase.service';
import { Notifications } from 'src/app/shared/interfaces/notification';
import { AuthService } from 'src/app/shared/service/auth.service';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-notification-detail',
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.scss']
})
export class NotificationDetailComponent implements OnInit {
  notices: Observable<Notifications[]>;
  role: string;
  constructor(private breadCrumbService: BreadcrumbService, private firebase: FirebaseService,
    private authService: AuthService
  ) {
    this.setBreadCrumb();
    this.setDataFromFirebase();
    this.getRole();
  }

  ngOnInit() {
    this.showRole();


  }

  private setBreadCrumb() {
    this.breadCrumbService.setPath([{ label: 'แจ้งเตือน', routerLink: '/notification' }]);
  }
  private setDataFromFirebase() {
    const userID = localStorage.getItem('userId');
    this.notices = this.firebase.getDataNoticeByUserID(+userID);
  }
  showRole(...role) {
    return role.includes(this.role);
  }

  private getRole() {
    this.authService.getRole().subscribe(res => this.role = res);
  }
}
