package com.hangole.server.session;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by dsm_025 on 2017-04-03.
 *
 */
public class Util {
    private static ArrayList<HttpSession> sessionList = new ArrayList<>();

    public static ArrayList<HttpSession> getSessionList() {
        return sessionList;
    }

    public static void addSession(HttpSession session) {
        sessionList.add(session);
    }

    public static String findEqualSessionId(HttpSession session) {

        for(HttpSession httpSession : sessionList ){
            if(httpSession.equals(session)){
                return ((User)httpSession.getAttribute("user")).getId();
            }
        }
        return null;
    }
}
