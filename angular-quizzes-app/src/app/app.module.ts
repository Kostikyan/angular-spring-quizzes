import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {HeaderComponent} from './layouts/header/header.component';
import {HomeComponent} from './pages/home/home.component';
import {QuizzesComponent} from './pages/quizzes/quizzes.component';
import {CreateQuizComponent} from './pages/create-quiz/create-quiz.component';
import {LoginComponent} from './pages/login/login.component';
import {RegisterComponent} from './pages/register/register.component';
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule} from "@angular/forms";
import {AxiosService} from "./axios.service";
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { QuizCardComponent } from './layouts/quiz-card/quiz-card.component';
import { SingleQuizComponent } from './pages/single-quiz/single-quiz.component';
import { ErrorComponent } from './pages/error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    QuizzesComponent,
    CreateQuizComponent,
    LoginComponent,
    RegisterComponent,
    QuizCardComponent,
    SingleQuizComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ToastrModule.forRoot(),
  ],
  providers: [AxiosService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
