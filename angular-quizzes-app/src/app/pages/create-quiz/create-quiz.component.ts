import {Component} from '@angular/core';
import {ToastrService} from "ngx-toastr";
import {AxiosService} from "../../axios.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-quiz',
  templateUrl: './create-quiz.component.html',
  styleUrls: ['./create-quiz.component.css'],
})
export class CreateQuizComponent {

  // Quiz
  quizTitle!: string;

  // Questions
  questionType: string = "SINGLE_SELECT";
  score: string = '';
  questionTitle: string = '';

  // Answers
  answersCount: any = 1;

  answer_1: string = '';
  answer_2: string = '';
  answer_3: string = '';
  answer_4: string = '';
  answer_5: string = '';
  answer_6: string = '';
  answer_7: string = '';
  answer_8: string = '';
  answer_9: string = '';
  correctAnswerId: string = '';
  correctAnswersId: string[] = [];

  // List Object
  questions: any = [];
  answers: any = [];

  constructor(private toastr: ToastrService, private axios: AxiosService, private router: Router) {
  }

  continueCreating(form: any) {
    this.quizTitle = form.value.title;
    this.openAddQuestion();
  }

  private openAddQuestion() {
    const form = document.getElementById('create-quiz') as HTMLFormElement;
    const form2 = document.getElementById('add-question') as HTMLFormElement;

    form.classList.remove('active-c');
    form.classList.add('hidden-c');

    form2.classList.remove('hidden-c');
    form2.classList.add('active-c');
  }

  setCorrectAnswerId(id: string) {
    this.correctAnswerId = id;
  }

  addCorrectAnswersId(id: string) {
    this.correctAnswersId.push(id);
  }

  deleteCorrectAnswerId() {
    this.correctAnswersId = [];
    this.correctAnswerId = '';
  }

  addQuestion(form: any) {
    let questionTypeForm = form.value.questionType;
    let scoreForm = String(form.value.score);
    let title = form.value.title;
    let object = {questionType: questionTypeForm, score: scoreForm, title: title};
    if (object.questionType != questionTypeForm
      || scoreForm != object.score
      || title != object.title
    ) {
      this.toastr.error('Question cannot be created!');
      return;
    }

    this.questions.push(object);

    if (this.answersCount == 2) {
      let answer1 = form.value.answer_1;
      let answer2 = form.value.answer_2;

      const objectToPush1 = {
        isCorrect: Boolean(this.correctAnswerId == '1') || Boolean(this.correctAnswersId.includes('1')),
        title: answer1
      };

      const objectToPush2 = {
        isCorrect: Boolean(this.correctAnswerId == '2') || Boolean(this.correctAnswersId.includes('2')),
        title: answer2
      };

      this.answers.push([objectToPush1, objectToPush2]);

      this.correctAnswersId = [];
      this.correctAnswerId = '';
      this.answer_1 = '';
      this.answer_2 = '';
    }

    if (this.answersCount == 3) {
      let answer3 = form.value.answer_3;
      let answer4 = form.value.answer_4;
      let answer5 = form.value.answer_5;

      const objectToPush1 = {
        isCorrect: Boolean(this.correctAnswerId == '1') || Boolean(this.correctAnswersId.includes('1')),
        title: answer3
      };

      const objectToPush2 = {
        isCorrect: Boolean(this.correctAnswerId == '2') || Boolean(this.correctAnswersId.includes('2')),
        title: answer4
      };

      const objectToPush3 = {
        isCorrect: Boolean(this.correctAnswerId == '3') || Boolean(this.correctAnswersId.includes('3')),
        title: answer5
      };
      this.answers.push([objectToPush1, objectToPush2, objectToPush3]);

      this.correctAnswersId = [];
      this.correctAnswerId = '';
      this.answer_3 = '';
      this.answer_4 = '';
      this.answer_5 = '';
    }

    if (this.answersCount == 4) {
      let answer6 = form.value.answer_6;
      let answer7 = form.value.answer_7;
      let answer8 = form.value.answer_8;
      let answer9 = form.value.answer_9;

      const objectToPush1 = {
        isCorrect: Boolean(this.correctAnswerId == '1') || Boolean(this.correctAnswersId.includes('1')),
        title: answer6
      };

      const objectToPush2 = {
        isCorrect: Boolean(this.correctAnswerId == '2') || Boolean(this.correctAnswersId.includes('2')),
        title: answer7
      };

      const objectToPush3 = {
        isCorrect: Boolean(this.correctAnswerId == '3') || Boolean(this.correctAnswersId.includes('3')),
        title: answer8
      };

      const objectToPush4 = {
        isCorrect: Boolean(this.correctAnswerId == '4') || Boolean(this.correctAnswersId.includes('4')),
        title: answer9
      };
      this.answers.push([objectToPush1, objectToPush2, objectToPush3, objectToPush4]);

      this.correctAnswerId = '';
      this.correctAnswersId = [];
      this.answer_6 = '';
      this.answer_7 = '';
      this.answer_8 = '';
      this.answer_9 = '';
    }

    // reset
    this.questionType = "SINGLE_SELECT";
    this.score = '';
    this.questionTitle = '';
    this.answersCount = 1;
    this.answer_1 = '';
    this.answer_2 = '';
    this.answer_3 = '';
    this.answer_4 = '';
    this.answer_5 = '';
    this.answer_6 = '';
    this.answer_7 = '';
    this.answer_8 = '';
    this.answer_9 = '';
    this.correctAnswerId = '';
    this.correctAnswersId = [];

    this.toastr.success('Question successfully added into Quiz', 'Question', {
      titleClass: "center",
      messageClass: "center"
    });
  }

  protected readonly Number = Number;

  sendToSpring() {
    const data = {
      title: this.quizTitle,
      options: this.answers,
      questions: this.questions
    }

    if (!data.title || data.options.isEmpty || data.questions.isEmpty) {
      window.location.reload();
      return;
    }

    this.axios.request('POST', '/create-quiz', data).then(UserDto => {
        window.location.reload();
        UserDto.status == 200 ? this.toastr.success('Quiz Successfully created', 'Quiz', {
          titleClass: "center",
          messageClass: "center"
        }) : this.toastr.error('Quiz cannot be created!');
      }
    ).catch(err => {
      this.router.navigate(['error-page/:err', {err: err}]).then(data => {
        console.log(data)
      });
    })
  }
}
