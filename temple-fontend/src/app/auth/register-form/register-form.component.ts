import { Component, OnInit } from '@angular/core';
import { MenuItem, MessageService, ConfirmationService } from 'primeng/api';
import { Validators, FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { formatDate } from '@angular/common';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { TitleNameService } from 'src/app/shared/service/title-name.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ManageUserService } from 'src/app/shared/service/manage-user.service';
import { AuthService } from '../../shared/service/auth.service';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { SelectItem } from 'primeng/api';
import { TitleName } from 'src/app/shared/interfaces/title-name';
import { Role } from 'src/app/shared/interfaces/role';
import { ManageRoleService } from 'src/app/shared/service/manage-role.service';
interface Transportation {
  memberTransportation: string;
}
@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
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
  public roles: Role[];
  profileString: string;
  currentId = 0;
  profile: any;
  bloodGroup: SelectItem[] = [
    {label: 'O', value: 'O'},
    {label: 'A', value: 'A'},
    {label: 'B', value: 'B'},
    {label: 'AB', value: 'AB'},
  ];
  public titleNames: any[];
  public displaySystemMessage = false;

  registerForm = new FormGroup({
    username: new FormControl( null, [Validators.required, Validators.minLength(6)]),
    password: new FormControl(null, [Validators.required, Validators.minLength(6)]),
    repassword: new FormControl('', [Validators.required, Validators.minLength(6)]),
    titleName: new FormControl('', [Validators.required]),
    fname: new FormControl(null, [Validators.required]),
    lname: new FormControl(null, [Validators.required]),
    job: new FormControl(null),
    role: new FormControl(null,[Validators.required]),
    gender: new FormControl('3', [Validators.required]),
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
  });

  public formError = {
    username: '',
    password: '',
    repassword: '',
    titleName: '',
    fname: '',
    lname: '',
    role: '',
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
  };

  public validationMessage = {
    username: {
      // detail: 'กรุณากรอก Username',
      required: 'Username*'
    },
    password: {
      // detail: 'กรุณากรอก Password',
      required: 'Password*'
    },
    role:{
     required: '  สิทธิการเข้าใช้งาน*'
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
  previewImg: string | ArrayBuffer;



  constructor(
    private messageService: MessageService,
    private titleService: TitleNameService,
    private router: Router,
    private manageUserService: ManageUserService,
    private route: ActivatedRoute,
    private confirmationService: ConfirmationService,
    private breadCrumbService: BreadcrumbService,
    private roleService: ManageRoleService
  ) {

  }

  ngOnInit() {
    // const email = this.registerForm.get('email');
    // console.log(email.dirty);
    
    //------------ Get Role ----------------------------
    this.showRole = this.roleService.getRoleStatus();
    this.roles = this.roleService.getRoles();

    this.breadCrumbService.setPath([
      { label: 'จัดการสมาชิก', routerLink: '/users' },
      { label: 'ลงทะเบียนสมาชิก', routerLink: '/users/create' },
    ]);
    this.urlback = this.route.snapshot.data.urlback;
    // console.log(this.urlback);
    this.registerSuccess = false;
    this.showCancelMessage = false;
    this.setBack();
    // this.createForm();
    this.settingCalendarTH();
    this.titleService.getTitleNames().subscribe(
      res => {
        // this.titleNames = res;
        this.titleNames = [
          ...res
        ];
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

  setBack() {
    this.urlback = this.route.snapshot.data.urlback;
    this.messageback = this.route.snapshot.data.messageback;
  }

  /* createForm() {
    this.registerForm = this.formBuilder.group(
      {
        username: ['', [Validators.required, Validators.minLength(5)]],
        password: ['', [Validators.required, Validators.minLength(5)]],
        repassword: ['', [Validators.required, Validators.minLength(5)]],
        titleName: ['', Validators.required],
        fname: ['', Validators.required],
        lname: ['', Validators.required],
        memberJob: ['', Validators.required],
        gender: ['', Validators.required],
        address: ['', Validators.required],
        phone: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        emergencyTel: ['', Validators.required],
        memberExpected: ['', Validators.required],
        memberCoursePassed: ['', Validators.required],
        memberNote: ['', Validators.required],
        // other: ['', Validators.required],
        memberTransportation: ['', Validators.required],
        memberExpPassed: ['', Validators.required],
      }
    );
  } */

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
        const titleCode = this.registerForm.get('titleName').value;
        const emerName = (this.registerForm.get('fnameEmergency').value) + ' ' + (this.registerForm.get('lnameEmergency').value);
        const bloodGroup = this.registerForm.get('blood').value;
        const dataUser = {
          username: this.registerForm.get('username').value,
          password: this.registerForm.get('password').value,
          roleId: this.registerForm.get('role').value.roleId,
          fname: this.registerForm.get('fname').value,
          lname: this.registerForm.get('lname').value,
          job: this.registerForm.get('job').value,
          address: this.registerForm.get('address').value,
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
          other: this.registerForm.get('other').value,
          allergyFood: this.registerForm.get('foodsAllergy').value,
          allergyMedicine: this.registerForm.get('drugsAllergy').value,
          disease: this.registerForm.get('underlyDisease').value,
          blood: bloodGroup.value,
        };
        // console.log(dataUser);
        this.manageUserService.createUser(dataUser).subscribe(
          res => {
            // console.log(res);
            
            if (res['status'] === 'Success') {
              this.showToast('alertMessage', 'สมัครสมาชิกสำเร็จ');
            } else {
              this.showToast('alertMessage', 'สมัครสมาชิกไม่สำเร็จ');
            }
          },
          err => {
            // console.log('submit error');
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
      if ((field === 'repassword') ) {
        if(control.value == ''){
          this.detailWarning[0] = 'กรุณากรอกยืนยันรหัสผ่าน';
        } else if(control.value !== this.registerForm.get('password').value){
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
  showToast(key, detail) {
    this.messageService.clear();
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
   * @param event 
   */
  filterTitleNameMultiple(event) {
    const query = event.query;
    this.filteredTitleName = this.filterTitleName(query, this.titleNames);
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

  onGenderSelect(event: TitleName) {
    const gender = this.registerForm.get('titleName').value;
   // const newObject = {...event};
    // console.log('from typing ' + event);
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
        alert('ไฟล์เกินขนาด!');
      }
    } else {
      alert('ไฟล์ผิดประเภท!');
    }
  }

  handleInputChange(files) {
    const file = files;
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
  _handleReaderLoaded(e) {
    const reader = e.target;
    const base64result = reader.result.substr(reader.result.indexOf(',') + 1);
    // this.imageSrc = base64result;
    const id = this.currentId;
    if (id === 1) {
      this.profileString = base64result;
      // console.log(this.profileString);
    }
  }

}
