import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AxiosService} from "../../axios.service";

@Component({
  selector: 'app-single-quiz',
  templateUrl: './single-quiz.component.html',
  styleUrls: ['./single-quiz.component.css']
})
export class SingleQuizComponent implements OnInit {

  id!: number;
  quizTitle: string = '';
  questions: any = [];
  options: any = [];

  optionsResult: any;

  currentAnswer: any[] = [];
  answersScores: number = 0;

  values: any[] = [];
  answers: any = [];

  scores: number = 0;

  end: boolean = false;

  constructor(private router: Router, private axios: AxiosService) {
    let url: string = this.router.url;
    if (url.includes(';id=')) {
      let strings = url.split(';id=');
      this.id = Number(strings[1]);

      let getQuizDataRequest = {
        id: this.id
      }

      this.axios.request("POST", '/get-data-by-quiz-id', getQuizDataRequest).then(rs => {
        if (rs.status != 200) return;
        let data = rs.data;
        this.quizTitle = data.title;
        this.questions = data.questions;
        this.options = data.options;
      });
    }
  }

  getAllOptionsOfQuestion(question: any): typeof this.optionsResult {
    return this.optionsResult = this.options.filter((option: any) => {
      return option.question.id === question.id;
    });
  }

  addAnswer(question: any) {
    let id = question.id;

    this.answers.push(this.values);
    this.currentAnswer = this.values;
    this.values = [];

    let elementById = document.getElementById(`${id}`) as HTMLButtonElement;
    elementById.setAttribute('disabled', '');

    this.calculateAnswers(question)

    this.end = this.answerAndQuestionLengths();
    if (this.end) this.sendAnswersToSpring();
  }

  onChangeRadio(e: string, id: number) {
    let elementById = document.getElementById(`${id}`) as HTMLButtonElement;
    if (elementById.hasAttribute('disabled')) return;
    this.values.length = 0;
    this.values.push(e);
  }

  onChangeCheckbox(value: string, id: number) {
    let elementById = document.getElementById(`${id}`) as HTMLButtonElement;
    if (elementById.hasAttribute('disabled')) return;

    if (this.values.includes(value)) {
      this.values = this.values.filter((item) => item !== value);
    } else {
      this.values.push(value);
    }
  }

  calculateAnswers(question: any) {
    let currentQuestionOptions = [];
    for (const option of this.options) {
      option.question.id == question.id ? currentQuestionOptions.push(option) : null;
    }
    let filteredCurrentOptions = currentQuestionOptions.filter(option => option.isCorrect);
    let values = this.currentAnswer;

    if (values.length == 0) return;

    if (values.length == 1 && filteredCurrentOptions.length == 1) {
      if (values.at(0).isCorrect) {
        this.answersScores += Number(question.score);
        return
      }
    } else if (values.toString() == filteredCurrentOptions.toString()) {
      this.answersScores += Number(question.score);
    }
  }


  ngOnInit() {
    setTimeout(() => {
      for (const question of this.questions) {
        this.scores += Number(question.score);
      }
    }, 2000)
  }

  answerAndQuestionLengths() {
    if (this.questions.length == 0 || this.answers.length == 0) return false;

    return this.questions.length == this.answers.length;
  }


  sendAnswersToSpring() {
    const createAnswerDto = {
      answers: this.answers
    }
    console.log(this.answers);
    console.log(this.options);
    console.log(createAnswerDto);
    this.axios.request("POST", "/create-answers", createAnswerDto).then(object => {
      console.log(object);
    })
  }

  protected readonly String = String;
}
