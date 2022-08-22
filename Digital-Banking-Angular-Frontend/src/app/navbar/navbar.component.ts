import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthService } from '../services/auth.service';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  constructor(
    public authService: AuthService,
    private router: Router,
    public storage: StorageService
  ) {}

  ngOnInit(): void {}
  handleLogout() {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this !",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Disconnect !',
    }).then((result) => {
      if (result.isConfirmed) {
        this.storage.clean();
        this.router.navigateByUrl('/login');
        Swal.fire(
          'Good job!',
          'Your logout has been successfully completed!',
          'success'
        );
      }
    });
  }
}
