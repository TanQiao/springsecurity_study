package com.test.security;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Administrator on 2015/12/12.
 */
public class UserAction extends ActionSupport{
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public  String login(){
        System.out.println(username);
        System.out.println(password);
        return Action.SUCCESS;
    }
}
