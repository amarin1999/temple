import { Component, OnInit } from '@angular/core';
import { Baggage } from '../../shared/interfaces/baggage';
import { BaggageService } from '../../shared/service/baggage.service';
import { MenuItem, ConfirmationService, MessageService } from 'primeng/api';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { AuthService } from 'src/app/shared/service/auth.service';
import { LocationService } from '../location/location.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Location } from 'src/app/shared/interfaces/location';

@Component({
  selector: 'app-baggages',
  templateUrl: './baggages.component.html',
  styleUrls: ['./baggages.component.css']
})
export class BaggagesComponent implements OnInit {
  displayDialog: boolean;
  items: any[];
  newBaggage: boolean;
  baggage: Baggage;
  lockerId: String;
  baggageNumber: String;
  cols: any[];
  location: Location;
  locationName: String;
  locations: any[];
  number: Number;
  public filteredLocation: any[];
  public role: string;
  public menu: MenuItem[];
  public formEdit: FormGroup;
  constructor(
    private baggageService: BaggageService,
    private breadCrumbService: BreadcrumbService,
    private authService: AuthService,
    private locationService: LocationService,
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
  }

  ngOnInit() {
    this.getData();
    this.locationService.getLocation().subscribe(
      res => {
        if (res.status == 'Success') {
          this.locations = res.data;
        }
      },
      error => {
        console.log(error['error']['message']);
      }
    )
    this.cols = [
      { field: 'number', header: 'หมายเลขตู้' },
      { field: 'locationName', header: 'สถานที่' },
    ];

    this.breadCrumbService.setPath([
      { label: 'จัดการตู้สัมภาระ', routerLink: '/baggages' }
    ]);

    this.authService.getRole().subscribe(res => this.role = res);
  }

  private getData() {
    this.baggageService.getItemAll().subscribe(
      res => {
        if (res['status'] === 'Success') {
          this.items = res['data'];
          this.baggage = res['data']
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
    this.number = this.items.findIndex(res => res.lockerId === event.lockerId);
    this.newBaggage = false;
    this.baggage = this.items.filter(e => e.lockerId === event.lockerId)[0];
    this.lockerId = this.baggage['lockerId']
    this.baggageNumber = this.baggage['number'];
    this.location = {
      id: +this.baggage['locationId'],
      name: this.baggage['locationName']
    };
    this.displayDialog = true;
  }

  delete(event) {
    this.confirmationService.confirm({
      message: 'ยืนยันการลบ',
      header: 'ข้อความจากระบบ',
      // acceptLabel: 'ยืนยัน',
      // rejectLabel: 'ยกเลิก',
      accept: () => {
        // this.messageService.clear()
        const index = this.items.findIndex(e => e.lockerId === event.lockerId);
        this.baggageService.delete(event.lockerId).toPromise()
          .then(res => {
            if (res['status'] === 'Success') {
              this.items.splice(index, 1);
              this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการลบตู้สัมภาระสำเร็จ' });
              this.getData();
            } else {
              this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการลบตู้สัมภาระไม่สำเร็จ' });
            }
          }).catch((e) => console.log(e['error']['message']));
      },
      reject: () => {

      }
    });
  }

  save() {
    if (this.items.findIndex(res => res.number === this.baggageNumber && res.locationId === this.location['id']) < 0) {
      // this.messageService.clear();
      const data = {
        number: this.baggageNumber,
        locationId: this.location['id']
      };
      // this.messageService.clear()
      this.baggageService.save(data).toPromise().then(res => {
        if (res['status'] === 'Success') {
          this.items=[...this.items,res['data'][0]]
          this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการเพิ่มตู้สัมภาระสำเร็จ' });
        } else {
          this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการเพิ่มไม่สำเร็จ : เนื่องจากระบบมีข้อผิดพลาด' });
        }
      }).catch((e) => console.log(e['error']['message']));
    } else {
      this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการเพิ่มไม่สำเร็จ : ตู้สัมภาระซ้ำ' });
    }
    this.clear();
  }

  update() {
    const count = this.items.findIndex(res => res.number === this.baggageNumber && res.locationId === this.location['id'])
    if (count == this.number || count == -1) {
      const data = {
        lockerId: this.baggage['lockerId'],
        number: this.baggageNumber,
        locationId: this.location.id
      };
      // this.messageService.clear()
      this.baggageService.update(data)
        .subscribe(res => {
          if (res['status'] === 'Success') {
            const index = this.items.findIndex(e => e['lockerId'] === res['data'][0]['lockerId']);
            this.items[index] = res['data'][0];
            this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการแก้ไขตู้สัมภาระสำเร็จ' });
          } else {
            this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการแก้ไขไม่สำเร็จ : เนื่องจากระบบมีข้อผิดพลาด' });
          }
        },
          (e) => {
            console.log(e['error']['message']);
          });
    } else {
      this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการแก้ไขไม่สำเร็จ : ตู้สัมภาระซ้ำ' });
    }
    this.clear();
  }

  clear() {
    this.baggage = { lockerNumber: '', baggageId: '' };
    this.baggageNumber = '';
    this.location = null;
    this.displayDialog = false;
  }
  showDialogToAdd() {
    this.newBaggage = true;
    this.displayDialog = true;
  }
  filterLocationMultiple(event) {
    let query = event.query;
    this.filteredLocation = this.filterLocation(query, this.locations);
  }
  filterLocation(query, locations: any[]): any[] {
    let filtered: any[] = [];
    for (let i = 0; i < locations.length; i++) {
      let location = locations[i]
      if ((location.name).indexOf(query) == 0) {
        filtered.push(location);
      }
    }
    return filtered;
  }
}

