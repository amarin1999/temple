import { Component, OnInit, Input, Output } from '@angular/core';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { MemberApproval } from 'src/app/shared/interfaces/member-approval';
import { ActivatedRoute } from '@angular/router';
import { ApprovalService } from '../approval.service';

import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-approval-form',
  templateUrl: './approval-form.component.html',
  styleUrls: ['./approval-form.component.scss']
})
export class ApprovalFormComponent implements OnInit {

  @Input() option: String;
  @Input() member: MemberApproval[];
  @Input() cols: any[];
  @Input() fieldId: string;
  @Input() course: any[];
  @Output() listData;
  // @Input() msgs: Message[] = [];
  public courseId: string;
  public nameCourse: string;
  public btnrej: boolean;
  public courseType: String;
  constructor(
    private breadCrumbService: BreadcrumbService,
    private route: ActivatedRoute,
    private approvalService: ApprovalService,
    private confirmationService: ConfirmationService,
    private messageServise: MessageService
  ) { }

  ngOnInit() {
    this.option = '2';
    this.fieldId = 'specialApproveId';
    this.courseId = this.route.snapshot.paramMap.get('id');
    this.courseType = this.route.snapshot.queryParamMap.get('type');
    this.initMember();
    this.nameCourse = this.route.snapshot.queryParamMap.get('course');
    this.setBreadCrumb();

    this.cols = [
      { field: 'displayName', header: 'ชื่อ-นามสกุล' },
      { field: 'transportationName', header: 'การเดินทาง' },
      { field: 'detail', header: 'คำขออนุมัติพิเศษ' },
      { field: 'checked', header: '' }
    ];

    // console.log(this.courseType);
    
  }
  setBreadCrumb() {
    if (this.courseType === 'OutTime') {
      this.breadCrumbService.setPath([{ label: 'การอนุมัตินอกเวลา', routerLink: '/approvalCourseOutTime' }, { label: 'อนุมัติผู้เรียนนอกเวลา' }]);
    } else {
      this.breadCrumbService.setPath([{ label: 'การอนุมัติพิเศษ', routerLink: '/approval' }, { label: 'อนุมัติผู้เรียนพิเศษ' }]);
    }

  }
  initMember() {
    // console.log(this.courseType)
    if (this.courseType === 'OutTime') {
      this.approvalService.getMemberForApproveOutTime(+this.courseId)
        .subscribe(res => {
          if (res['status'] === 'Success') {
            // console.log(res);
            this.member = res['data'];
            if (this.member.length === 0) {
              this.member = [{ displayName: "ไม่มีข้อมูล" }]
            }
          }
        });
    } else {
      this.approvalService.getMemberForApprove(+this.courseId)
        .subscribe(res => {
          if (res['status'] === 'Success') {
            this.member = res['data'];
            if (this.member.length === 0) {
              this.member = [{ displayName: "ไม่มีข้อมูล" }]
            }
          }
        });
    }
  }

  showDialog(e) {
    this.btnrej = true;
    const message = e.status == '1' ? '' : 'ไม่';
    this.confirmationService.confirm({
      message: message + 'ต้องการอนุมัตพิเศษ',
      header: 'การอนุมัติพิเศษ',
      accept: () => {
        this.approvalService.approveStudents(e)
          .subscribe((res) => {
            // console.log(res);
            if (res['status'] === 'Success') {
              this.initMember();
              this.messageServise.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการ' + message + 'อนุมัติพิเศษสำเร็จ' });
            } else {
              this.btnrej = false;
              this.messageServise.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการ' + message + 'อนุมัติพิเศษไม่สำเร็จ' });
            }
          });
      },
      reject: () => {
        this.btnrej = false;
        this.messageServise.add({ severity: 'info', summary: 'ข้อความจากระบบ', detail: 'ยกเลิกการ' + message + 'อนุมัติพิเศษ' });
      }
    });
  }

}
