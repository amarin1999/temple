import { Component, OnInit } from '@angular/core';
import { Course } from 'src/app/shared/interfaces/course';
import { CourseService } from '../shared/course.service';
import { LazyLoadEvent, ConfirmationService, MessageService } from 'primeng/api';
import { of, forkJoin } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { ManagePassCourseService } from 'src/app/shared/service/manage-pass-course.service';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';

import { ApiConstants } from 'src/app/shared/constants/ApiConstants';
import { HttpClient } from '@angular/common/http';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { formatDate } from '@angular/common';
import { Transportation } from 'src/app/shared/interfaces/transportation';
import { TransportService } from 'src/app/shared/service/transport.service';

@Component({
  selector: 'app-course-register-out-time',
  templateUrl: './course-register-out-time.component.html',
  styleUrls: ['./course-register-out-time.component.css']
})
export class CourseRegisterOutTimeComponent implements OnInit {

  public cols: any[];
  public courses: Course[];
  public specialApproveWait: Course[];
  public specialApproveSuccess: Course[];
  public totalRecords: number;
  public userPasscours: number;
  public displayDialog = false;
  public transportations: Transportation[];
  public courseId: number;
  public formOutTime: FormGroup;
  public filterTransport: any[];
  filteredTransportation: any[];
  id: any;
  name: any;

  // เอาไว้ใช้ใน calendar html 
  year = new Date().getFullYear();
  

  constructor(
    private breadCrumbService: BreadcrumbService,
    private courseService: CourseService,
    private managePassCourseService: ManagePassCourseService,
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private TransService: TransportService
  ) { }

  ngOnInit() {
    this.cols = [
      { field: 'stDate', header: 'วันที่' },
      { field: 'name', header: 'ชื่อคอร์ส' },
      { field: 'locationName', header: 'สถานที่' },
      { field: 'conditionMin', header: 'หมายเหตุ' },
    ];

    this.managePassCourseService.getNumberOfPassCourse(localStorage.getItem('userId'))
      .subscribe(res => this.userPasscours = res.data[0]);

    // this.http.get(ApiConstants.baseURl + `/transportations`)
    // .subscribe(res =>{
    //   console.log(res);
    //   this.transportations = [...res['data']];
    //   const tranId = [...res['data']];
    //  console.log(this.transportations);
    //  console.log(tranId);
    // } );
    this.breadCrumbService.setPath([
      { label: 'คอร์สนอกเวลา' },
    ]);
    this.getTranSport();
    this.getDataCanRegis();
    this.createForm();
    this.getDataSpecialApproveWait();
    this.getDataSpecialApproveSuccess();
  }
  // get การเดินทาง
  private getTranSport() {
    this.TransService.getTranSport().subscribe(res => {
      this.transportations = [...res];
    });
  }

  createForm() {
    this.formOutTime = this.formBuilder.group(
      {
        tranId: ['', Validators.required],
        date: ['', Validators.required],
        expected: ['',Validators.required],
        experience: ['',Validators.required],
        detail: ['', Validators.required]
      }
    );
  }
  private getDataCanRegis() {
    const allCourseOutTime = this.courseService.getCoursesOutTimeCanRegis('allCourseOutTime'); // res[0]
    const specialApproveCourse = this.courseService.getCoursesOutTimeCanRegis('Specialapprove'); // res[1]
    const memberHasCourse = this.courseService.getCoursesOutTimeCanRegis('MemberHasCourse'); // res[2]
    const memberHasOutTime = this.courseService.getCoursesOutTimeCanRegis('MemberHasOutTime'); // res[3]
    const memberHasToStudy = this.courseService.getCoursesOutTimeCanRegis('MemberHasToStudy'); // res[4]
    const temp = [];
    let check = false;
    temp.push(allCourseOutTime, specialApproveCourse, memberHasCourse, memberHasOutTime, memberHasToStudy);
    forkJoin(temp).subscribe(res => {
      if (res[0]['status'] === 'Success' && res[1]['status'] === 'Success' && res[2]['status'] === 'Success') {
        for (const dataAll of res[0]['data']) {
          if (res[1]['data'].length !== 0) {
            for (const dataSpa of res[1]['data']) {
              if (dataAll.id === dataSpa.id) {
                check = true;
                break;
              } else {
                check = false;
              }
            }
          }
          if (check === false) {
            if (res[2]['data'].length !== 0) {
              for (const dataMhc of res[2]['data']) {
                if (dataAll.id === dataMhc.id) {
                  check = true;
                  break;
                } else {
                  check = false;
                }
              }
            }
          }
          if (check === false) {
            if (res[3]['data'].length !== 0) {
              for (const dataMho of res[3]['data']) {
                if (dataAll.id === dataMho.no) {
                  check = true;
                  break;
                } else {
                  check = false;
                }
              }
            }
          }
          if (check === false) {
            if (res[4]['data'].length !== 0) {
              for (const dataMhs of res[4]['data']) {
                if (dataAll.id === dataMhs.no) {
                  check = true;
                  break;
                } else {
                  check = false;
                }
              }
            }
          }
          if (check === false) {
            dataAll.enable = true;
          } else {
            dataAll.enable = false;
          }
          check = false;
        }
        this.courses = res[0]['data'].filter(res => res.enable === true);
        console.log(this.courses);
        
      }
    });
  }

