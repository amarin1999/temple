import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Event, NavigationStart, NavigationEnd, NavigationError } from '@angular/router';
@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {
  notiDisable: boolean = true;
  currentUrl: string;

  // เรียก current path เพื่อซ่อนการแจ้งเตือน
  constructor(private router: Router) {
    this.router.events.subscribe((_: NavigationEnd) => this.currentUrl = _.url);
  }

  ngOnInit() {
  }
  toggle() {
    this.notiDisable = false;
  }





}
