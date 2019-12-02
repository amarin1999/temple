import {Component, OnInit} from '@angular/core';
import {MenuItem, MessageService, ConfirmationService, LazyLoadEvent} from 'primeng/api';
import {ApprovalService} from '../approval.service';
import {BreadcrumbService} from '../../../shared/service/breadcrumb.service';
import {Course} from '../../../shared/interfaces/course';
import {of} from 'rxjs';
import {switchMap, tap} from 'rxjs/operators';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-course-approve',
  templateUrl: './list-course-approve.component.html',
  styleUrls: ['./list-course-approve.component.scss']
})
export class ListCourseApproveComponent implements OnInit {
  public cols: any[];
  public courses: Course[];
  public coursesOutTime: Course[];
  public menu: MenuItem[];
  public totalRecords: number;
  public totalRecordsOutTime: number;
  public loading: boolean;
  public loadingOutTime: boolean;
  public selectedCourse: Course;
  public courseId: string;

  constructor(
    private approvalService: ApprovalService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private breadCrumbService: BreadcrumbService,
    private router: Router,
  ) {
  }

  ngOnInit() {
    this.loading = true;
    this.loadingOutTime = true;
    this.setColumn();
    this.setBreadCrumb();
    this.getTotalRecord();
    this.getTotalRecordOutTime();
  }

  public loadData(e: LazyLoadEvent) {
    // console.log(e);
    let query = '';
    if (e.globalFilter) {
      query = e.globalFilter;
    }
    this.getData(e.first, e.rows, query);
  }

  public loadDataOutTime(e: LazyLoadEvent) {
    // console.log(e);
    let query = '';
    if (e.globalFilter) {
      query = e.globalFilter;
    }
    this.getDataOutTime(e.first, e.rows, query);
  }

  private setColumn() {
    this.cols = [
      {field: 'name', header: 'ชื่อคอร์ส'},
      {field: 'conditionMin', header: 'หมายเหตุ'},
      {field: 'detail', header: 'รายละเอียด'},
    ];
  }

  private setBreadCrumb() {
    this.breadCrumbService.setPath([
      {label: 'การอนุมัติ', routerLink: '/approval'},
    ]);
  }

  onRowSelect(e) {
    // console.log(e);
    this.router.navigateByUrl(`/approval/${e.data.id}?course=${e.data.name}&&type=InTime`);
  }

  onRowSelectOutTime(e) {
    // console.log(e);
    this.router.navigateByUrl(`/approval/${e.data.id}?course=${e.data.name}&&type=OutTime`);
  }

  private getData(first = 0, rows = 5, query: string = '') {
    this.loading = true;
    of([first, rows, query]).pipe(
      switchMap(([firstCon, rowsCon, queryCon]: [number, number, string]) =>
        this.approvalService.getCoursesApproval(firstCon, rowsCon, queryCon))
    ).subscribe(res => {
      // console.log(res);
      if (res['status'] === 'Success') {
        this.courses = res['data'];
        this.loading = false;
      }
    });
  }

  private getDataOutTime(first = 0, rows = 5, query: string = '') {
    this.loadingOutTime = true;
    of([first, rows, query]).pipe(
      switchMap(([firstCon, rowsCon, queryCon]: [number, number, string]) =>
        this.approvalService.getCoursesApprovalOutTime(firstCon, rowsCon, queryCon))
    ).subscribe(res => {
      // console.log(res);
      if (res['status'] === 'Success') {
        this.coursesOutTime = res['data'];
        this.loadingOutTime = false;
      }
    });
  }

  private getTotalRecord() {
    this.approvalService.getTotalRecord().subscribe(res => {
      if (res['status'] === 'Success') {
        this.totalRecords = res['data'][0]['totalRecord'];
      }
    });
  }

  private getTotalRecordOutTime() {
    this.approvalService.getTotalRecordOutTime().subscribe(res => {
      if (res['status'] === 'Success') {
        this.totalRecordsOutTime = res['data'][0]['totalRecord'];
      }
    });
  }
}
