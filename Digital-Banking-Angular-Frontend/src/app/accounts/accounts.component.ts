import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import Swal from 'sweetalert2';
import { AccountDetails } from '../model/account';
import { AccountsService } from '../services/accounts.service';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css'],
})
export class AccountsComponent implements OnInit {
  accountFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  accountObservable!: Observable<AccountDetails>;
  operationFormGroup!: FormGroup;
  errorMessage!: string;
  constructor(
    private fb: FormBuilder,
    private accountService: AccountsService,
    public storage: StorageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId: this.fb.control(''),
    });
    this.operationFormGroup = this.fb.group({
      operationType: this.fb.control(null),
      amount: this.fb.control(0),
      description: this.fb.control(null),
      accountDestination: this.fb.control(null),
    });
  }
  handleSearchAccount() {
    let accountId: string = this.accountFormGroup.value.accountId;
    this.accountObservable = this.accountService
      .getAccountPerPage(accountId, this.currentPage, this.pageSize)
      .pipe(
        catchError((err) => {
          this.errorMessage = err.message;
          return throwError(err);
        })
      );
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
          Swal.fire('Good job!', 'Success Debit !', 'success');
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
          Swal.fire('Good job!', 'Success Credit !', 'success');
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
            Swal.fire('Good job!', 'Success Transfer !', 'success');
            this.operationFormGroup.reset();
            this.handleSearchAccount();
          },
          error: (err) => {
            console.log(err);
          },
        });
    }
  }
}
