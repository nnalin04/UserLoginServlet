package com.bridgelabz.userloginwebapp.configure;

public enum Database {
    USER_REGISTRATION("jdbc:mysql://localhost:3306/userregistration", "root", "Iamcool@1996", "userdetail");

    public final String url;
    public final String userId;
    public final String password;
    public final String tableName;

    Database(String url, String user, String password, String tableName) {
        this.url = url;
        this.userId = user;
        this.password = password;
        this.tableName = tableName;
    }
}