  showDisplay(id) {
    this.displayDialog = true;
    this.courseId = id;

    this.formOutTime.reset();
  }

  public assignCourseOutTime() {
    const tranId = this.formOutTime.get('tranId').value;
    const date = this.formOutTime.get('date').value;
    const outTimeCourse = {
      courseId: this.courseId,
      expected: this.formOutTime.get('expected').value,
      experience: this.formOutTime.get('experience').value,
      detail: this.formOutTime.get('detail').value,
      transportationId: tranId.id,
      stDate: formatDate(date[0], 'yyyy-MM-dd', 'en'),
      endDate: formatDate(date[1], 'yyyy-MM-dd', 'en'),
      date: date.map(res => formatDate(res, "yyyy-MM-dd", 'en')).sort()
    };

    this.confirmationService.confirm({
      message: 'ยืนยันการขออนุมัติคอร์สนอกเวลา',
      header: 'ข้อความจากระบบ',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.courseService.registerCourseOutTime(outTimeCourse).subscribe(res => {
          if (res.status === 'Success') {
            this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ขออนุมัตินอกเวลาสำเร็จ' });
            this.getDataCanRegis();
            this.getDataSpecialApproveWait();
            this.getDataSpecialApproveSuccess();
          } else {
            this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: res['errorMessage'] });
          }
        });
        this.displayDialog = false;
      },
      reject: () => { }
    });
  }

  private getDataSpecialApproveWait() {
    this.courseService.getSpecialApprove('Wait').subscribe(res => this.specialApproveWait = res['data']);
  }

  private getDataSpecialApproveSuccess() {
    this.courseService.getSpecialApprove('Success').subscribe(res => this.specialApproveSuccess = res['data']);
  }

  cancelApprove(courseId) {
    this.confirmationService.confirm({
      message: 'ยืนยันการยกเลิกขออนุมัติคอร์สนอกเวลา',
      header: 'ข้อความจากระบบ',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.courseService.cancelApproveOutTime(courseId).subscribe(res => {
          if (res.status === 'Success') {
            // console.log("ยกเลิกขออนุมัตินอกเวลาสำเร็จ");
            this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ยกเลิกขออนุมัตินอกเวลาสำเร็จ' });
            this.getDataCanRegis();
            this.getDataSpecialApproveWait();
            this.getDataSpecialApproveSuccess();
          } else {
            this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: res['errorMessage'] });
            // console.log(res);
          }
        });
      },
      reject: () => { }
    });
  }
  /**
   * รับค่าจากแป้นพิมพ์
   * @param event ;
   */

  filterTransportation(query, transportations: any[]): any[] {
    const filtered: any[] = [];
    for (let i = 0; i < transportations.length; i++) {
      const tranId = transportations[i];
      if (tranId.name.match(query)) {
        filtered.push(tranId);
      }
    }
    return filtered;
  }
  /**
     * เปรียบเทียบค่าที่ได้จากแป้นพิมพ์ กับ ค่าที่ได้จากดาต้าเบส
     * @param query ;
     * @param titleNames ;
     */
  filterTransportationMultiple(event) {
    const query = event.query;
    this.filteredTransportation = this.filterTransportation(query, this.transportations);
  }
}
