package com.hangole.server.session;

/**
 * Created by kimgiwhang_010 on 2017-03-29.
 */
public class User {

    private String id;
    private String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
