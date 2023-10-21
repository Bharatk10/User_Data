import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { User } from './model/User';



@Injectable({
  providedIn: 'root'
})
export class UserserviceService {

  private userUrl = 'http://localhost:8080/api/user';

  constructor(private http:HttpClient) { }

  downloaddata() {
    return this.http.get(this.userUrl, { responseType: 'blob' });
  }

  saveUsers(user:any) {

    console.log(user)

    console.log(user.First_Name);
    let params= new HttpParams().set("first_name",user.First_Name).set("last_name",user.Last_Name)
    .set("date_of_birth",user.Date_of_Birth).set("city",user.City);
    return this.http.post(this.userUrl,params);
  }
}
