import { Component, OnInit } from '@angular/core';

import { AngularFirestore } from '@angular/fire/firestore';
import { Observable } from 'rxjs/internal/Observable';

import { Router, NavigationEnd } from '@angular/router';
import { FirebaseService } from 'src/app/shared/service/firebase.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss'],
  providers: [AngularFirestore]
})
export class NotificationComponent implements OnInit {
  notiDisable: boolean = true;
  currentUrl: string;
  items: Observable<any[]>;
  // เรียก current path เพื่อซ่อนการแจ้งเตือน
  constructor(private router: Router, private fireBase: FirebaseService) {
    this.router.events.subscribe((_: NavigationEnd) => this.currentUrl = _.url);

  }

  ngOnInit() {
    this.fireBase.getNoticeData().subscribe(res => console.log(res)
    )

  }
  toggle() {
    this.notiDisable = false;
  }





}
