import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchCriteriaComponent } from './pages/search-criteria/search-criteria.component';
import { LoginComponent } from './auth/components/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtTokenInterceptor } from './auth/interceptors/jwt.token.interceptor';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import { RegistrationComponent } from './registration/registration.component';
import { OffersListComponent } from './pages/offers-list/offers-list.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchCriteriaComponent,
    LoginComponent,
    FooterComponent,
    HeaderComponent,
    RegistrationComponent,
    OffersListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtTokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
