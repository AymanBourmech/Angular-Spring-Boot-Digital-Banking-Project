import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AccountDetails } from '../model/account';
import { Customer } from '../model/customer';
import { AccountsService } from '../services/accounts.service';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-accounts',
  templateUrl: './customer-accounts.component.html',
  styleUrls: ['./customer-accounts.component.css'],
})
export class CustomerAccountsComponent implements OnInit {
  accounts!: Observable<Array<AccountDetails>>;
  account!: Observable<AccountDetails>;
  customerId!: object;
  customer!: Observable<Customer>;

  cust: Customer = new Customer();
  constructor(
    private accountService: AccountsService,
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['id'];
    this.accounts = this.accountService.getAccountByCustomer(this.customerId);
    this.customerService.getCustomersById(this.customerId).subscribe({
      next: (data) => {
        this.cust = data;
      },
    });
  }
  handleAccountPerCustomer(account: AccountDetails) {
    this.router.navigateByUrl('/navbar/account-customer/' + account.id);
  }
  BankingReceiptPage(account: AccountDetails) {
    this.router.navigateByUrl('/navbar/banking-receipt/' + account.id);
  }
}
