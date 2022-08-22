import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AccountDetails, AccountOperation } from '../model/account';

@Injectable({
  providedIn: 'root',
})
export class AccountsService {
  constructor(private http: HttpClient) {}
  public getAccountPerPage(
    accountId: string,
    page: number,
    size: number
  ): Observable<AccountDetails> {
    return this.http.get<AccountDetails>(
      environment.backendHost +
        '/api/acc/accounts/' +
        accountId +
        '/pageOperations?page=' +
        page +
        '&size' +
        size
    );
  }
  public getAccountListById(
    accountId: string
  ): Observable<Array<AccountOperation>> {
    return this.http.get<Array<AccountOperation>>(
      environment.backendHost + '/api/acc/accounts/' + accountId + '/operations'
    );
  }
  public debit(accountId: string, amount: number, description: string) {
    let data = {
      accountId: accountId,
      amount: amount,
      description: description,
    };
    return this.http.post(
      environment.backendHost + '/api/acc/accounts/debit',
      data
    );
  }
  public credit(accountId: string, amount: number, description: string) {
    let data = {
      accountId: accountId,
      amount: amount,
      description: description,
    };
    return this.http.post(
      environment.backendHost + '/api/acc/accounts/credit',
      data
    );
  }
  public transfer(
    accountSource: string,
    accountDestination: string,
    amount: number,
    description: string
  ) {
    let data = {
      accountSource,
      accountDestination,
      amount,
      description,
    };
    return this.http.post(
      environment.backendHost + '/api/acc/accounts/transfer',
      data
    );
  }
  public getAccountByCustomer(id: object): Observable<Array<AccountDetails>> {
    return this.http.get<Array<AccountDetails>>(
      environment.backendHost + '/api/acc/accounts/customers/' + id
    );
  }

  public getAccountById(id: string): Observable<AccountDetails> {
    return this.http.get<AccountDetails>(
      environment.backendHost + '/api/acc/accounts/' + id
    );
  }
}
