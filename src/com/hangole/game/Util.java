package com.hangole.game;

import org.json.JSONObject;

/**
 * Created by dsm_025 on 2017-04-03.
 */
public class Util {

    public static String makeErrorLog(String message){
        JSONObject object = new JSONObject();
        object.put("type", "error");
        object.put("message", message);
        return object.toString();
    }

    public static String makeSuccessLog(String message){
        JSONObject object = new JSONObject();
        object.put("type", "success");
        object.put("message", message);
        return object.toString();
    }

}
