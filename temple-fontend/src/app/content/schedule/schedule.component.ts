import { Component, OnInit } from '@angular/core';
import { ScheduleService } from 'src/app/shared/service/schedule.service';
import { BreadcrumbService } from '../../shared/service/breadcrumb.service';
import { AuthService } from 'src/app/shared/service/auth.service';
import { DatePipe } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {
  events: any[];
  newevents: any[];
  options: any;
  title: String;
  date: Date;
  newEndDate: any;
  newStartDate: any;
  status: any;
  constructor(
    private scheduleService: ScheduleService,
    private breadCrumbService: BreadcrumbService,
    private authService: AuthService,
    public spinner: NgxSpinnerService
  ) {

  }

  ngOnInit() {
    if (this.authService.getRole().value === 'monk') {
      this.title = 'ตารางสอน';
      this.loadDataForMonk();
    } else if (this.authService.getRole().value === 'user') {
      this.title = 'ตารางเรียน';
      this.loadData();
    }
    this.setPath();
    this.setOption();
  }

  private setPath() {
    this.breadCrumbService.setPath([
      { label: `${this.title}` }
    ]);
  }

  private loadData() {
    // this.date.setDate( this.date.getDate() + 3 );
    this.spinner.show();
    this.scheduleService.getSchedule().toPromise()
      .then(res => {
        if (res['status'] === 'Success') {
          res['data'].forEach(element => {
            // เซทค่าวันสุดท้ายใหม่ บวกเพิ่ม 1 วัน ในปฏิทิน
            this.date = new Date(element.end);
            this.date.setDate((this.date.getDate()) + 1);
            this.newEndDate = new DatePipe('en-En').transform(this.date, 'yyyy-MM-dd');
            this.newStartDate = new DatePipe('en-En').transform(element.start, 'yyyy-MM-dd');
            element.end = this.newEndDate;
            element.start = this.newStartDate;
          });
          this.events = res['data'];
          /*เปล่ยนสี events ตาม การเรียนคอร์สนอกเวลา และ ในเวลาปกติ
          */
          this.events = this.events.map(data => {
            if (data.courseStatus === '1') {
              return { ...data, color: '#2E8B57' };
            } else {
              return { ...data, color: '#e68a00' };
            }
          });
        }
      }).catch(err => {
        console.log('Error', err);
      }
      ).finally(() => this.spinner.hide());
  }

  private loadDataForMonk() {
    this.spinner.show();
    this.scheduleService.getScheduleForMonk().toPromise()
      .then(res => {
        if (res['status'] === 'Success') {
          res['data'].forEach(element => {
            // เซทค่าวันสุดท้ายใหม่ บวกเพิ่ม 1 วัน ในปฏิทิน
            this.date = new Date(element.end);
            this.date.setDate((this.date.getDate()) + 1);
            this.newEndDate = new DatePipe('en-En').transform(this.date, 'yyyy-MM-dd');
            this.newStartDate = new DatePipe('en-En').transform(element.start, 'yyyy-MM-dd');
            element.end = this.newEndDate;
            element.start = this.newStartDate;

          });
          this.events = res['data'];
          /*เปล่ยนสี events ตาม การเรียนคอร์สนอกเวลา และ ในเวลาปกติ
         */
          this.events = this.events.map(data => {
            if (data.courseStatus === '1') {
              return { ...data, color: '#2E8B57' };
            } else if (data.courseStatus === '0') {
              return { ...data, color: '#e68a00' };
            }
          });

          // console.log(this.status);


          // console.log(this.events);
        }
      }).catch(err => {
        console.log('Error', err);
      }).finally(() => this.spinner.hide());
  }

  public setOption() {
    // if (this.status === '1') {
    this.options = {
      header: {
        left: 'prev,today,next',
        center: 'title',
        right: 'month,agendaWeek'
      },
      locale: 'th',

      buttonText: {
        prev: 'ย้อน',
        next: 'ถัดไป',
        today: 'วันนี้',
        month: 'เดือน',
        week: 'สัปดาห์',
        day: 'วัน',
        list: 'แผนงาน'
      },

      // eventColor: '#378006',
      allDayText: 'ตลอดวัน',
      eventLimitText: 'เพิ่มเติม',
      noEventsMessage: 'ไม่มีกิจกรรมที่จะแสดง'
      //   };
      // } else if (this.status === '0') {
      //   this.options = {
      //     header: {
      //       left: 'prev,today,next',
      //       center: 'title',
      //       right: 'month,agendaWeek'
      //     },
      //     locale: 'th',
      //     buttonText: {
      //       prev: 'ย้อน',
      //       next: 'ถัดไป',
      //       today: 'วันนี้',
      //       month: 'เดือน',
      //       week: 'สัปดาห์',
      //       day: 'วัน',
      //       list: 'แผนงาน'
      //     },
      //     eventColor: '#007ad9',
      //     allDayText: 'ตลอดวัน',
      //     eventLimitText: 'เพิ่มเติม',
      //     noEventsMessage: 'ไม่มีกิจกรรมที่จะแสดง'
      //   };
    }
  }

}
