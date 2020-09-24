package com.bridgelabz.userloginwebapp.Repositories;

import com.bridgelabz.userloginwebapp.model.User;

import java.sql.*;

public class UserRepo {
    Connection conn;
    PreparedStatement myStmt = null;

    public UserRepo() {
        createConnection();
    }

    private void createConnection(){
        try {
            this.conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public User saveUserInDatabase(User user) throws SQLException {
        String sql = "insert into userdetail "+" (firstName, lastName, email, password, phoneNo)"
                +" values (?, ?, ?, ?, ?)";
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, user.firstName);
        myStmt.setString(2, user.lastName);
        myStmt.setString(3, user.email);
        myStmt.setString(4, user.password);
        myStmt.setString(5, user.phoneNo);
        myStmt.executeUpdate();
        return user;
    }

    public User findByEmailId(String email) throws SQLException {
        String sql = "select * from userdetail where email='"+email+"'";
        myStmt = conn.prepareStatement(sql);
        ResultSet rs = myStmt.executeQuery();
        User user = new User();
        if (rs.next()){
            user.firstName = rs.getString("firstName");
            user.lastName = rs.getString("lastName");
            user.email = rs.getString("email");
            user.password = rs.getString("password");
            user.phoneNo = rs.getString("phoneNo");
        }
        return user;
    }

    public User updateUser(String email, String input, String type) throws SQLException {
        String sql = this.createQuery(type);
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, input);
        myStmt.setString(2, email);
        myStmt.executeUpdate();
        return findByEmailId(email);
    }

    private String createQuery(String type) {
        return "update userdetail set "+type+"=? where email=?";
    }

    public void deleteByEmailId(String email) throws SQLException {
        String sql = "delete from userdetail where email=?";
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, email);
        myStmt.executeUpdate();
    }
}
