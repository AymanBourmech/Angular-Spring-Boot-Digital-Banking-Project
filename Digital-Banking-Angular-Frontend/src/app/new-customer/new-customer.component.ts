import { CustomerService } from './../services/customer.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Customer } from '../model/customer';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css'],
})
export class NewCustomerComponent implements OnInit {
  newCustomerFormGroup!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.newCustomerFormGroup = this.fb.group({
      name: this.fb.control(null, [
        Validators.required,
        Validators.minLength(4),
      ]),
      email: this.fb.control(null, [Validators.required, Validators.email]),
    });
  }
  handleSaveCustomer() {
    let customer: Customer = this.newCustomerFormGroup?.value;
    this.customerService.saveCustomer(customer).subscribe({
      next: (data) => {
        Swal.fire(
          'Good job!',
          'Customer has been successfully saved !',
          'success'
        );
        this.router.navigateByUrl('/navbar/customers');
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
