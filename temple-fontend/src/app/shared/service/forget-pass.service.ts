import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {ApiConstants} from '../constants/ApiConstants';

@Injectable({
  providedIn: 'root'
})
export class ForgetPassService {

  constructor(
    private router: Router,
    private http: HttpClient
  ) { }

  getUserForgetInfo(email, username){
    const body = {
      email, username
    };
    return this.http.post(ApiConstants.baseURl + '/forgetpass/', body);
  }
}