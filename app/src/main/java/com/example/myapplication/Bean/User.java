package com.example.myapplication.Bean;

/**
 * 用户
 */
public class User {
    private String id;
    private String account;//账号
    private String name;//名称
    private String password;//密码

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String id, String account, String name, String password) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.password = password;
    }
}
