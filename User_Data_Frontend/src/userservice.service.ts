import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';





@Injectable({
  providedIn: 'root'
})
export class UserserviceService {

  private userUrl = 'http://localhost:8080/api/user';

  constructor(private http:HttpClient) { }

  downloaddata(data:any) {
    console.log(data.field1)
    let params = new HttpParams().set("field1",data.field1).set("field2",data.field2).set("field3",data.field3).set("field4",data.field4)
    const fullUrl = `${this.userUrl}?${params.toString()}`;
    return this.http.get(fullUrl, { responseType: 'blob', });
  }

  saveUsers(user:any) {

    console.log(user)

    console.log(user.First_Name);
    let params= new HttpParams().set("first_name",user.First_Name).set("last_name",user.Last_Name)
    .set("date_of_birth",user.Date_of_Birth).set("city",user.City);
    return this.http.post(this.userUrl,params);
  }
}
 