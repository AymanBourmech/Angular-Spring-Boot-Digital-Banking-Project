import { AuthentificationGuard } from './guards/authentification.guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountCustomerComponent } from './account-customer/account-customer.component';
import { AccountsComponent } from './accounts/accounts.component';
import { BankingReceiptComponent } from './banking-receipt/banking-receipt.component';
import { CustomerAccountsComponent } from './customer-accounts/customer-accounts.component';
import { CustomersComponent } from './customers/customers.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NewCustomerComponent } from './new-customer/new-customer.component';
import { RegisterComponent } from './register/register.component';
import { EditcustomerComponent } from './editcustomer/editcustomer.component';

const routes: Routes = [
  {
    path: 'navbar',
    component: NavbarComponent,
    canActivate: [AuthentificationGuard],
    children: [
      {
        path: 'customers',
        component: CustomersComponent,
      },
      {
        path: 'accounts',
        component: AccountsComponent,
      },
      {
        path: 'new-customer',
        component: NewCustomerComponent,
      },
      { path: 'editcustomer/:id', component: EditcustomerComponent },
      {
        path: 'customer-accounts/:id',
        component: CustomerAccountsComponent,
      },
      {
        path: 'account-customer/:id',
        component: AccountCustomerComponent,
      },
      {
        path: 'banking-receipt/:id',
        component: BankingReceiptComponent,
      },
      {
        path: 'home',
        component: HomeComponent,
      },
    ],
  },
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
