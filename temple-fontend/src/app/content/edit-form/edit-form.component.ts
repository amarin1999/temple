import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { DatePipe } from '@angular/common';
import { Validators, FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { MessageService, MenuItem, ConfirmationService, Message, SelectItem } from 'primeng/api';
import { TitleNameService } from 'src/app/shared/service/title-name.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ManageUserService } from 'src/app/shared/service/manage-user.service';
import { BreadcrumbService } from '../../shared/service/breadcrumb.service';
import localeTh from '@angular/common/locales/th.js';
import { ManageRoleService } from 'src/app/shared/service/manage-role.service';
import { Role } from 'src/app/shared/interfaces/role';
import {AuthService} from '../../shared/service/auth.service';


@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls: ['./edit-form.component.css']
})
export class EditFormComponent implements OnInit {
  public menu: MenuItem[];
  public titleName: any[];
  public th: any;
  public yearRange: string;
  public detailWarning: any[];
  public registerSuccess: boolean;
  public showCancelMessage: boolean;
  public personalId: string;
  public previewImg: any;
  public onEdit: boolean;
  public pipe = new DatePipe('th-TH');
  public showRole: boolean;
  public roles: Role[];
  public msgs: Message[] = [];
  public urlback: string;
  public messageback: string;
  public filteredTitleName: any[];
  public displaySystemMessage = false;
  currentId = 0;
  profile: any;
  profileString: string;
  titleNames: any[];
  bloodGroup: SelectItem[] = [
    {label: 'O', value: 'O'},
    {label: 'A', value: 'A'},
    {label: 'B', value: 'B'},
    {label: 'AB', value: 'AB'},
  ];

 
  editForm = new FormGroup({
    titleName: new FormControl('', [Validators.required]),
    fname: new FormControl(null, [Validators.required]),
    lname: new FormControl(null, [Validators.required]),
    job: new FormControl(null),
    gender: new FormControl(null, [Validators.required]),
    address: new FormControl(null, [Validators.required]),
    phone: new FormControl(null, [Validators.required]),
    email: new FormControl(null, [Validators.required, Validators.email]),
    phoneEmergency: new FormControl(null, [Validators.required]),
    fnameEmergency: new FormControl(null, [Validators.required]),
    lnameEmergency: new FormControl(null, [Validators.required]),
    relationshipEmergency: new FormControl(null, [Validators.required]),
    other: new FormControl(null),
    foodsAllergy: new FormControl(null),
    drugsAllergy: new FormControl(null),
    underlyDisease: new FormControl(null),
    blood: new FormControl('', [Validators.required]),
    role: new FormControl(null),
    imgProfile: new FormControl(null)
  });

  public formError = {
    username: '',
    password: '',
    repassword: '',
    titleName: '',
    fname: '',
    lname: '',
    job: '',
    gender: '',
    address: '',
    phone: '',
    email: '',
    phoneEmergency: '',
    fnameEmergency: '',
    lnameEmergency: '',
    relationshipEmergency: '',
    other: '',
    foodsAllergy: '',
    drugsAllergy: '',
    underlyDisease: '',
    blood: '',
    role: ''
  };

