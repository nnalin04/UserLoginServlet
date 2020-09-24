package com.bridgelabz.userloginwebapp.model;

public class User {

    public int userId;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phoneNo;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
