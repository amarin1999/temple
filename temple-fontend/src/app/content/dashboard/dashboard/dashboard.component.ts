import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/shared/service/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  gender: any;
  transport: any;
  region: any;
  constructor(private dashBoard: DashboardService) { }

  ngOnInit() {
    this.dashBoard.getDataChart().subscribe(res => {
      console.log(res[0].transport);

      this.gender = {
        labels: ['ชาย', 'หญิง', 'ไม่ระบุ'],
        datasets: [
          {
            data: res[0].region,
            backgroundColor: [
              '#FF6384',
              '#36A2EB',
              '#FFCE56'
            ],
            hoverBackgroundColor: [
              '#FF6384',
              '#36A2EB',
              '#FFCE56'
            ]
          }]
      };
      this.transport = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
          {
            label: 'My First dataset',
            backgroundColor: '#42A5F5',
            borderColor: '#1E88E5',
            data: [65, 59, 80, 81, 56, 55, 40]
          },
          {
            label: 'My Second dataset',
            backgroundColor: '#9CCC65',
            borderColor: '#7CB342',
            data: [28, 48, 40, 19, 86, 27, 90]
          }
        ]
      }
      this.region = {
        labels: ['กลาง', 'ตะวันออกเฉียงเหนือ', 'เหนือ', 'ใต้', 'ตะวันออก', 'ตะวันตก'],
        datasets: [
          {
            data: res[0].region,
            backgroundColor: [
              '#FF6384',
              '#36A2EB',
              '#FF6384',
              '#36A2EB',
              '#FF6384',
              '#36A2EB',
            ],
            hoverBackgroundColor: [
              '#FF6384',
              '#36A2EB',
              '#FF6384',
              '#36A2EB',
              '#FF6384',
              '#36A2EB',
            ]
          }]
      }
    });

  }

}
