import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../shared/service/auth.service';
import { ManageUserService } from 'src/app/shared/service/manage-user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Member } from 'src/app/shared/interfaces/member';
import { map, switchMap } from 'rxjs/operators';

@Component({
    selector: 'app-topbar',
    templateUrl: './topbar.component.html',
    styleUrls: ['./topbar.component.css']
})
export class TopbarComponent implements OnInit {
    public isLoggedIn: boolean;
    public userData: Member;


    constructor(
        private authService: AuthService,
        private manageUser: ManageUserService,
    ) {
    }

    ngOnInit() {
        this.authService.isLoggedIn().subscribe(res => {
            this.isLoggedIn = res;
            this.isLoggedIn ? this.manageUser.getUser(localStorage.getItem('userId')).subscribe() : undefined;           
        })

        this.manageUser.getUserOnline().subscribe(res => {
            this.userData = { ...res };
        })
    }


    logout() {
        this.authService.logout();
        delete this.userData;
    }

}
