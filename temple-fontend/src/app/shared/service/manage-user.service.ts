import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { BehaviorSubject, Subject } from 'rxjs';
import { ApiConstants } from '../constants/ApiConstants';
import { AuthService } from './auth.service';
import { HttpClientService } from './http-client.service';
import { Member } from '../interfaces/member';

@Injectable()
export class ManageUserService {
  private user = new Subject<Member>();

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private httpService: HttpClientService
  ) {
  }

  createUser(dataUser) {
    return this.http.post(ApiConstants.baseURl + '/auth/register',
      dataUser, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access-token')}`
      }
    }).pipe(
      map(res => {
        return {
          status: res['result'],
          errorMessage: res['errorMessage']
        };
      }
      )
    );

  }

  getUser(id) {
    return this.http.get(ApiConstants.baseURl + `/members/${id}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access-token')}`
      }
    }).pipe(
      map((res) => {
        this.user.next(res['data'][0]);
        return {
          status: res['result'],
          data: res['data'][0],
        };
      })
    );
  }

  getUserOnline(): Subject<Member> {
    return this.user;
  };


  getAllUsersWithOutImg() {
    return this.http.get(ApiConstants.baseURl + '/members/getAllUsersWithOutImg', {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access-token')}`
      }
    }).pipe(
      map((res) => {
        const data = res['data']
          .map(member => {
            return {
              id: member['id'],
              titleName: member['titleName'],
              fname: member['fname'],
              lname: member['lname'],
              rolename: member['roleName'],
              email: member['email'],
              tel: member['tel']
            };
          });
        return {
          status: res['result'],
          data: data
        };
      })
    );
  }

  getAllUsers() {
    return this.http.get(ApiConstants.baseURl + '/members', {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access-token')}`
      }
    }).pipe(
      map((res) => {
        const data = res['data']
          .map(member => {
            return {
              id: member['id'],
              titleName: member['titleName'],
              fname: member['fname'],
              lname: member['lname'],
              rolename: member['roleName'],
              email: member['email'],
              tel: member['tel']
            };
          });
        return {
          status: res['result'],
          data: data
        };
      })
    );
  }

  updateUser(id, dataUser) {
    let res;
    const data = {
      ...dataUser,
    };
    if (this.authService.getRole().value === "admin") {
      res = this.httpService.put(ApiConstants.baseURl + `/members/updateByAdmin/${id}`, data)
      //res = this.httpService.put(ApiConstants.baseURl + `/members/${id}`, data)
    } else {
      res = this.httpService.put(ApiConstants.baseURl + `/members/${id}`, data)
    }
    return res.pipe(
      map(res => {
        return {
          status: res['result'],
          res: res
        };
      }));
  }

  deleteUser(id) {
    return this.http.delete(ApiConstants.baseURl + `/members/${id}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('access-token')}`
      }
    }).pipe(
      map(res => {
        return {
          status: res['result']
        };
      })
    );
  }

}
