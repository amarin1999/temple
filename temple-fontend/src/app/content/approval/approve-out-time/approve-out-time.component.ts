import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-approve-out-time',
  templateUrl: './approve-out-time.component.html',
  styleUrls: ['./approve-out-time.component.scss']
})
export class ApproveOutTimeComponent implements OnInit {


  @Input('option') option;
  @Input('cols') cols;
  @Input('fieldId') fieldId;
  @Input('msgs') msgs;
  @Input() disbtn;
  @Input() detail;
  @Output() listData = new EventEmitter();
  rejectDialog: boolean;
  public status;
  public check: boolean;
  public checked = true;
  public courseId: string;
  public menusSelect = [
    {
      status: '1',
      menuName: 'อนุมัติ',
    },
    {
      status: '0',
      menuName: 'ไม่อนุมัติ',
    },
  ];

  public urlback: string;
  public messageback: string;
  constructor(
    private route: ActivatedRoute, private messageService: MessageService
  ) {
  }
  ngOnInit() {

    this.urlback = this.route.snapshot.data.urlback;
    this.messageback = 'กลับไปยังหน้า' + this.route.snapshot.data.messageback;
    this.courseId = this.route.snapshot.paramMap.get('id');
    this.status = {
      status: '1',
      menuName: 'อนุมัติ',
    };
    this.check = false;

  }
  showWhenApproveForm() {
    if (this.option === '1') {
      return false;
    } else if (this.option === '2') {
      return true;
    }
  }
  sentData(status = null) {
    // this.messageService.clear();
    console.log(status);

    this.check = false;
    let detailSend;
    // อนุมัติผ่านคอร์ส
    // '1' = pass '0' != pass
    if (this.option == '1') {
      detailSend = {
        mhcId: this.detail[this.fieldId],
        status: this.detail['checked'] ? '1' : ''
      };

      detailSend = {
        member: [...detailSend],
        courseId: this.courseId
      };
      console.log(detailSend);


      // อนุมัติพิเศษ
      // '1' = Approve '0' != ไม่Approve 
    } else if (this.option == '2') {
      if (status == 0) {
        this.rejectDialog = true;
      } else if (status == 1) {
        console.log(this.detail);
        const { specialApproveId, courseId } = this.detail;
        detailSend = { spaId: [specialApproveId], courseId, status };
        console.log(detailSend);
        if (detailSend) {
          this.listData.emit(detailSend);
        }
      }
    }

  }

  onDialogClose(event) {
    this.rejectDialog = event;
  }

  sendDataReject(e: string) {
    console.log(e);

    let detailSend;
    console.log(this.detail);
    const { specialApproveId, courseId } = this.detail;
    detailSend = { spaId: [specialApproveId], courseId, status: 0, rejectComment: e };
    console.log(detailSend);

    if (detailSend) {
      console.log(detailSend);
      
      this.listData.emit(detailSend);

    }
  }
}
