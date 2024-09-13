import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-new-login',
  templateUrl: './new-login.component.html',
  styleUrl: './new-login.component.css'
})
export class NewLoginComponent {
  loginForm: FormGroup;
  errorMessage!: string;

  constructor(private fb: FormBuilder, private authService: UserService,  private router: Router,) {
    
    this.loginForm = this.fb.group({
      emailId: ['', [Validators.required, Validators.email]], // Updated form control name
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get emailId() {
    return this.loginForm.get('emailId');
  }

  get password() {
    return this.loginForm.get('password');
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginRequest: LoginRequest = {
        emailId: this.emailId?.value, // Updated to use emailId
        password: this.password?.value
      };

      this.authService.login(loginRequest).subscribe(
        response => {
          console.log('Login successful', response);
          const token = response.data;
          this.authService.saveToken(token);
          // Handle successful login...redirect to dashboard page
          // Redirect to Orders page after successful login
          this.router.navigate(['/order']);
        },
        error => {
          this.errorMessage = 'Login failed. Please check your email and password.';
          console.error('Login error', error);
        }
      );
    } else {
      this.loginForm.markAllAsTouched(); // Trigger validation messages
    }
  }
}

// Define the structure of the login request object
interface LoginRequest {
  emailId: string; // Updated to match backend expectation
  password: string;
}