  public validationMessage = {
    username: {
      detail: 'กรุณากรอก Username',
      required: 'Username*'
    },
    password: {
      detail: 'กรุณากรอก Password',
      required: 'Password*'
    },
    repassword: {
      detail: 'กรุณากรอก Re-password',
      required: 'Re-password*'
    },
    titleName: {
      detail: 'กรุณากรอก คำนำหน้า',
      required: 'คำนำหน้า*'
    },
    fname: {
      detail: 'กรุณากรอก ชื่อ',
      required: 'ชื่อ*'
    },
    lname: {
      detail: 'กรุณากรอก นามสกุล',
      required: 'นามสกุล*'
    },
    job: {
      detail: 'กรุณากรอก อาชีพ',
      required: 'อาชีพ*'
    },
    gender: {
      detail: 'กรุณากรอก เพศ',
      required: 'เพศ*'
    },
    address: {
      detail: 'กรุณากรอก ที่อยู่',
      required: 'ที่อยู่*'
    },
    phone: {
      detail: 'กรุณากรอก เบอร์โทร',
      required: 'เบอร์โทร*'
    },
    email: {
      detail: 'กรุณากรอก E-mail',
      required: 'E-mail*'
    },
    phoneEmergency: {
      detail: 'กรุณากรอก เบอร์ติดต่อฉุกเฉิน',
      required: 'เบอร์ติดต่อฉุกเฉิน*'
    },
    fnameEmergency: {
      detail: 'กรุณากรอก ชื่อผู้ติดต่อฉุกเฉิน',
      required: 'ชื่อผู้ติดต่อฉุกเฉิน*'
    },
    lnameEmergency: {
      detail: 'กรุณากรอก นามสกุลผู้ติดต่อฉุกเฉิน',
      required: 'นามสกุลผู้ติดต่อฉุกเฉิน*'
    },
    relationshipEmergency: {
      detail: 'กรุณากรอก ความสัมพันธ์กับผู้ติดต่อฉุกเฉิน',
      required: 'ความสัมพันธ์*'
    },
    other: {
      detail: 'กรุณากรอก หมายเหตุ',
      required: 'หมายเหตุ*'
    },
    foodsAllergy: {
      detail: 'กรุณากรอก อาหารที่แพ้',
      required: 'อาหารที่แพ้*'
    },
    drugsAllergy: {
      detail: 'กรุณากรอก ยาที่แพ้',
      required: 'ยาที่แพ้*'
    },
    underlyDisease: {
      detail: 'กรุณากรอก โรคประจำตัว',
      required: 'โรคประจำตัว*'
    },
    blood: {
      detail: 'กรุณากรอก กรุ๊ปเลือด',
      required: 'กรุ๊ปเลือด*'
    },
  };


  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private titleService: TitleNameService,
    private router: Router,
    private manageUserService: ManageUserService,
    private route: ActivatedRoute,
    private breadCrumbService: BreadcrumbService,
    private confirmationService: ConfirmationService,
    private roleService:ManageRoleService,
    private authService:AuthService
  ) {
  }

  ngOnInit() {
    this.showRole = this.roleService.getRoleStatus();
    this.roles = this.roleService.getRoles();
    this.personalId = this.route.snapshot.paramMap.get('id');
    this.setBack();
    this.registerSuccess = false;
    this.showCancelMessage = false;
    this.onEdit = false;
    this.settingForm();
    this.settingCalendarTH();
    this.titleService.getTitleNames().subscribe(
      res => {
        this.titleNames = [
          res
        ];
      },
      err => {
        console.log(err['error']['message']);

      }
    );
    
    if (this.authService.getRole().value === 'admin') {
      this.breadCrumbService.setPath([
        {label: 'จัดการสมาชิก', routerLink: '/users'},
        { label: 'แก้ไขข้อมูลส่วนตัว' },
      ]);
    } else {
      this.breadCrumbService.setPath([
        { label: 'ข้อมูลส่วนตัว', routerLink: ['/profile', localStorage.getItem('userId')] },
        { label: 'แก้ไขข้อมูลส่วนตัว' },
      ]);
    }

  }

  setBack(){
    const route = this.authService.getRole().value==='admin'?'':this.personalId;
    this.urlback = this.route.snapshot.data.urlback+route;
    this.messageback = this.route.snapshot.data.messageback;
  }

  settingForm() {
    this.manageUserService.getUser(this.personalId)
      .subscribe(res => {
        console.log( res);
        const titlename = {
          id: res['data']['titleId'],
          display: res['data']['titleDisplay'],
          name: res['data']['titleName']
        };
        const role = {
          roleId: res['data']['roleId'],
          roleName: res['data']['roleName'],
        };        
        const blood = {
          label: res['data']['blood'],
          value: res['data']['blood']
        }
        let emerName = res['data']['emergencyName'];
        if (res['data']['emergencyName'] !== null) {
          emerName = emerName.split(' ', 2);
          this.editForm.controls['fnameEmergency'].setValue(emerName[0]);
          this.editForm.controls['lnameEmergency'].setValue(emerName[1]);
        }else{
          this.editForm.controls['fnameEmergency'].setValue(null);
          this.editForm.controls['lnameEmergency'].setValue(null);
        }

        this.editForm.get('role').patchValue(role);
        this.editForm.get('titleName').patchValue(titlename);
        this.editForm.get('fname').setValue(res['data']['fname']);

        this.editForm.controls['lname'].setValue(res['data']['lname']);
        this.editForm.controls['job'].setValue(res['data']['job']);
        this.editForm.controls['gender'].setValue(res['data']['genderId']);
        this.editForm.controls['phone'].setValue(res['data']['tel']);
        this.editForm.controls['email'].setValue(res['data']['email']);
        this.editForm.controls['address'].setValue(res['data']['address']);
        this.editForm.controls['phoneEmergency'].setValue(res['data']['emergencyTel']);
        
        this.editForm.controls['imgProfile'].setValue(res['data']['img']);
        this.editForm.controls['relationshipEmergency'].setValue(res['data']['emergencyRelationship']);
        this.editForm.controls['other'].setValue(res['data']['other']);
        this.editForm.controls['foodsAllergy'].setValue(res['data']['allergyFood']);
        this.editForm.controls['drugsAllergy'].setValue(res['data']['allergyMedicine']);
        this.editForm.controls['underlyDisease'].setValue(res['data']['disease']);
        this.editForm.controls['blood'].patchValue(blood);
      },
        err => console.log(err['error']['message'])
      );
  }

  settingCalendarTH() {
    this.th = {
      firstDayOfWeek: 1,
      dayNamesMin: ['อา', 'จ', 'อ', 'พ', 'พฤ', 'ศ', 'ส'],
      monthNames: ['มกราคม ', 'กุมภาพันธ์ ', 'มีนาคม ', 'เมษายน ',
        'พฤษภาคม  ', 'มิถุนายน ', 'กรกฎาคม ', 'สิงหาคม ',
        'กันยายน ', 'ตุลาคม ', 'พฤศจิกายน ', 'ธันวาคม '],
      today: 'Today',
      clear: 'Clear',
    };

    // const currentYear = new Date().toLocaleString('en-Us',{timeZone:'Asia/Bangkok'})
    // const aestTime = new Date(currentYear)
    //const currentYear = parseInt(formatDate(Date.now(),'yyyy','th'))
    const currentYear = this.pipe.transform(Date.now(), 'yyyy');
    const startYear = parseInt(currentYear) - 100;
    this.yearRange = startYear + ':' + currentYear;

  }

  onSubmit(e) {
    // console.log('onsubmit');
    if (!this.editForm.valid) {
      this.subscribeInputMessageWaring();
      this.showMessageWrongValidate();
    } else {
      this.submitMessage(e);
    }
  }

  showMessageWrongValidate() {
    this.displaySystemMessage = true;
   // this.showToast('systemMessage', this.detailWarning);
  }


  submitMessage(e) {
    const message = 'ยืนยันการแก้ไขข้อมูลส่วนตัว ?';
    const type = 'submit'
    this.showDialog(message, type);
  }
  onEditprofile() {
    this.registerSuccess = true;
    this.router.navigateByUrl(this.urlback + this.personalId);
  }

  onCancel() {
    this.router.navigateByUrl(this.urlback + this.personalId);
  }

  onReject() {
    if (this.registerSuccess) {
      this.router.navigateByUrl(this.urlback + this.personalId);
    }
    this.messageService.clear('systemMessage');
    this.showCancelMessage = false;
  }

  showCancelConfirm() {
    let message;
    if(this.authService.getRole().value ==='admin'){
      message = 'ยกเลิกการแก้ไขข้อมูลส่วนตัว และกลับสู่หน้าจัดการสมาชิก ?';
    }else{
      message = 'ยกเลิกการแก้ไขข้อมูลส่วนตัว และกลับสู่หน้าข้อมูลส่วนตัว ?';
    }
    const type = 'cancle';
    this.showDialog(message, type);

  }


 // อัปโหลดรูปภาพ
 profileSelect(event, field) {
  this.currentId = field;
  const fileList: FileList = event.target.files;
  if (fileList.length > 0) {
    const file: File = fileList[0];
    if (file.size < 512000) {
      this.profile = file;
      this.handleInputChange(this.profile); // turn into base64
    } else {
      alert('ไฟล์เกินขนาด!');
    }
  } else {
    alert('ไฟล์ผิดประเภท!');
  }
}
// เปลี่ยนรูปภาพ
handleInputChange(files) {
  const file = files;
  // format รูป เป็นไฟล์อื่นจะผิด
  const pattern = /image-*/;
  const reader = new FileReader();
  if (!file.type.match(pattern)) {
    alert('ไฟล์ผิดประเภท!');
    return;
  }
  reader.onloadend = this._handleReaderLoaded.bind(this);
  reader.onload = () => {
    this.previewImg = reader.result;
  };
  reader.readAsDataURL(file);
}
// เปลี่ยนรูปจาก image เป็น base64 แล้ว post ลงฐานข้อมูล ในรูปแบบ String เป็น Type Blob
_handleReaderLoaded(e) {
  const reader = e.target;
  const base64result = reader.result.substr(reader.result.indexOf(',') + 1);
  // this.imageSrc = base64result;
  const id = this.currentId;
  if (id === 1) {
    this.profileString = base64result;
    this.editForm.controls['imgProfile'].setValue(this.profileString);
    // console.log(this.profileString);
  }
}

  subscribeInputMessageWaring() {
    this.editForm
      .valueChanges
      .pipe(
        debounceTime(500),
        distinctUntilChanged()
      )
      .subscribe(() => this.waringMessage());
    this.waringMessage();
  }

  waringMessage() {
    if (!this.editForm) {
      return;
    }
    this.detailWarning = [];
    let details: string;
    for (const field of Object.keys(this.formError)) {
      this.formError[field] = '';
      const control = this.editForm.get(field);
      if (control && !control.valid) {
        details = 'กรุณากรอกข้อมูลให้ครบถ้วน';
        this.detailWarning[1] = details;
        // this.detailWarning += this.validationMessage[field].detail + '\n';
        this.formError[field] = this.validationMessage[field].required;
      } 
    }
  }

  delPic(){
    console.log("delpicprofile");
    
  }

  showClearConfirm() {
    const message = 'คืนค่าการแก้ไขทั้งหมด';
    const type = 'clear'
    this.showDialog(message, type);
  }

  showDialog(message, type) {
    this.confirmationService.confirm({
      message: message,
      header: 'ข้อความจากระบบ',
      accept: () => {
        this.actionAccept(type);
      },
      reject: () => {
        // switch (type) {
        //   case 'clear':{
        //     this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ยกเลิกการคืนค่าข้อมูลส่วนตัว' });
        //     break;
        //   }
        //   case 'cancle': {
        //     this.messageService.add({ severity: 'warn', summary: 'ข้อความจากระบบ', detail: 'ยกเลิกการยืนยันแก้ไขข้อมูลส่วนตัว' });
        //     break;
        //   }
        // }
      }
    });
  }

  actionAccept(type) {
    switch (type) {
      case 'clear': {
        this.settingForm();
        this.messageService.add({ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการคืนค่าข้อมูลส่วนตัวสำเร็จ' });
        break;
      }
      case 'cancle': {
        if( this.authService.getRole().value==='admin'){
          this.router.navigateByUrl(`/users`);
        }else{
          this.router.navigateByUrl(`/profile/${this.personalId}`);
        }
        
        break;
      }
      case 'submit': {
        const titleCode = this.editForm.get('titleName').value;
        const role = this.editForm.get('role').value;
        const emerName = (this.editForm.get('fnameEmergency').value) + ' ' + (this.editForm.get('lnameEmergency').value);
        const bloodGroup = this.editForm.get('blood').value;
        const dataUser = {
          fname: this.editForm.get('fname').value,
          lname: this.editForm.get('lname').value,
          job: this.editForm.get('job').value,
          address: this.editForm.get('address').value,
          tel: this.editForm.get('phone').value,
          emergencyTel: this.editForm.get('phoneEmergency').value,
          email: this.editForm.get('email').value,
          img: this.profileString,
          registerDate: null,
          lastUpdate: null,
          genderId: this.editForm.get('gender').value,
          titleId: +(titleCode.id),
          roleId: +(role.roleId),
          emergencyName: emerName,
          emergencyRelationship: this.editForm.get('relationshipEmergency').value,
          other: this.editForm.get('other').value,
          allergyFood: this.editForm.get('foodsAllergy').value,
          allergyMedicine: this.editForm.get('drugsAllergy').value,
          disease: this.editForm.get('underlyDisease').value,
          blood: bloodGroup.value,

        };

        this.manageUserService.updateUser(this.personalId, dataUser).subscribe(
          res => {
            if (res['status'] === 'Success') {
              this.showToast('alertMessage', 'แก้ไขข้อมูลสำเร็จ');
            } else {
              this.showToast('alertMessage', 'แก้ไขข้อมูลไม่สำเร็จ');
            }
          },
          err => {
            console.log(err);
          }
        );
        break;
      }
      default: { break; }
    }
  }

  showToast(key, detail) {
    // this.messageService.clear();
    this.messageService.add(
      {
        key: key,
        sticky: true,
        summary: 'ข้อความจากระบบ',
        detail: detail
      }
    );
  }

  /**
   * รับค่าจากแป้นพิมพ์
   * @param event ;
   */
  filterTitleNameMultiple(event) {
    const query = event.query;
    this.filteredTitleName = this.filterTitleName(query, this.titleNames);
  }

  /**
   * เปรียบเทียบค่าที่ได้จากแป้นพิมพ์ กับ ค่าที่ได้จากดาต้าเบส
   * @param query ;
   * @param titleNames ;
   */
  filterTitleName(query, titleNames: any[]): any[] {
    const filtered: any[] = [];
    for (let i = 0; i < titleNames.length; i++) {
      const titleName = titleNames[i];
      if ((titleName.display).match(query)) {
        filtered.push(titleName);
        // console.log(titleName.display);
      }
    }
    return filtered;
  }
  
}
