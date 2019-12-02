import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BreadcrumbService } from '../../../shared/service/breadcrumb.service';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { CourseService } from '../shared/course.service';
import { formatDate, DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { LocationService } from '../../location/location.service';
import { ConfirmationService , MessageService} from 'primeng/api';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit {

  public msgs: any;
  public noticearr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
  public notice: Array<any> = [];
  public locations: Location[];
  public filteredTeacher: any[];
  public teachers: any[];
  public teacher: any;
  public teacherLength;
  public obj = [];
  public pipe = new DatePipe('th-TH');
  public yearRange: string;
  @Input() displayEditDialog = false;
  @Input() courseId: number;
  @Output() closeDisplayEditDialog = new EventEmitter();

  formEdit = new FormGroup(
    {
      courseName: new FormControl('', [Validators.required, Validators.maxLength(255)]),
      detail: new FormControl('', [Validators.required, Validators.maxLength(255)]),
      location: new FormControl('', Validators.required),
      date: new FormControl('', Validators.required),
      conditionMin: new FormControl('', Validators.required),
      teachers: new FormControl('', Validators.required),
    }
  )

public formError = {
  courseName: '',
  detail: '',
  location: '',
  date: '',
  teachers: '',
  conditionMin: ''
};

public formLengthError = {
  courseName: '',
  detail: '',
  location: '',
  date: '',
  teachers: '',
  conditionMin: ''
};

public validationMessage = {
  courseName: {
    required: 'ชื่อคอร์ส*'
  },
  detail: {
    required: 'รายละเอียด*'
  },
  location: {
    required: 'สถานที่*'
  },
  date: {
    required: 'วันที่เรียน*'
  },
  teachers: {
    required: 'ผู้สอน*'
  },
  conditionMin: {
    required: 'หมายเหตุ*'
  }
};


  constructor(
    private breadCrumbService: BreadcrumbService,
    private formBuilder: FormBuilder,
    private courseService: CourseService,
    private route: ActivatedRoute,
    private locationService: LocationService,
    private router: Router,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
   this.initNotice();

/*     this.courseId = this.route.snapshot.paramMap.get('id');
    console.log(this.courseId); */

    this.courseService.getTeachers().subscribe(
      res => {
        if (res.status == 'Success') {
          this.teachers = res['data'].map(res => {
            return {
              id: res.id,
              name: res.titleDisplay + res.fname + ' ' + res.lname
            }
          })
          // console.log(this.teachers);

        }
      },
      error => {
        console.log(error['error']['message']);

      }
    );

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
    const currentYear = this.pipe.transform(Date.now(), 'yyyy');
    const startYear = parseInt(currentYear) - 100;
    this.yearRange = startYear + ':' + currentYear;
  }

  private initNotice() {
    this.noticearr.map(res => {
      this.notice.push({ id: res })
    });
  }

  settingForm(id) {
    this.courseId = id;
    this.courseService.getCourseByid(id)
      .subscribe(res => {
        /*var result = [];
        var teacherLength;*/
        // console.log(res);
        const teachers = res['data']['teacherList'].map(res => {
          return {
            id: res['id'],
            name: res['titleDisplay'] + res['fname'] + ' ' + res['lname'],
          };
        });
        // console.log('Teachers = ' + teachers);
        for (var i = 0; i < teachers.length; i++) {
          this.obj.push(teachers[i]);
          // teacherLength= teachers.length
          // console.log(this.obj);
          // console.log(teachers);
        }
        // console.log(this.obj.length);
        // console.log('stDate = ' + res['data']['stDate']);
        // console.log('endDate = ' + res['data']['endDate']);
        const dateJson = [];
        dateJson[0] = res['data']['stDate'];
        dateJson[1] = res['data']['endDate'];
        // console.log(dateJson[0]);
        // console.log(dateJson[1]);
        const datecon = new Date(JSON.stringify(dateJson[0]));
        const datecon2 = new Date(JSON.stringify(dateJson[1]));

        const date = [];
        date[0] = datecon;
        date[1] = datecon2;
        // console.log(date);

        const location = {
          id: res['data']['locationId'],
          name: res['data']['locationName']
        };
        this.formEdit.controls['courseName'].setValue(res['data']['name']);
        this.formEdit.controls['detail'].setValue(res['data']['detail']);
        this.formEdit.controls['location'].setValue(location);
        this.formEdit.controls['teachers'].patchValue(teachers);
        this.formEdit.controls['date'].patchValue(date);
        this.formEdit.controls['conditionMin'].setValue({ id: '' + (res['data']['conditionMin']) });

      },
        err => console.log(err['error']['message'])
      );
  }


  onSubmit() {
    this.setValidate();
    if (!this.formEdit.valid ) {
        this.subscribeInputMessageWaring();
    } else {
      this.confirmationService.confirm({
        message: 'ยืนยันการแก้ไขคอร์ส',
        header: 'แก้ไขคอร์ส',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          const date2 = this.formEdit.get('date').value;
        //  console.log('dateForm0 =' + date2[0]);
        //  console.log('dateForm1 =' + date2[1]);
          const stDate = formatDate(date2[0], 'yyyy-MM-dd', 'en');
          const endDate = formatDate(date2[1], 'yyyy-MM-dd', 'en');
          const id = this.courseId;
          const date = this.formEdit.get('date').value;
          const datesort = date.map(res => formatDate(res, 'yyyy-MM-dd', 'en')).sort();
        // const lastUpdate = formatDate(Date.now(), 'yyyy-MM-dd hh:mm:ss', 'en');
        // console.log(this.obj);

          const course = {
            name: this.formEdit.get('courseName').value,
            detail: this.formEdit.get('detail').value,
            locationId: this.formEdit.get('location').value.id,
            conditionMin:  parseInt(this.formEdit.get('conditionMin').value.id),
            date: datesort,
            stDate: stDate,
            endDate: endDate,
            // lastUpdate: lastUpdate,
            teacher: this.formEdit.get('teachers').value.map(res => res.id)
          };
          // console.log(course);

          this.courseService.editCourse(id, course).subscribe(res => {
            if (res['result'] === 'Success') {
              this.msgs = [{ severity: 'success', summary: 'ข้อความจากระบบ', detail: 'ดำเนินการแก้ไขคอร์สสำเร็จ' }];
              this.onCancle(this.msgs);
            } else if (res['result'] === 'Fail') {
              this.msgs = [{ severity: 'error', summary: 'ข้อความจากระบบ', detail: res['errorMessage'] }];
              this.onCancle(this.msgs);
            }
          });
        },
        reject: () => {
          this.msgs = [{severity: 'info', summary: 'ข้อความจากระบบ', detail: 'ยกเลิกการแก้ไขคอร์ส'}];
          this.onCancle(this.msgs);
        }
      });
    }
  }

  /**
   * ตรวจสอบค่าที่รับเข้ามาใหม่ในกรณีกรอกข้อมูลไม่ครบถ้วน
   */
  subscribeInputMessageWaring() {
    this.formEdit
      .valueChanges
      .pipe(
        debounceTime(500),
        distinctUntilChanged()
      ).subscribe(() => this.waringMessage());
      this.waringMessage();
  }

  waringMessage() {
    if (!this.formError) {
      return;
    }
      for (const field of Object.keys(this.formError)) {
        this.formError[field] = '';
        const control = this.formEdit.get(field);
        if (control && this.validationMessage[field]) {
          // console.log(field);
          // console.log(control.valid);
          if (!control.valid) {
            this.formError[field] = this.validationMessage[field].required;
            if (field == 'courseName') {
              if (control.hasError('maxlength')) {
                // console.log('if' + field);
                this.formLengthError[field] = '**ข้อความต้องน้อยกว่า 255 ตัวอักษร';
              }
            }
            if (field == 'detail' ){
              if (control.hasError('maxlength')) {
                // console.log('if' + field);
                this.formLengthError[field] = '**ข้อความต้องน้อยกว่า 255 ตัวอักษร';
              }
            }
          }else{
            this.formLengthError[field] = '';
          }
        }
      }
  }

  filterTeacherMultiple(event) {
    let query = event.query;
    this.filteredTeacher = this.filterTeacher(query, this.teachers);
  }

  filterTeacher(query, teachers: any): any[] {   
    let filtered: any[] = [];
    for (let i = 0; i < teachers.length; i++) {
      let teacher = teachers[i]
      if ((teacher.name).toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(teacher);
      }
    }
    return filtered;
  }

  onCancle(message) {
    this.closeDisplayEditDialog.emit(message);
    this.formEdit.reset();
        Object.values(this.formEdit.controls).forEach(df => {
          df.markAsPristine();
          df.setValidators(null);
          df.updateValueAndValidity();
        });
  }

  setValidate() {
    Object.keys(this.formEdit.controls).forEach(key => {
      const control = this.formEdit.get(key);
      control.clearValidators();
      if (key == 'courseName' || key == 'detail') {
        control.setValidators([Validators.required, Validators.maxLength(255)]);
      } else {
        control.setValidators(Validators.required);
      }
      control.updateValueAndValidity();
    });
  }
}