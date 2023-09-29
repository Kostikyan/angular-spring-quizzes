import {AfterContentInit, Component} from '@angular/core';
import {AxiosService} from "../../axios.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements AfterContentInit {
  token: string | null = null;

  constructor(private axiosService: AxiosService, private router: Router) {
  }

  ngAfterContentInit(): void {
    let authToken = this.axiosService.getAuthToken();
    if (authToken !== null) {
      this.token = authToken;
      console.log(this.token);
    } else {
      this.router.navigate(['/login-page']).then(data => {
        console.log(data)
      });
      this.token = null;
    }
  }

  logout() {
    this.axiosService.setAuthToken(null);
  }


}
