import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ApiConstants } from '../constants/ApiConstants';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ForgetPassService {

  constructor(
    private http: HttpClient
  ) { }

  getUserForgetInfo(idCard, username, phoneNumber) {
    const body = {
      idCard, username, phoneNumber
    };
    return this.http.post(ApiConstants.baseURl + '/forgetpass/', body);
    // .pipe(
    //   map((res) => {
    //     return {
    //       status: res['result'],
    //       data: res['data'],
    //       stringData: res['stringData'],
    //       errorMessage: res['errorMessage'],
    //       code: res['code']
    //     };
    //   })
    // );
  }
}