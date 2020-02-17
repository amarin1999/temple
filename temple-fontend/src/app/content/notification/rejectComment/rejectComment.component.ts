import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-rejectComment',
  templateUrl: './rejectComment.component.html',
  styleUrls: ['./rejectComment.component.scss']
})
export class RejectCommentComponent implements OnInit {
  @Input() display: boolean;
  @Output() CloseDialog = new EventEmitter();
  @Output() rejectComment = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }
  onClose() {
    this.CloseDialog.emit(false);

  }
  onAccept(e: HTMLInputElement) {
    console.log(e.value);
    // this.CloseDialog.emit(false);
    this.rejectComment.emit(e.value);

  }
  // console.log(this.detail);
  // const { specialApproveId, courseId } = this.detail;
  // detailSend = { spaId: [specialApproveId], courseId, status };
  // console.log(detailSend);
}
