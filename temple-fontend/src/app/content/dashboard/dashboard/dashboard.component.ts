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
  courseHistory: any;

  transportOptions: {};
  genderOptions: {};
  regionOptions: {};
  courseHistoryOption: {};
  constructor(private dashBoard: DashboardService, private breadCrumbService: BreadcrumbService, private authService: AuthService) { }

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
      const sumGender = (res[0].gender.reduce((dataGender, sum) => sum + dataGender));
      const sumTransport = (res[0].transport.reduce((dataTransport, sum) => sum + dataTransport));
      const sumRegion = (res[0].region.reduce((dataRegion, sum) => sum + dataRegion));
      const sumCourse = (res[0].courseHistory.reduce((dataCourse, sum) => sum +dataCourse));

      this.gender = {
        labels: ['ชาย', 'หญิง', 'ไม่ระบุ'],
        datasets: [
          {
            data: res[0].gender,
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
        labels: ['การเดินทางของวัด' , 'การเดินทางมาด้วยตนเอง'],
        datasets: [
          {
            data: res[0].transport,
            backgroundColor: [
              '#42A5F5',
              '#9CCC65'
            ],
            hoverBackgroundColor: [
              '#42A5F5',
              '#9CCC65'
            ]
          }
        ]
      },
      this.region = {
        labels: [''],
        // 'กลาง', 'ตะวันออกเฉียงเหนือ', 'เหนือ', 'ใต้', 'ตะวันออก', 'ตะวันตก'
        datasets: [
          {
            label: 'กลาง',
            data: [res[0].region[0]],
            backgroundColor: '#ff6d6d'
          },
          {
            label: 'ตะวันออกเฉียงเหนือ',
            data: [res[0].region[1]],
            backgroundColor: '#fff766'
          },
          {
            label: 'เหนือ',
            data: [res[0].region[2]],
            backgroundColor: '#5cff8a'
          },
          {
            label: 'ใต้',
            data: [res[0].region[3]],
            backgroundColor: '#73fffa'
          },
          {
            label: 'ตะวันออก',
            data: [res[0].region[4]],
            backgroundColor: '#d16eff'
          },
          {
            label: 'ตะวันตก',
            data: [res[0].region[5]],
            backgroundColor: '#36A2EB'
          }
        ]
        },
      this.courseHistory = {
  labels: ['คอร์สที่ไม่ผ่านหลักสูตร (F)', 'คอร์สที่ผ่านหลักสูตร', 'คอร์สที่กำลังศึกษาอยู่'],
  datasets: [
    {
      data: res[0].courseHistory,
      backgroundColor: [
        '#ff6d6d',
        '#52DE22',
        '#F3EF18'
      ],
      hoverBackgroundColor: [
        '#ff6d6d',
        '#52DE22',
        '#F3EF18'
      ]
    }
  ]
};
this.transportOptions = {
  title: {
    display: true,
    text: `ช่องทางการเดินทางของผู้เข้าอบรม (ทั้งหมด ${sumTransport} คน)`,
    fontSize: 16,
    position: 'bottom'
  }
};


this.genderOptions = {
  title: {
    display: true,
    text: `เพศของผู้เข้าอบรม (ทั้งหมด ${sumGender} คน)`,
    fontSize: 16,
    position: 'bottom'
  }
};

this.regionOptions = {
  title: {
    display: true,
    text: `ภูมิภาคของผู้เข้าอบรม (ทั้งหมด ${sumRegion} คน)`,
    fontSize: 16,
    position: 'bottom'
  }
};
this.courseHistoryOption = {
  title: {
    display: true,
    text: `คอร์สทั้งหมดที่ทำการสมัครเรียน (ทั้งหมด ${sumCourse} คอร์ส)`,
    fontSize: 16,
    position: 'bottom'
  }
}
    });



  };

showDashboardRole(...role) {
  return role.includes(this.role);
}
  private getUserId() {
  this.userId = localStorage.getItem('userId');
}
  private getRole() {
  this.authService.getRole().subscribe(res => this.role = res);
}

}

