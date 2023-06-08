package com.harrisonwells.funding.models;

import lombok.Data;

@Data
public class RegisteredUser {
    private String firstName;
    private String lastName;
    private String username;
    private String nationalID;
    private String contactNumber;
    private String email;
    private String category;
    private String address;
    private String password;
    private String confirmPassword;

}
