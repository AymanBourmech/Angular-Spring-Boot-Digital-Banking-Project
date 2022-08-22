import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CustomersComponent } from './customers/customers.component';
import { AccountsComponent } from './accounts/accounts.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NewCustomerComponent } from './new-customer/new-customer.component';
import { CustomerAccountsComponent } from './customer-accounts/customer-accounts.component';
import { AccountCustomerComponent } from './account-customer/account-customer.component';
import { HomeComponent } from './home/home.component';
import { BankingReceiptComponent } from './banking-receipt/banking-receipt.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { httpInterceptorProviders } from './services/interceptor.service';
import { FooterComponent } from './footer/footer.component';
import { EditcustomerComponent } from './editcustomer/editcustomer.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CustomersComponent,
    AccountsComponent,
    NewCustomerComponent,
    CustomerAccountsComponent,
    AccountCustomerComponent,
    HomeComponent,
    BankingReceiptComponent,
    LoginComponent,
    RegisterComponent,
    FooterComponent,
    EditcustomerComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent],
})
export class AppModule {}
