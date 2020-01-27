import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConstants } from '../constants/ApiConstants';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient, private dashboard: DashboardService) { }
  getDataChart() {
    return this.http.get(ApiConstants.baseURl + `/dashboard`);
  }
}
