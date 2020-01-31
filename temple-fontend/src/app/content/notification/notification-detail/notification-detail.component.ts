import { Component, OnInit, OnChanges } from '@angular/core';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { FirebaseService } from 'src/app/shared/service/firebase.service';
import { Notifications } from 'src/app/shared/interfaces/notification';
import { AuthService } from 'src/app/shared/service/auth.service';
import { Observable } from 'rxjs/internal/Observable';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notification-detail',
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.scss']
})
export class NotificationDetailComponent implements OnInit {
  notices: Observable<Notifications[]>;
  role: string;
  courseId: number;
  userID: string;
  constructor(private breadCrumbService: BreadcrumbService, private firebase: FirebaseService,
    private authService: AuthService, private router: Router
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
    this.userID = localStorage.getItem('userId');
    this.notices = this.firebase.getDataNoticeByUserID(+this.userID);

  }
  showRole(...role) {
    return role.includes(this.role);
  }

  private getRole() {
    this.authService.getRole().subscribe(res => this.role = res);
  }

  openSpecailCourse(e) {
    if (e.specialApproveStatus === '4') {
      this.router.navigateByUrl(`/approvalCourseOutTime/${e.courseID}?course=${e.detail}&&type=OutTime`);
      // console.log(e.courseID);
    } else {
      this.router.navigateByUrl(`/approval/${e.courseID}?course=${e.detail}&&type=InTime`);
    }

    this.updateNotification(e);
  }
  getCourseDetail(e){
    this.router.navigateByUrl(`/courses/${e.courseID}`);

    this.updateNotification(e);
  }

  private updateNotification(notification : Notifications) {
    this.firebase.updateNotification(notification);
  }
}
