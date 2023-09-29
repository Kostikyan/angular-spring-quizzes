import {Component, OnInit} from '@angular/core';
import {AxiosService} from "../../axios.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-quiz-card',
  templateUrl: './quiz-card.component.html',
  styleUrls: ['./quiz-card.component.css']
})
export class QuizCardComponent implements OnInit {

  quizzes: any;

  constructor(private axios: AxiosService, private router: Router) {
  }

  ngOnInit(): void {
    this.axios.request("GET", '/get-quizzes', {}).then(
      rs => {
        if (rs.status == 200) {
          this.quizzes = rs.data;
        }
      }
    ).catch(err => {
      console.log(err)
    })
  }

  singleQuiz(id: number) {
    this.router.navigate(['single-quiz/:id', {id: id}]).then(data => {
      console.log(data)
    });
  }

  protected readonly Number = Number;
}
