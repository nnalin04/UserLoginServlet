package com.bridgelabz.userloginwebapp.Repositories;

import com.bridgelabz.userloginwebapp.model.User;
import java.sql.*;

public class UserRepo {

    private String tableName;
    Connection conn;
    PreparedStatement myStmt = null;

    public UserRepo(){ }

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

    public void saveUserInDatabase(User user) throws SQLException {
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
    }

    public boolean loginUser(User loginBean){
        boolean status = false;

        String sql = "select * from tableName where username = ? and password =?";
        sql = this.addTableName(sql);
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, loginBean.email);
            ps.setString(2, loginBean.password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public User findByEmailId(String email) throws SQLException {
        String sql = "select * from tableName where email='"+email+"'";
        return getUser(sql);
    }

    private User getUser(String sql) throws SQLException {
        sql = addTableName(sql);
        myStmt = conn.prepareStatement(sql);
        ResultSet rs = myStmt.executeQuery();
        User user = new User();
        if (rs.next()){
            user.userId = rs.getInt("id");
            user.firstName = rs.getString("firstName");
            user.lastName = rs.getString("lastName");
            user.email = rs.getString("email");
            user.password = rs.getString("password");
            user.phoneNo = rs.getString("phoneNo");
        }
        return user;
    }

    public void updateUser(User user, String input, String type) throws SQLException {
        String sql1 = this.createQuery(type);
        String sql = addTableName(sql1);
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, input);
        myStmt.setString(2, ""+user.userId+"");
        myStmt.executeUpdate();
        findByEmailId(user.email);
    }

    private String createQuery(String type) {
        return "update tableName set "+type+"=? where id=?";
    }

    public void deleteByEmailId(String email) throws SQLException {
        String sql = "delete from tableName where email=?";
        sql = addTableName(sql);
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, email);
        myStmt.executeUpdate();
    }
}
