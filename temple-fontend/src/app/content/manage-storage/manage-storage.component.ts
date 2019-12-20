import { Component, OnInit } from '@angular/core';
import { Baggage } from 'src/app/shared/interfaces/baggage';
import { BaggageService } from 'src/app/shared/service/baggage.service';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { AuthService } from 'src/app/shared/service/auth.service';
import { MenuItem, ConfirmationService, Message, MessageService } from 'primeng/api';
import { ManageUserService } from 'src/app/shared/service/manage-user.service';
import { LoginComponent } from 'src/app/auth/login/login.component';

@Component({
  selector: 'app-manage-storage',
  templateUrl: './manage-storage.component.html',
  styleUrls: ['./manage-storage.component.scss']
})
export class ManageStorageComponent implements OnInit {

  displayDialog: boolean;
  items: Baggage[];
  newBaggage: boolean;
  baggage: Baggage;
  baggageNumber: String;
  cols: any[];
  baggageselect: Baggage;
  data: any;
  public role: string;
  public menu: MenuItem[];
  public members: any[];
  public numberOfLocker: any[];
  public numberSelected: any[];
  public selectedMember: any;
  public selectedNumber: any;
  public selectedStatus: any;
  // public msgs: Message[] = [];
  public status = [
    { val: '1', label: 'ฝาก' },
    { val: '0', label: 'รับคืนแล้ว' }
  ];

  constructor(
    private baggageService: BaggageService,
    private breadCrumbService: BreadcrumbService,
    private authService: AuthService,
    private memberService: ManageUserService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
  ) {
  }

  ngOnInit() {

    this.initDialogData();
    this.getData();
    this.getItem();

    this.cols = [
      { field: 'createDate', header: 'วันที่', width: '16%' },
      { field: 'memberName', header: 'สมาชิก', width: '20%' },
      { field: 'locker', header: 'หมายเลขตู้' },
      { field: 'status', header: 'สถานะ' },
      { field: 'memberId', header: 'รหัสผู้ใช้' },
      { field: 'baggageId', header: 'รหัสสัมภาระ', width: '10%'}
    ];

    this.breadCrumbService.setPath([
      { label: 'จัดการสัมภาระ', routerLink: '/storage' }
    ]);

    this.authService.getRole().subscribe(res => this.role = res);
  }

  private initDialogData() {
    this.memberService.getAllUsers()
      .subscribe(res => {
        if (res['status'] === 'Success') {
          this.members = res['data'].map(res => {
            return {
              memberId: res['id'],
              memberName: res['titleName'] + res['fname'] + '  ' + res['lname']
            };
          });
        }
      },
        err => {
          console.log(err);

        }
      );

  }
  private getItem() {
    this.baggageService.getItem()
      .subscribe(
        res => {
          // console.log(res);
          if (res['status'] === 'Success') {
            this.numberOfLocker = res['data'].map(res => {
              return {
                number: res['locationName'] + '  ' + res['number'],
                lockerId: res['lockerId']
              };
            });
          }
        }
      );
  }

  private getData() {
    this.baggageService.getItems().subscribe(
      res => {
        if (res['status'] === 'Success') {
          this.items = res['data'];
        }
      },
      (e) => console.log(e['error']['message'])
    );
  }

  showEditButton(...role) {
    return role.includes(this.role);
  }

  showEdit(event) {
    this.baggage = { lockerNumber: '', baggageId: '' };
    this.newBaggage = false;
    this.displayDialog = true;
    this.data = {
      number: event.locker,
      lockerId: event.lockerId
    };
    this.numberOfLocker = [...this.numberOfLocker, this.data];
    this.selectedNumber = this.numberOfLocker.filter(res => res.lockerId === event.lockerId)[0];
    // console.log(this.numberOfLocker);
    this.selectedMember = this.members.filter(res => res.memberId === event.memberId)[0];
    // console.log(this.selectedMember);

    this.selectedStatus = {
      val: event.status,
      label: event.status === '1' ? 'ฝาก' : 'รับคืนแล้ว'
    };
    this.baggageselect = event;
  }

  delete(id) {
    const index = this.items.findIndex(e => e.baggageId === id);
    this.baggageService.delete(id).toPromise()
      .then(res => {
        if (res['status'] === 'Success') {
          this.items.splice(index, 1);
        }
      }).catch((e) => console.log(e['error']['message']));
  }

  save() {
    this.confirmationService.confirm({
      message: 'ยืนยันการบันทึก',
      header: 'ข้อความจากระบบ',
      // acceptLabel: 'ใช่',
      // rejectLabel: 'ไม่',
      accept: () => {
        // this.messageService.clear();
        this.displayDialog = false;
        const data = {
          memberId: this.selectedMember['memberId'],
          lockerId: this.selectedNumber['lockerId']
        };
        this.baggageService.saveStorage(data)
          .subscribe(res => {
            if (res['status'] === 'Success') {
              this.getData();
              this.getItem();
              this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการฝากสัมภาระสำเร็จ' });
            } else {
              this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการฝากสัมภาระไม่สำเร็จ' });
            }
          },
            (err) => {
              this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการฝากสัมภาระไม่สำเร็จ' });
            },
            () => {
              this.selectedMember = null;
              this.selectedNumber = null;
            }
          );
      }
    });
  }

  update() {
    this.confirmationService.confirm({
      message: 'ยืนยันการแก้ไข',
      header: 'ข้อความจากระบบ',
      acceptLabel: 'ใช่',
      rejectLabel: 'ไม่',
      accept: () => {
        this.displayDialog = false;
        const data = {
          memberId: this.selectedMember['memberId'],
          lockerId: this.selectedNumber['lockerId'],
          status: this.selectedStatus['val']
        };
        this.baggageService.updateStorage(this.baggageselect.baggageId, data).subscribe(res => {

          if (res['status'] === 'Success') {
            this.clear();
            this.getData();
            this.getItem();
            this.messageService.add({
              severity: 'success', summary: 'ข้อความจากระบบ',
              detail: (res['data']['status'] === 1 ? 'แก้ไข' : 'คืน') + 'สัมภาระสำเร็จ'
            });
          } else {
            this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'แก้ไขสัมภาระไม่สำเร็จ' });
          }
        },
          err => {
            this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'แก้ไขสัมภาระไม่สำเร็จ' });
          }
        );
      }
    });
  }

  clear() {
    if (!this.newBaggage) {
      const index = this.numberOfLocker.findIndex(res => res === this.data);
      this.numberOfLocker = [
        ...this.numberOfLocker.slice(0, index),
        ...this.numberOfLocker.slice(index + 1)
      ];
    }
    // console.log(this.numberOfLocker);
    this.selectedMember = null;
    this.selectedNumber = null;
    this.selectedStatus = null;
    this.displayDialog = false;
  }


  showDialogToAdd() {
    // console.log(this.numberOfLocker);
    this.newBaggage = true;
    this.baggage = { lockerNumber: '', baggageId: '' };
    this.displayDialog = true;
    this.selectedMember = null;
    this.selectedNumber = null;
  }
}

