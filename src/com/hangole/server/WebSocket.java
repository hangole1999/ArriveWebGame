package com.hangole.server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Hangole on 2017-03-20.
 */

@ServerEndpoint("/game")
public class WebSocket {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOnpen()");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage("+message+")");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose()");
    }

    @OnError
    public void onError(Throwable throwable, Session session) {
        System.out.println("onError()");
        throwable.printStackTrace();
    }
}
