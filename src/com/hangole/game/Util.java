package com.hangole.game;

import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by dsm_025 on 2017-04-03.
 */
public class Util {

    public static String makeSuccessLog(String type, String message){
        JSONObject object = new JSONObject();
        object.put("type", type);
        object.put("state", "success");
        object.put("message", message);
        return object.toString();
    }

    public static String makeErrorLog(String type, String message){
        JSONObject object = new JSONObject();
        object.put("type", type);
        object.put("state", "fail");
        object.put("message", message);
        return object.toString();
    }

    public static String getLocalIP(){
        try {
            return InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
