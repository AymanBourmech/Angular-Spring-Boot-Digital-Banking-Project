import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../model/customer';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-editcustomer',
  templateUrl: './editcustomer.component.html',
  styleUrls: ['./editcustomer.component.css'],
})
export class EditcustomerComponent implements OnInit {
  id!: object;
  customer: Customer = new Customer();
  // newCustomerFormGroup!: FormGroup;
  constructor(
    private router: ActivatedRoute,
    private route: Router,
    private customerService: CustomerService // private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.customer = new Customer();
    this.id = this.router.snapshot.params['id'];
    this.customerService
      .getCustomersById(this.id)
      .subscribe((data) => (this.customer = data));

    // this.newCustomerFormGroup = this.fb.group({
    //   name: this.fb.control(this.customer.name, [
    //     Validators.required,
    //     Validators.minLength(4),
    //   ]),
    //   email: this.fb.control(this.customer.email, [
    //     Validators.required,
    //     Validators.email,
    //   ]),
    // });
  }
  editCustomer() {
    this.customerService
      .updateCustomer(this.id, this.customer)
      .subscribe((data) => this.route.navigateByUrl('/navbar/customers'));
  }
}
