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
        userRepo.saveUserInDatabase(user);
    }

    public User getUsetDetail(String email) throws UserLoginException, SQLException {
        return userRepo.findByEmailId(email);
    }

    public void updateUser(User user) throws SQLException {
        userRepo.updateUser(user.userId, user.firstName, "firstName");
        userRepo.updateUser(user.userId, user.lastName, "lastName");
        userRepo.updateUser(user.userId, user.email, "email");
        userRepo.updateUser(user.userId, user.password, "password");
        userRepo.updateUser(user.userId, user.phoneNo, "phoneNo");
    }

    public void deleteUser(String email) throws SQLException {
        userRepo.deleteByEmailId(email);
    }

    public String checkInput(User user) throws SQLException {
        String result;
        result = checkFieldValidation(UserRegistration.ValidatorPat.NAME,
                user.firstName, "firstName");
        if (!result.equals("success")) return result;
        result = checkFieldValidation(UserRegistration.ValidatorPat.NAME,
                user.lastName, "lastName");
        if (!result.equals("success")) return result;
        result = checkFieldValidation(UserRegistration.ValidatorPat.EMAIL,
                user.email, "email");
        if (!result.equals("success")) return result;
        result = checkFieldValidation(UserRegistration.ValidatorPat.PASSWORD,
                user.password, "password");
        if (!result.equals("success")) return result;
        result = checkFieldValidation(UserRegistration.ValidatorPat.MOBILE,
                user.phoneNo, "phone no.");
        if (!result.equals("success")) return result;
        return result;
    }

    private String checkFieldValidation(UserRegistration.ValidatorPat pattern, String input, String type)
            throws SQLException {

        if (userRepo.findByEmailId(input).email != null && type.equals("email"))
            return "email already present";

        if (!UserRegistration.validateInput(input, pattern))
            return "Invalid "+type+" please re-enter";

        return "success";
    }
}
