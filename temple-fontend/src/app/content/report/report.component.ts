import { Component, OnInit, ViewChild } from '@angular/core';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ReportService } from 'src/app/shared/service/report.service';
import { Report } from 'src/app/shared/interfaces/report';
import { AutoComplete } from 'primeng/autocomplete';
import { count } from 'rxjs/operators';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})


export class ReportComponent implements OnInit {
  cols: any[];
  data: any;
  filteredCourse: any[];
  public course: any[];
  reportData: Report[];
  count: Report;
  formReport = new FormGroup({
    courses: new FormControl('')
  });


  constructor(
    private breadCrumbService: BreadcrumbService,
    private reportGenService: ReportService
  ) { }

  ngOnInit() {
    this.breadCrumbService.setPath([
      { label: 'ออกรายงาน', routerLink: '/report' },
    ]);
    this.getDataOfReport(null);

    this.cols = [
      { field: 'coursesName', header: 'ชื่อคอร์ส'},
      { field: 'genderFemale', header: 'หญิง' },
      { field: 'genderMale', header: 'หญิง' },
      { field: 'genderNotspec', header: 'ไม่ระบุ' },
      { field: 'tranTemple', header: 'การเดินทางของวัด' },
      { field: 'transport', header: 'การเดินทางด้วยตัวเอง'},
      { field: 'newStudent', header: 'คนใหม่'},
      { field: 'bangkok', header: 'กรุงเทพฯ'},
      { field: 'central', header: 'ภาคกลาง'},
      { field: 'sakon', header: 'สกลนคร'},
      { field: 'northEast', header: 'ภาคตะวันออกเฉียงเหนือ'},
      { field: 'north', header: 'ภาคเหนือ'},
      { field: 'east', header: 'ภาคตะวันออก'},
      { field: 'western', header: 'ภาคตะวันตก'},
      { field: 'south', header: 'ภาคใต้'},
  ];

  // ----------------- get Course Name -------------------
  this.reportGenService.getCourseName().subscribe( res => {
    this.course = res.data;
    this.course = this.course.map(
      data => {
        return {id : data.coursesId, name: data.coursesName};
      }
    );
  },
  err => {
    console.log(err['error']['message']);
  });

  }
 /***
  * ---------------getDataofReport(id):  Method for get Data for Report ------------------------
  * @param courseid
  *
  *  */
  getDataOfReport(id) {
    this.count = {
      genderMale: 0,
      genderFemale: 0,
      /*** gender that not specified. * */
      genderNotspec: 0,
      tranTemple: 0,
      transport: 0,
      newStudent: 0,
      bangkok: 0,
      central: 0,
      /*** จังหวัด สกลนคร * */
      sakon: 0,
      northEast: 0,
      north: 0,
      south: 0,
      east: 0,
      western: 0,
    };
    this.reportGenService.getDataByCourseId(id).subscribe(
      res => {
        this.reportData = [...res['data']];
        // console.log(this.reportData);
        this.reportData.forEach((couse:Report) =>{
          this.count = {
            genderMale: this.count.genderMale + couse.genderMale,
            genderFemale: this.count.genderFemale + couse.genderFemale,
            /*** gender that not specified. * */
            genderNotspec: this.count.genderNotspec + couse.genderNotspec,
            tranTemple: this.count.tranTemple + couse.tranTemple,
            transport: this.count.transport + couse.transport,
            newStudent: this.count.newStudent + couse.newStudent,
            bangkok: this.count.bangkok + couse.bangkok,
            central: this.count.central + couse.central,
            /*** จังหวัด สกลนคร * */
            sakon: this.count.sakon + couse.sakon,
            northEast: this.count.northEast + couse.northEast,
            north: this.count.north + couse.north,
            south: this.count.south + couse.south,
            east: this.count.east + couse.east,
            western: this.count.western + couse.western,
          }
        });
      }
    );
    // for (let key of this.reportData) {
      
    // }
  }

  /**
   * รับค่าจากแป้นพิมพ์
   * @param event
   */
  filterCourseMultiple(event) {
    const query = event.query;
    this.filteredCourse = this.filterCourse(query, this.course);
  }

  filterCourse(query, course: any[]): any[] {
    const filtered: any[] = [];
    for (let i = 0; i < course.length; i++) {
      const courses = course[i];
      if (courses.name.match(query)) {
        filtered.push(courses);
      }
    }
    return filtered;
  }

  onSelect(data) {
    const courseId = data.id;
    this.getDataOfReport(courseId);
  }
  onClear(event) {
    if (event.data === null) {
      this.getDataOfReport(null);
    }
  }

  onReset(event) {
    console.log(event);
  }





}
