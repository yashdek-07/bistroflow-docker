import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-signup',
  templateUrl: './new-signup.component.html',
  styleUrl: './new-signup.component.css'
})
export class NewSignupComponent implements OnInit{

  signUpForm!: FormGroup;
  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.signUpForm = this.fb.group({
      username: ['', [Validators.required, Validators.pattern('^[a-zA-Z]+$')]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }

  passwordMatchValidator(formGroup: FormGroup): void {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;
    if (password !== confirmPassword) {
      formGroup.get('confirmPassword')?.setErrors({ mismatch: true });
    } else {
      formGroup.get('confirmPassword')?.setErrors(null);
    }
  }

  onSubmit(): void {
    if (this.signUpForm.valid) {
      console.log('Form Submitted!', this.signUpForm.value);
      const userData = {
        userName: this.signUpForm.get('username')?.value,
        emailId: this.signUpForm.get('email')?.value,
        password: this.signUpForm.get('password')?.value,
        contactNumber: this.signUpForm.get('phone')?.value,
        status: true,  // Default value
        role: 'user'   // Default value
      };

      this.userService.signUp(userData).subscribe(
        response => {
          console.log('Registration successful!', response);
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Registration error', error);
        }
      );

    }
  }

}
