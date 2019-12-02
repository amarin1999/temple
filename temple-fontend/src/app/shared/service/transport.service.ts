import { Injectable } from '@angular/core';
import { HttpClientService } from './http-client.service';
import { ApiConstants } from '../constants/ApiConstants';
import { map } from 'rxjs/operators';
import { Transportation } from '../interfaces/transportation';

@Injectable({
  providedIn: 'root'
})
export class TransportService {

  constructor(
    private http: HttpClientService
  ) { }

  getTranSport() {
    return this.http.get(ApiConstants.baseURl + '/transportations')
      .pipe(
        map((res: any[]) => {
          return res['data'].map(data => {
            return {
              id: data['id'],
              name: data['name'],
              status: data['status']
            }
          })
        })
      );
  }

  getTranSportToEdit() {
    return this.http.get(ApiConstants.baseURl + '/transportations')
      .pipe(
        map((res) => {
          return {
            status: res['result'],
            data: res['data']
          }
        })
      );
  }

  createTransportation(transportation: Transportation) {
    return this.http.post(ApiConstants.baseURl + '/transportations', transportation)
      .pipe(
        map(res => {
          return {
            status: res['result'],
            data: res['data'][0]
          }
        })
      )
  }

  updateTransportation(transportation: Transportation){
    return this.http.put(`${ApiConstants.baseURl}/transportations/${transportation.id}`,transportation)
      .pipe(
        map(res =>{
          return {
            status: res['result'],
            data: res['data'][0]
          }
        })
      )
  }

  deleteTransportation(id:number){
    return this.http.put(`${ApiConstants.baseURl}/transportations/delete/${id}`,{id: id})
      .pipe(
        map(res=>{
          return{
            status: res['result']
          }
        })
      )
  }

}
