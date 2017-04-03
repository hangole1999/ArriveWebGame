package com.hangole.server.session;

/**
 * Created by kimgiwhang_010 on 2017-03-29.
 */
public class User {

    private String id;
    private String password;
    private String ip;

    public User(String id, String password, String ip) {
        this.id = id;
        this.password = password;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getIp() {
        return ip;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
