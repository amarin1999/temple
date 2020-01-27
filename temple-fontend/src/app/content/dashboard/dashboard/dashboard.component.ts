import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/shared/service/dashboard.service';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { AuthService } from 'src/app/shared/service/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  gender: any;
  transport: any;
  region: any;
  userId: string;
  role: string;
  transportOptions: {};
  genderOptions: {};
  regionOptions: {};
  constructor(private dashBoard: DashboardService, private breadCrumbService: BreadcrumbService,private authService: AuthService) { }

  ngOnInit() {
    this.setDataChart();
    this.setBreadCrumb();
    this.getUserId();
    this.getRole();
  }
  private setBreadCrumb() {
    this.breadCrumbService.setPath([{ label: 'สรุปผลข้อมูล', routerLink: '/dashboard' }]);
  }
  private setDataChart() {
    this.dashBoard.getDataChart().subscribe(res => {
      console.log([res[0].transport[0]]);

      this.gender = {
        labels: ['ชาย', 'หญิง', 'ไม่ระบุ'],
        datasets: [
          {
            data: res[0].region,
            backgroundColor: [
              '#36A2EB',
              '#FF6384',
              '#FFCE56'
            ],
            hoverBackgroundColor: [
              '#36A2EB',
              '#FF6384',
              '#FFCE56'
            ]
          }]
      };
      this.transport = {
        labels: [''],
        datasets: [
          {
            label: 'การเดินทางของวัด',
            backgroundColor: '#42A5F5',
            borderColor: '#1E88E5',
            data: [res[0].transport[0]]
          },
          {
            label: 'การเดินทางของวัด',
            backgroundColor: '#9CCC65',
            borderColor: '#7CB342',
            data: [res[0].transport[1]]
          }
        ]
      }
      this.region = {
        labels: ['กลาง', 'ตะวันออกเฉียงเหนือ', 'เหนือ', 'ใต้', 'ตะวันออก', 'ตะวันตก'],
        datasets: [
          {
            data: res[0].region,
            backgroundColor: [
              '#ff6d6d',
              '#fff766',
              '#5cff8a',
              '#73fffa',
              '#d16eff',
              '#36A2EB',
            ],
            hoverBackgroundColor: [
              '#ff6d6d',
              '#fff766',
              '#5cff8a',
              '#73fffa',
              '#d16eff',
              '#36A2EB',
            ]
          }]
      }
    });

    this.transportOptions = {
      title: {
        display: true,
        text: 'ช่องทางการเดินทางของผู้เข้าอบรม',
        fontSize: 16,
        position: 'bottom'
      }
    }


    this.genderOptions = {
      title: {
        display: true,
        text: 'เพศของผู้เข้าอบรม',
        fontSize: 16,
        position: 'bottom'
      }
    }

    this.regionOptions = {
      title: {
        display: true,
        text: 'ภูมิภาคของผู้เข้าอบรม',
        fontSize: 16,
        position: 'bottom'
      }
    }


  };

  showDashboardRole(...role) {
    return role.includes(this.role);
  }
  private getUserId(){
    this.userId = localStorage.getItem('userId');
  }
  private getRole(){
    this.authService.getRole().subscribe(res => this.role = res);
  }

  private getCourseChart(){
    this.dashBoard.getDataChart().subscribe(res => {
      // console.log(res[4].courseHistory);
     });
  }
  
}

