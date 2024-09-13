package com.bistroflow.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    @NotBlank(message = "User name is mandatory")
    @Size(max = 255, message = "User name cannot exceed 255 characters")
    private String userName;

    @Column(name = "phone_number")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number should only contain digits and optional '+' prefix")
    private String contactNumber;

    @Column(name = "email_id")
    @NotBlank(message = "Email ID is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email ID cannot exceed 255 characters")
    private String emailId;

    @Column(name = "role")
//    @NotBlank(message = "Role is mandatory")
    @Pattern(regexp = "^(admin|user)$", message = "Role must be either 'admin' or 'user'")
    private String role;

    @Column(name = "status")
    private boolean status;

    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    //@Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters long")
    private String password;

}
