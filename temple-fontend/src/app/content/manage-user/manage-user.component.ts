import { Component, OnInit } from '@angular/core';
import { ManageUserService } from 'src/app/shared/service/manage-user.service';
import { MenuItem, MessageService } from 'primeng/api';
import { BreadcrumbService } from 'src/app/shared/service/breadcrumb.service';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-manage-user',
  templateUrl: './manage-user.component.html',
  styleUrls: ['./manage-user.component.css']
})
export class ManageUserComponent implements OnInit {

  public personal: any[];
  public menu: MenuItem[];
  cols: { field: string; header: string; }[];

  constructor(
    private manageUser: ManageUserService,
    private breadCrumbService: BreadcrumbService,
    private router: Router,
    public spinner: NgxSpinnerService
  ) { }

  ngOnInit() {
    this.spinner.show();
    this.breadCrumbService.setPath([
      { label: 'จัดการสมาชิก', routerLink: '/users' },
    ]);
    this.spinner.show();
    this.manageUser.getAllUsersWithOutImg().toPromise().then(res => {
      if (res['status'] === 'Success') {
        this.personal = res.data;
      }
    }).catch(e =>
      console.log(e['error']['errorMessage'])
    ).finally(() => this.spinner.hide());

    this.menu = [
      { label: '', icon: 'pi pi-home', routerLink: '/' },
      { label: 'Manange Locations : จัดการสถานที่' },
    ];

    this.cols = [
      { field: 'name', header: 'name' },
      { field: 'rolename', header: 'rolename' },
      { field: 'contact', header: 'contact' }
    ];
    this.spinner.hide();
  }

  deleteUser(id) {
    this.spinner.show();
    this.manageUser.deleteUser(id).toPromise()
      .then(res => {
        if (res['status'] === 'Success') {
          const index = this.personal.findIndex(e => e.id === id);
          this.personal = [
            ...this.personal.slice(0, index),
            ...this.personal.slice(index + 1)
          ];
        }
      }).catch((e) => console.log(e['error']['errorMessage'])
      ).finally(() => this.spinner.hide());
    this.spinner.hide();
  }
  public onRowSelect(e) {
    this.router.navigate(['/profile', e.data['id']]);
  }
}
