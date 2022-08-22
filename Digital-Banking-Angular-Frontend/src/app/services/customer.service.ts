import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../model/customer';
@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}
  public getCustomers(): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(
      environment.backendHost + '/api/cust/customers'
    );
  }
  public getCustomersById(id: object): Observable<Customer> {
    return this.http.get<Customer>(
      environment.backendHost + '/api/cust/customers/' + id
    );
  }
  public searchCustomers(keyword: string): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(
      environment.backendHost + '/api/cust/customers/search?keyword=' + keyword
    );
  }
  public saveCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(
      environment.backendHost + '/api/cust/customers',
      customer
    );
  }
  public updateCustomer(id: object, customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(
      environment.backendHost + '/api/cust/customers/' + id,
      customer
    );
  }
  public deleteCustomer(id: number) {
    return this.http.delete(
      environment.backendHost + '/api/cust/customers/' + id
    );
  }
}
