import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AccountDetails, AccountOperation } from '../model/account';
import { AccountsService } from '../services/accounts.service';
@Component({
  selector: 'app-banking-receipt',
  templateUrl: './banking-receipt.component.html',
  styleUrls: ['./banking-receipt.component.css'],
})
export class BankingReceiptComponent implements OnInit {
  accountId!: number;
  id!: string;
  acc!: AccountDetails;
  accountFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  accountObservable!: Observable<Array<AccountOperation>>;
  operationFormGroup!: FormGroup;
  errorMessage!: string;
  constructor(
    private fb: FormBuilder,
    private accountService: AccountsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId: this.fb.control(this.route.snapshot.params['id']),
    });

    this.operationFormGroup = this.fb.group({
      operationType: this.fb.control(null),
      amount: this.fb.control(0),
      description: this.fb.control(null),
      accountDestination: this.fb.control(null),
    });
    this.id = this.route.snapshot.params['id'];
    this.accountService.getAccountById(this.id).subscribe({
      next: (data) => {
        this.acc = data;
      },
    });
  }
  handleSearchAccount() {
    let accountId: string = this.accountFormGroup.value.accountId;
    this.accountObservable = this.accountService.getAccountListById(accountId);
  }
  gotoPage(page: number) {
    this.currentPage = page;
    this.handleSearchAccount();
  }
  handleAccountOperation() {
    let accountId: string = this.accountFormGroup.value.accountId;
    let amount: number = this.operationFormGroup.value.amount;
    let description: string = this.operationFormGroup.value.description;
    let accountDestination: string =
      this.operationFormGroup.value.accountDestination;
    let operationType = this.operationFormGroup.value.operationType;
    if (operationType == 'DEBIT') {
      this.accountService.debit(accountId, amount, description).subscribe({
        next: (data) => {
          alert('Success Debit');
          this.operationFormGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else if (operationType == 'CREDIT') {
      this.accountService.credit(accountId, amount, description).subscribe({
        next: (data) => {
          alert('Success Credit');
          this.operationFormGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          console.log(err);
        },
      });
    } else if (operationType == 'TRANSFER') {
      this.accountService
        .transfer(accountId, accountDestination, amount, description)
        .subscribe({
          next: (data) => {
            alert('Success Transfer');
            this.operationFormGroup.reset();
            this.handleSearchAccount();
          },
          error: (err) => {
            console.log(err);
          },
        });
    }
  }
  PrintThisPage() {
    window.print();
  }
}
