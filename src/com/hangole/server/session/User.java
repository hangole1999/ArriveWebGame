package com.hangole.server.session;

/**
 * Created by kimgiwhang_010 on 2017-03-29.
 */
public class User {

    private String identity;
    private String password;

    public User(String identity, String password) {
        this.identity = identity;
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }
    public String getPassword() {
        return password;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
