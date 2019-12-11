import { Component, OnInit } from '@angular/core';
import { MenuItem, SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { FormGroup, FormControl, Validators, FormControlName } from '@angular/forms';
import { TitleNameService } from 'src/app/shared/service/title-name.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ManageUserService } from 'src/app/shared/service/manage-user.service';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { TitleName } from 'src/app/shared/interfaces/title-name';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { formatDate } from '@angular/common';
import { ProvinceService } from 'src/app/shared/service/province.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public menu: MenuItem[];
  public titleName: any[];
  public th: any;
  public yearRange: string;
  public detailWarning: any[];
  public registerSuccess: boolean;
  public showCancelMessage: boolean;
  public urlback: string;
  public showRole: boolean;
  public messageback: string;
  public filteredTitleName: any[];
  public filteredProvince: any[];
  public courseHisName = '';
  public courseHisLocation = '';
  public courseHisList: any[] = [];
  profileString: string;
  currentId = 0;
  profile: any;
  bloodGroup: SelectItem[] = [
    { label: 'O', value: 'O' },
    { label: 'A', value: 'A' },
    { label: 'B', value: 'B' },
    { label: 'AB', value: 'AB' },
  ];
  public provinces: any[];
  public titleNames: any[];
  public displaySystemMessage = false;

  registerForm = new FormGroup({
    username: new FormControl(null, [Validators.required, Validators.minLength(6)]),
    password: new FormControl(null, [Validators.required, Validators.minLength(6)]),
    repassword: new FormControl('', [Validators.required, Validators.minLength(6)]),
    idCard: new FormControl(null, [Validators.required]),
    titleName: new FormControl('', [Validators.required]),
    fname: new FormControl(null, [Validators.required]),
    lname: new FormControl(null, [Validators.required]),
    job: new FormControl(null),
    gender: new FormControl('3', [Validators.required]),
    age: new FormControl(null, [Validators.required, Validators.min(0)]),
    address: new FormControl(null, [Validators.required]),
    province: new FormControl(null, [Validators.required]),
    postalCode: new FormControl(null, [Validators.required, Validators.pattern('[0-9]')]),
    ordianDate: new FormControl(null),
    ordianNumber: new FormControl(null),
    phone: new FormControl(null, [Validators.required]),
    email: new FormControl(null, [Validators.required, Validators.email]),
    phoneEmergency: new FormControl(null, [Validators.required]),
    fnameEmergency: new FormControl(null, [Validators.required]),
    lnameEmergency: new FormControl(null, [Validators.required]),
    relationshipEmergency: new FormControl(null, [Validators.required]),
    historyDharma: new FormControl(null),
    other: new FormControl(null),
    foodsAllergy: new FormControl(null),
    drugsAllergy: new FormControl(null),
    underlyDisease: new FormControl(null),
    blood: new FormControl('', [Validators.required]),
  });

  public formError = {
    username: '',
    idCard: '',
    password: '',
    repassword: '',
    titleName: '',
    age: '',
    fname: '',
    lname: '',
    gender: '',
    address: '',
    province: '',
    postalCode: '',
    phone: '',
    email: '',
    phoneEmergency: '',
    fnameEmergency: '',
    lnameEmergency: '',
    relationshipEmergency: '',
    blood: '',
  };

  public validationMessage = {
    username: {
      // detail: 'กรุณากรอก Username',
      required: 'ชื่อผู้ใช้*'
    },
    password: {
      // detail: 'กรุณากรอก Password',
      required: 'รหัสผ่าน*'
    },
    repassword: {
      // detail: 'กรุณากรอก Re-password',
      required: 'ยืนยันรหัสผ่าน*'
    },
    idCard: {
      // detail: 'กรุณากรอก เลขประจำตัวประชาชน',
      required: 'เลขประจำตัวประชาชน*'
    },
    age: {
      // detail: 'กรุณากรอก อายุ',
      required: 'อายุ*'
    },
    titleName: {
      // detail: 'กรุณากรอก คำนำหน้า',
      required: 'คำนำหน้า*'
    },
    fname: {
      // detail: 'กรุณากรอก ชื่อ',
      required: 'ชื่อ*'
    },
    lname: {
      // detail: 'กรุณากรอก นามสกุล',
      required: 'นามสกุล*'
    },
    gender: {
      // detail: 'กรุณากรอก เพศ',
      required: 'เพศ*'
    },
    address: {
      // detail: 'กรุณากรอก ที่อยู่',
      required: 'ที่อยู่*'
    },
    province: {
      // detail: 'กรุณากรอก จังหวัด',
      required: 'จังหวัด*'
    },
    postalCode: {
      // detail: 'กรุณากรอก รหัสไปรษณีย์',
      required: 'รหัสไปรษณีย์*'
    },
    phone: {
      // detail: 'กรุณากรอก เบอร์โทร',
      required: 'เบอร์โทรศัพท์*'
    },
    email: {
      // detail: 'กรุณากรอก E-mail',
      required: 'E-mail*'
    },
    phoneEmergency: {
      // detail: 'กรุณากรอก เบอร์ติดต่อฉุกเฉิน',
      required: 'เบอร์ติดต่อฉุกเฉิน*'
    },
    fnameEmergency: {
      // detail: 'กรุณากรอก ชื่อผู้ติดต่อฉุกเฉิน',
      required: 'ชื่อผู้ติดต่อฉุกเฉิน*'
    },
    lnameEmergency: {
      // detail: 'กรุณากรอก นามสกุลผู้ติดต่อฉุกเฉิน',
      required: 'นามสกุลผู้ติดต่อฉุกเฉิน*'
    },
    relationshipEmergency: {
      // detail: 'กรุณากรอก ความสัมพันธ์กับผู้ติดต่อฉุกเฉิน',
      required: 'ความสัมพันธ์*'
    },
    blood: {
      // detail: 'กรุณากรอก กรุ๊ปเลือด',
      required: 'กรุ๊ปเลือด*'
    },
  };
  previewImg: string | ArrayBuffer;
  constructor(
    private messageService: MessageService,
    private titleService: TitleNameService,
    private router: Router,
    private manageUserService: ManageUserService,
    private route: ActivatedRoute,
    private confirmationService: ConfirmationService,
    private breadCrumbService: BreadcrumbService,
    private provinceService: ProvinceService
  ) { }

  ngOnInit() {
    //---------- CalenderTH -----------------------
    this.th = {
      firstDayOfWeek: 1,
      dayNames: ['อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ', 'พฤหัสบดี', 'ศุกร์', 'เสาร์'],
      dayNamesShort: ['อาทิต', 'จัน', 'อังคาร', 'พุธ', 'พฤหัส', 'ศุกร์', 'เสาร์'],
      dayNamesMin: ['อา', 'จ', 'อ', 'พ', 'พฤ', 'ศ', 'ส'],
      monthNames: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน',
        'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม',
        'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
      monthNamesShort: ['มกรา', 'กุมภา', 'มีนา', 'เมษา',
        'พฤษภา', 'มิถุนา', 'กรกฎา', 'สิงหา',
        'กันยา', 'ตุลา', 'พฤศจิกา', 'ธันวา'],
      monthNamesMin: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.',
        'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.',
        'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.'],
      today: 'Today',
      clear: 'Clear',
    };
    //----------------------------------------------
    // const email = this.registerForm.get('email');
    // console.log(email.dirty);
    this.registerSuccess = false;
    this.showCancelMessage = false;
    this.setBack();
    // this.createForm();
    this.settingCalendarTH();
    this.provinceService.getProvince().subscribe(
      res => {
        this.provinces = res.data;
      },
      err => {
        console.log(err['error']['message']);
      }
    );
    this.titleService.getTitleNames().subscribe(
      res => {
        // this.titleNames = res;
        this.titleNames = res;
      },
      err => {
        console.log(err['error']['message']);
      }
    );

    this.menu = [
      { label: 'Login', url: 'auth/login' },
      { label: 'Register : สมัครสมาชิก' },
    ];
  }

  addCourseHis() {
    this.courseHisName = '';
    this.courseHisLocation = '';
    const his = { 'courseName': this.courseHisName, 'courseLocation': this.courseHisLocation };
    this.courseHisList.push(his);
    // } else {
    //   document.getElementById('courseDis').style.color = 'red';
    // }
  }

  delHisCourse(index) {
    this.courseHisList.splice(index, 1);
  }

  setBack() {
    this.urlback = this.route.snapshot.data.urlback;
    this.messageback = this.route.snapshot.data.messageback;
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

    const currentYear = formatDate(new Date(), 'yyyy', 'en');
    const startYear = parseInt(currentYear) - 100;
    this.yearRange = startYear + ':' + currentYear;
  }

  onSubmit(e) {
    if (!this.registerForm.valid) {
      this.subscribeInputMessageWaring();
      this.showMessage();
    } else {
      this.submitMessage(e);
    }
    //console.log('test');

  }

  /**
   * ข้อความยืนยันสมัครสมาชิก
   * @param e ;
   */
  submitMessage(e) {
    const message = 'ยืนยันการสมัครสมาชิก';
    const type = 'submit';
    this.showDialog(message, type);
  }

  /**
   * แสดง ConfirmDialog ยืนยันการสมัครสมาชิก
   * @param message ;
   * @param type ;
   */
  showDialog(message, type) {
    this.confirmationService.confirm({
      message: message,
      header: 'ข้อความแจ้งเตือน',
      accept: () => {
        this.actionAccept(type);
        // console.log(type);
      },
      reject: () => {
      }
    });
  }

  /**
   * create user
   */
  actionAccept(type) {
    switch (type) {
      case 'cancle': {
        this.router.navigateByUrl(this.urlback);
        break;
      }
      case 'submit': {
        //console.log('submit');
        // const dataUser = this.onSave(this.registerForm.getRawValue());
        const provinceCode = this.registerForm.get('province').value;
        const titleCode = this.registerForm.get('titleName').value;
        const emerName = (this.registerForm.get('fnameEmergency').value) + ' ' + (this.registerForm.get('lnameEmergency').value);
        const bloodGroup = this.registerForm.get('blood').value;
        const dataUser = {
          username: this.registerForm.get('username').value,
          password: this.registerForm.get('password').value,
          idCard: this.registerForm.get('idCard').value,
          age: this.registerForm.get('age').value,
          fname: this.registerForm.get('fname').value,
          lname: this.registerForm.get('lname').value,
          job: this.registerForm.get('job').value,
          address: this.registerForm.get('address').value,
          provinceId: parseInt(provinceCode.provinceId),
          ordianDate: this.registerForm.get('ordianDate').value,
          ordianNumber: this.registerForm.get('ordianNumber').value,
          tel: this.registerForm.get('phone').value,
          emergencyTel: this.registerForm.get('phoneEmergency').value,
          emergencyName: emerName,
          emergencyRelationship: this.registerForm.get('relationshipEmergency').value,
          email: this.registerForm.get('email').value,
          img: this.profileString,
          registerDate: null,
          lastUpdate: null,
          genderId: this.registerForm.get('gender').value,
          titleId: parseInt(titleCode.id),
          historyDharma: this.courseHisList,
          other: this.registerForm.get('other').value,
          allergyFood: this.registerForm.get('foodsAllergy').value,
          allergyMedicine: this.registerForm.get('drugsAllergy').value,
          disease: this.registerForm.get('underlyDisease').value,
          blood: bloodGroup.value,
        };
        console.log(dataUser);
        this.manageUserService.createUser(dataUser).subscribe(
          res => {
            if (res['status'] === 'Success') {
              this.showToast('alertMessage', 'สมัครสมาชิกสำเร็จ', 'success');
            } else {
              this.showToast('alertMessage', 'สมัครสมาชิกไม่สำเร็จ', 'error');
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

  CheckColors(val) {
    const element = document.getElementById('color');
    if (val === 'pick a color' || val === 'others') {
      element.style.display = 'block';
    } else {
      element.style.display = 'none';
    }
  }

  showMessage() {
    this.displaySystemMessage = true;
    // this.showToast('systemMessage', this.detailWarning);
  }

  onReject() {
    if (this.registerSuccess) {
      this.router.navigateByUrl(this.urlback);
    }
    this.showCancelMessage = false;
  }

  showCancel() {
    // this.showMessage('cancel');
    const message = 'ยกเลิกการสมัคร ?';
    const type = 'cancle';
    this.showDialog(message, type);
  }

  /**
   * ตรวจสอบค่าที่รับเข้ามาใหม่ในกรณีกรอกข้อมูลไม่ครบถ้วน
   */
  subscribeInputMessageWaring() {
    this.registerForm
      .valueChanges
      .pipe(
        debounceTime(500),
        distinctUntilChanged()
      )
      .subscribe(() => this.waringMessage());
    this.waringMessage();
  }

  /**
   * ตรวจสอบข้อมูลที่กรอก
   */
  waringMessage() {
    if (!this.registerForm) {
      return;
    }
    this.detailWarning = [];
    let details: string;
    for (const field of Object.keys(this.formError)) {
      this.formError[field] = '';
      const control = this.registerForm.get(field);
      if ((field === 'repassword')) {
        if (control.value === '') {
          this.detailWarning[0] = 'กรุณากรอกยืนยันรหัสผ่าน';
        } else if (control.value !== this.registerForm.get('password').value) {
          this.detailWarning[0] = 'กรุณากรอกรหัสผ่านให้ตรงกัน';
        } else {
          continue;
        }
        this.formError[field] = this.validationMessage[field].required;
      } else if (control && !control.valid && this.validationMessage[field].required) {
        details = 'กรุณากรอกข้อมูลให้ครบถ้วน';
        this.detailWarning[1] = details;
        // this.detailWarning += this.validationMessage[field].detail + '\n';
        this.formError[field] = this.validationMessage[field].required;
      }
    }
  }

  /**
   * Toast สมัครสมาชิก
   * @param key ;
   * @param detail ;
   */
  showToast(key, detail, severity) {
    this.messageService.clear();
    this.messageService.add(
      {
        severity: severity,
        key: key,
        sticky: true,
        summary: 'ข้อความจากระบบ',
        detail: detail
      }
    );
  }

  /**
   * รับค่าจากแป้นพิมพ์
   * @param event
   */
  filterTitleNameMultiple(event) {
    const query = event.query;
    this.filteredTitleName = this.filterTitleName(query, this.titleNames);
  }
  /**
     * รับค่าจากแป้นพิมพ์
     * @param event
     */
  filterProvinceMultiple(event) {
    const query = event.query;
    this.filteredProvince = this.filterProvince(query, this.provinces);
  }

  /**
   * เปรียบเทียบค่าที่ได้จากแป้นพิมพ์ กับ ค่าที่ได้จากดาต้าเบส
   * @param query
   * @param titleNames
   */
  filterTitleName(query, titleNames: any[]): any[] {
    const filtered: any[] = [];
    for (let i = 0; i < titleNames.length; i++) {
      const titleName = titleNames[i];
      if ((titleName.display).match(query)) {
        filtered.push(titleName);
        //console.log(titleName.display);
      }
    }
    return filtered;
  }

  filterProvince(query, provinces: any[]): any[] {
    const filtered: any[] = [];
    for (let i = 0; i < provinces.length; i++) {
      const province = provinces[i];
      if ((province.provinceName).match(query)) {
        filtered.push(province);
      }
    }
    return filtered;
  }

  onGenderSelect(event: TitleName) {
    const gender = this.registerForm.get('titleName').value;
    // const newObject = {...event};
    //console.log('from typing ' + event);
  }

  profileSelect(event, field) {
    this.currentId = field;
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file: File = fileList[0];
      if (file.size < 1000000) {
        this.profile = file;
        this.handleInputChange(this.profile); // turn into base64
      } else {
        this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ไฟล์เกินขนาด!' });
      }
    }
  }
  // เปลี่ยนรูปภาพ
  handleInputChange(files) {
    const file = files;
    // format รูป เป็นไฟล์อื่นจะผิด
    const pattern = /image-*/;
    const reader = new FileReader();
    if (!file.type.match(pattern)) {
      this.messageService.add({ severity: 'error', summary: 'ข้อความจากระบบ', detail: 'ไฟล์ผิดประเภท!' });
      return;
    }
    reader.onloadend = this._handleReaderLoaded.bind(this);
    reader.onload = () => {
      this.previewImg = reader.result;
    };
    reader.readAsDataURL(file);
  }
  _handleReaderLoaded(e) {
    const reader = e.target;
    const base64result = reader.result.substr(reader.result.indexOf(',') + 1);
    // this.imageSrc = base64result;
    const id = this.currentId;
    if (id === 1) {
      this.profileString = base64result;
      //console.log(this.profileString);
    }
  }
}
