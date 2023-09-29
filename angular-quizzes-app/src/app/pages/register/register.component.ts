import {Component} from '@angular/core';
import {AxiosService} from "../../axios.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  name: string = "";
  surname: string = "";
  username: string = "";
  password: string = "";
  type: string = "";

  constructor(private axiosService: AxiosService, private toastr: ToastrService) {
  }

  onSubmitRegister(form: any) : void{
    this.name = form.value.name;
    this.surname = form.value.surname;
    this.username = form.value.username;
    this.password = form.value.password;
    this.type = form.value.type;

    if(!this.name || !this.surname || !this.username || !this.password || !this.type) {
      this.toastr.error('Enter correct data!');
      return;
    }

    this.axiosService.request(
      "POST",
      "/register",
      {
        name: this.name,
        surname: this.surname,
        username: this.username,
        password: this.password,
        type: this.type
      }
    ).then(rs => {
      if(rs.status == 201) {
        this.axiosService.setAuthToken(rs.data.token);
        window.location.href = '/';
      }
    })
  }
}
