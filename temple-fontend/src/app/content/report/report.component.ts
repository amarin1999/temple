import { Component, OnInit } from '@angular/core';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { FormGroup, FormControl } from '@angular/forms';
import { CourseService } from '../courses/shared/course.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

  cols: any[];
  filteredCourse: any[];
  public course: any[];

  formReport = new FormGroup({
    courses: new FormControl('')
  });

  constructor(
    private breadCrumbService: BreadcrumbService,
    private courseService: CourseService
  ) { }

  ngOnInit() {
    this.breadCrumbService.setPath([
      { label: 'ออกรายงาน', routerLink: '/report' },
    ]);

    this.cols = [
      { field: 'gender', header: 'เพศ' },
      {field: 'transportation', header: 'การเดินทาง' },
      { field: 'newUser', header: 'คนใหม่' },
      { field: 'region', header: 'ภูมิภาค' }
  ];

  this.courseService.getCourses().subscribe( res => { 
    this.course = res.data;
    this.course = this.course.map(
      data => {
        return {id : data.id, name: data.name}
      }
    );
    // console.log('course', this.course);
  },
  err => {
    console.log(err['error']['message']);
  });

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

  }
  onClear(event) {
    if (event.data == null) {
      // console.log('data');
    }
  }


}
