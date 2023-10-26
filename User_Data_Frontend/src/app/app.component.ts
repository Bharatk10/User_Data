import { Component } from '@angular/core';
import { UserserviceService } from 'src/userservice.service';
import * as xls from 'xlsx'
import { User } from 'src/model/User';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  users:any=''
  user: any=''
  data:any

  constructor(private Service:UserserviceService,private toastr:ToastrService){}

  downloadExcel(form:any) {
    console.log(form.value)
    this.Service.downloaddata(form.value).subscribe((data) => {
      const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'userdata.xls';
      a.click();
      window.URL.revokeObjectURL(url); 
    });
  }




  readExcelFile(event: any): void {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.readAsArrayBuffer(file);

    reader.onload = (e) => {
      const data = e.target?.result;
      const workbook = xls.read(data, { type: 'array' });

      const sheetname = workbook.SheetNames[0];
      const sheet1 = workbook.Sheets[sheetname];

      this.users = xls.utils.sheet_to_json<User>(sheet1, { raw: false });

      console.log(this.users);

     

      this.saveuser();
    
    };

    
  }


  saveuser(){
      for(var i=0;i<this.users.length;i++){
        this.user=this.users[i];
        console.log(this.user)
        this.Service.saveUsers(this.user).subscribe(
          (response) => {
            this.toastr.success('Data saved successfully');
            console.log('Data saved successfully');
          },
          (error) => {
            this.toastr.error('Data not updated');
            console.error('Error saving data');
          }
        );

      }

          

  }

}


