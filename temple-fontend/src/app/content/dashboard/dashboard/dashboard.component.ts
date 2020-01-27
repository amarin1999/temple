import { Component, OnInit } from '@angular/core';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  data: any;
  constructor(    private breadCrumbService: BreadcrumbService,
    ) { }

  ngOnInit() {
    this.data = {
      labels: ['A', 'B', 'C'],
      datasets: [
        {
          data: [300, 50, 100],
          backgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56"
          ],
          hoverBackgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56"
          ]
        }]
    };
    this.setBreadCrumb();
  }

  setBreadCrumb(){
    this.breadCrumbService.setPath([{ label: 'สรุปผล', routerLink: '/dashboard' }]);
  }

}
