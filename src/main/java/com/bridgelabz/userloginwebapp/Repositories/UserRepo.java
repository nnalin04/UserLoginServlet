package com.bridgelabz.userloginwebapp.Repositories;

import com.bridgelabz.userloginwebapp.configure.Database;
import com.bridgelabz.userloginwebapp.model.User;

import java.sql.*;

public class UserRepo {

    private final String tableName;
    Connection conn;
    PreparedStatement myStmt = null;

    public UserRepo(String url, String userId, String password, String tableName) {
        createConnection(url, userId, password);
        this.tableName = tableName;
    }

    private void createConnection(String url, String userId, String password){
        try {
            this.conn = DriverManager.getConnection(url, userId, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String addTableName(String statement) {
        return statement.replaceAll("tableName", this.tableName);
    }

    public User saveUserInDatabase(User user) throws SQLException {
        String sql = "insert into tableName "+" (firstName, lastName, email, password, phoneNo)"
                +" values (?, ?, ?, ?, ?)";
        sql = addTableName(sql);
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
        String sql = "select * from tableName where email='"+email+"'";
        sql = addTableName(sql);
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
        String sql1 = this.createQuery(type);
        String sql = addTableName(sql1);
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, input);
        myStmt.setString(2, email);
        myStmt.executeUpdate();
        return findByEmailId(email);
    }

    private String createQuery(String type) {
        return "update tableName set "+type+"=? where email=?";
    }

    public void deleteByEmailId(String email) throws SQLException {
        String sql = "delete from tableName where email=?";
        sql = addTableName(sql);
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, email);
        myStmt.executeUpdate();
    }
}
