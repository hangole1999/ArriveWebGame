package com.hangole.server.session;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
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

    public static String findEqualSessionId(Session session) {
        String gameSessionIP = session.getUserProperties().get("javax.websocket.endpoint.remoteAddress").toString();
        session.getId();
        for(HttpSession httpSession : sessionList ){
            User user = (User)httpSession.getAttribute("user");
            if(user.getIp().equals(gameSessionIP)){
                return user.getId();
            }
        }
        return null;
    }
}
