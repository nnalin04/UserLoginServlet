package com.bridgelabz.userloginwebapp.controler;

import com.bridgelabz.userloginwebapp.Repositories.UserRepo;
import com.bridgelabz.userloginwebapp.configure.Database;
import com.bridgelabz.userloginwebapp.exception.UserLoginException;
import com.bridgelabz.userloginwebapp.model.User;

import java.sql.SQLException;

public class UserService {
    UserRepo userRepo;

    public UserService() {
        userRepo = new UserRepo(Database.USER_REGISTRATION.url, Database.USER_REGISTRATION.userId,
                Database.USER_REGISTRATION.password, Database.USER_REGISTRATION.tableName);
    }

    public void registerUser(User user) throws SQLException {
        user.firstName = checkFieldValidation(UserRegistration.ValidatorPat.NAME,
                user.firstName, "firstName");
        user.lastName = checkFieldValidation(UserRegistration.ValidatorPat.NAME,
                user.lastName, "lastName");
        user.email = checkFieldValidation(UserRegistration.ValidatorPat.EMAIL,
                user.email, "email");
        user.password = checkFieldValidation(UserRegistration.ValidatorPat.PASSWORD,
                user.password, "password");
        user.phoneNo = checkFieldValidation(UserRegistration.ValidatorPat.MOBILE,
                user.phoneNo, "phone no.");
        userRepo.saveUserInDatabase(user);
        System.out.println("User registered");
    }

    public boolean loginUser(String email, String password) throws UserLoginException, SQLException {
        boolean loginResult = false;
        User user = userRepo.findByEmailId(email);
        if (user != null && user.password.equals(password))
            loginResult = true;
        return loginResult;
    }

    public void updateUser(User user) throws SQLException {
        System.out.println("What do you want to up date");
        System.out.println("1 - email");
        System.out.println("2 - password");
        System.out.println("3 - phoneNo");
        int choice = RegisterUser.takingIntInput();
        RegisterUser.takingStringInput();
        switch(choice) {
            case 1:
                System.out.println("Enter the new email");
                String newEmail = RegisterUser.takingStringInput();

                newEmail = checkFieldValidation(UserRegistration.ValidatorPat.EMAIL,
                        newEmail, "email");
                user.email = userRepo.updateUser(user.email, newEmail, "email").email;
                break;
            case 2:
                System.out.println("Enter the new password");
                String newPassword = RegisterUser.takingStringInput();

                newPassword = checkFieldValidation(UserRegistration.ValidatorPat.PASSWORD,
                        newPassword, "password");
                user.password = userRepo.updateUser(user.email, newPassword, "password").password;
                break;
            case 3:
                System.out.println("Enter the new phoneNo");
                String newPhoneNo = RegisterUser.takingStringInput();
                newPhoneNo = checkFieldValidation(UserRegistration.ValidatorPat.MOBILE,
                        newPhoneNo, "phoneNo");
                user.phoneNo = userRepo.updateUser(user.email, newPhoneNo, "phoneNo").phoneNo;
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public void deleteUser(String email) throws SQLException {
        userRepo.deleteByEmailId(email);
    }

    private String checkFieldValidation(UserRegistration.ValidatorPat pattern, String input, String type)
            throws SQLException {

        while (userRepo.findByEmailId(input).email != null && type == "email"){
            System.out.println(type+" already present please enter different "+type);
            input = RegisterUser.takingStringInput();
        }

        while(!UserRegistration.validateInput(input, pattern)){
            System.out.println("Invalid "+type+" please re-enter");
            input = RegisterUser.takingStringInput();
        }
        return input;
    }
}
