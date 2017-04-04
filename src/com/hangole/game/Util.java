package com.hangole.game;

import com.hangole.game.common.Player;
import org.json.JSONObject;

/**
 * Created by dsm_025 on 2017-04-03.
 */
public class Util {


    public static String makeErrorLog(String message){
        JSONObject object = new JSONObject();
        object.put("type", "error");
        object.put("mseeage", message);
        return object.toString();
    }
}
