import {inject, NgModule} from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn, Router,
  RouterModule,
  RouterStateSnapshot,
  Routes,
  UrlTree
} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {QuizzesComponent} from "./pages/quizzes/quizzes.component";
import {CreateQuizComponent} from "./pages/create-quiz/create-quiz.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {SingleQuizComponent} from "./pages/single-quiz/single-quiz.component";
import {ErrorComponent} from "./pages/error/error.component";
import {Observable} from "rxjs";
import {AxiosService} from "./axios.service";

const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
):
  | Observable<boolean | UrlTree>
  | Promise<boolean | UrlTree>
  | boolean
  | UrlTree => {
  let authToken = inject(AxiosService).getAuthToken();
  if(authToken == null) return inject(Router).navigate(["/login-page"]);
  return true;
};

const routes: Routes = [
  {path: 'login-page', component: LoginComponent},
  {path: 'register-page', component: RegisterComponent},
  {path: 'error-page/:err', component: ErrorComponent},

  {path: '', component: HomeComponent, canActivate: [authGuard]},
  {path: 'quizzes-page', component: QuizzesComponent, canActivate: [authGuard]},
  {path: 'quizzes-page/create-page', component: CreateQuizComponent, canActivate: [authGuard]},
  {path: 'single-quiz/:id', component: SingleQuizComponent, canActivate: [authGuard]},


  {path: '**', redirectTo: '/', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
