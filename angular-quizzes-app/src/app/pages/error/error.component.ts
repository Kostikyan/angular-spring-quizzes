import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent {
  err: any;

  constructor(private router: Router) {
    let url = router.url;
    if (url.includes(';err=')) {
      let strings = url.split(";err=");
      let error = strings[1];
      this.err = error.replaceAll('%20', ' ');
    }

  }
}
