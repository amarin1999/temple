import { Component, OnInit } from '@angular/core';
import { TransportService } from 'src/app/shared/service/transport.service';
import { ConfirmationService, Message, MessageService } from 'primeng/api';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { Transportation } from 'src/app/shared/interfaces/transportation';

@Component({
  selector: 'app-manage-transportation',
  templateUrl: './manage-transportation.component.html',
  styleUrls: ['./manage-transportation.component.scss']
})
export class ManageTransportationComponent implements OnInit {

  public displayDialog: boolean = false;
  public filterData: any[];
  public transport: Transportation[];
  public transportation: Transportation;
  public cols: any[];
  public displayTransportation: boolean = false;
  public newTransportation: string = '';
  // public msgs: Message[] = [];
  public temp : string ;

  constructor(
    private breadCrumbService: BreadcrumbService,
    private confirmationService: ConfirmationService,
    private transportationService: TransportService,
    private messageService: MessageService,
  ) { }

  ngOnInit() {
    this.breadCrumbService.setPath([
      { label: 'จัดการการเดินทางทั้งหมด', routerLink: '/manageTransportation' },
    ]);
    this.cols = [
      { field: 'name', header: 'การเดินทาง' }
    ];
    this.getTransportation();
    this.initTransportation();
  }

  showDialogToAdd() {
    this.displayTransportation = true;
    this.displayDialog = true;
    this.newTransportation = '';
  }

  getTransportation() {
    this.transportationService.getTranSportToEdit().subscribe(
      res => {
        if (res['status'] === 'Success') {
          this.transport = res['data'];
          this.filterData = res['data'];
        }
      }
    );

  }

  // public searchData(event) {
  //   if (!event) {
  //     this.filterData = this.transport;
  //   } else {
  //     this.filterData = this.transport.filter(res => {
  //       console.log(res['name'].trim().toLowerCase().includes(event.trim().toLowerCase()));
  //       return res['name'].trim().toLowerCase().includes(event.trim().toLowerCase());
  //     });
  //   }
  // }

  public save() {
    this.transportation.name = this.newTransportation;
    const checkArry = this.transport.filter(res=> res.name === this.transportation.name)
    if(checkArry.length !== 0){
      this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการเพิ่มไม่สำเร็จ เนื่องจากข้อมูลซ้ำ' });
      return;
    }
    //console.log(this.transportation.name);
    this.transportationService.createTransportation(this.transportation).subscribe(
      res => {
        if (res['status'] === 'Success') {
          // console.log(res);
          this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการเพิ่มการเดินทาง: '+res['data']['name']+' สำเร็จ' });
          // this.filterData = [
          //   this.filterData,
          //   res['data']
          // ]
          // this.transport = [
          //   this.transport,
          //   res['data']
          // ]
          this.getTransportation();
          this.initTransportation();
        }
        
      },
      (e) => {
        // console.log(e['error']['message'])
        // this.messageService.clear;
        this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการไม่สำเร็จ' });
      }
    );
    this.clear();
  }

  update() {
    // this.transportation.name = this.newTransportation;
    // console.log(this.transportation)
    if(this.filterData.findIndex(res=> res.name === this.newTransportation) < 0 || this.transportation.name === this.newTransportation ){
      this.transportation.name = this.newTransportation;
      this.transportationService.updateTransportation(this.transportation).subscribe(
        res => {
          if (res['status'] === 'Success') {
            this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการแก้ไขสำเร็จ' });
            const index = this.transport.findIndex(e => e.id === res['data']['id']);
  
            this.transport[index] = res['data'];
            this.filterData[index] = res['data'];
          }
        },
        (e) => {
          this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการแก้ไขไม่สำเร็จ' });
        }
      )
      this.clear();
    }else{
      // this.messageService.clear;
      this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการไม่สำเร็จ เนื่องจากข้อมูลซ้ำ' });
    } 
  }

  delete(id) {
    this.confirmationService.confirm({
      message: 'ยืนยันการลบข้อมูล : '+ this.filterData.filter(res=>res.id === id)[0].name ,
      header: 'ข้อความจากระบบ',
      accept: () => {
        this.transportationService.deleteTransportation(id)
          .subscribe(res => {
            if (res['status'] === 'Success') {
              this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการลบสำเร็จ' });
              this.getTransportation();
            }
          },
            (e) => {
              // console.log(e['error']['message']);
              this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการลบไม่สำเร็จ' });
            },
          );
      }
    })
    this.clear();
  }

  showEdit(id) {
    this.displayDialog = true;
    this.displayTransportation = false;
    this.transportation = this.filterData.filter(e => e.id === id)[0];
    // console.log(this.filterData.filter(e => e.id === id))
    this.newTransportation = this.transportation.name;
    this.temp = this.transportation.name;
  }


  clear() {
    this.initTransportation();
    this.newTransportation = '';
    this.messageService.clear();
  }
  private initTransportation() {
    this.displayDialog = false;
    this.transportation = {
      id: null,
      name: '',
      status: null
    }
  }

}