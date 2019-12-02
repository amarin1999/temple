package com.cdgs.temple.dto;

import java.io.Serializable;

public class AuthDto implements Serializable {


    private static final long serialVersionUID = 2258512224536230594L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
