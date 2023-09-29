import {Component} from '@angular/core';
import {AxiosService} from "../../axios.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  password: string = "";
  username: string = "";

  constructor(private axiosService: AxiosService, private toastr: ToastrService) {
  }

  onSubmitLogin(form: any): void {
    this.username = form.value.username;
    this.password = form.value.password;

    if(!this.username || !this.password) {
      this.toastr.error('Enter correct data!');
      return;
    }

    this.axiosService.request(
      "POST",
      "/login",
      {
        username: this.username,
        password: this.password
      }
    ).then(rs => {
      if(rs.status == 200) {
        this.axiosService.setAuthToken(rs.data.token);
        window.location.href="/"
      }
    })
  }
}
