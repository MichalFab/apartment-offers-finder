import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './auth/guards/auth.guard';
import { LoginComponent } from './auth/components/login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { SearchCriteriaComponent} from "./pages/search-criteria/search-criteria.component";
import {OffersListComponent} from "./pages/offers-list/offers-list.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent},
  { canActivate: [AuthGuard], path: '', component: OffersListComponent },
  { canActivate: [AuthGuard], path: 'report', component: SearchCriteriaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
