package com.hangole.game;

import com.hangole.game.common.Player;
import com.hangole.game.common.Room;
import com.hangole.game.controller.MainPageController;
import com.hangole.server.session.Util;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hangole on 2017-03-20.
 */

@ServerEndpoint(value = "/game", configurator = GetHttpSessionConfigurator.class)
public class WebSocket {
    private static ArrayList<Session> list = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        System.out.println("onOnpen()");
        list.add(session);
        session.getBasicRemote().sendText(Room.getRoomListAsJSON());
        HttpSession httpSession = (HttpSession) config.getUserProperties()
                .get(HttpSession.class.getName());
        Player player = new Player(Util.findEqualSessionId(httpSession), false, session);
        Player.addPlayerToList(player);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("onMessage(" + message + ")");
        JSONObject jsonObject = new JSONObject(message);
        switch (jsonObject.getString("type")) {
            case "create_room": {
                Room created_room = MainPageController.createRoom(jsonObject.getString("name"), jsonObject.getBoolean("lock"), jsonObject.getString("password"), session);
                session.getBasicRemote().sendText(created_room.getRoomDetailInfomToJSON().put("type", "room_detail").toString());
            }
            break;
            case "enter_room": {
                Room entered_room = MainPageController.enterRoom(jsonObject.getInt("roomNum"), session);
                if (entered_room != null) {
                    ArrayList<Session> roomMembers = entered_room.getPlayerSession();
                    for (Session member : roomMembers) {
                        member.getBasicRemote().sendText(entered_room.getRoomDetailInfomToJSON().put("type", "room_detail").toString());
                    }
                } else {
                    session.getBasicRemote().sendText(com.hangole.game.Util.makeErrorLog("방 인원이 가득 찼습니다."));
                }
            }
            break;
            case "change_master": {
                Room targetRoom = MainPageController.findRoomFromRoomList(jsonObject.getInt("roomNum"));
                if (targetRoom != null) {
                    Boolean isSuccess = targetRoom.changeRoomMaster(targetRoom.getPlayerEqualSession(session));

                    if (isSuccess == true) {
                        session.getBasicRemote().sendText(targetRoom.getRoomDetailInfomToJSON().put("type", "room_detail").toString());
                    } else {
                        session.getBasicRemote().sendText(com.hangole.game.Util.makeErrorLog("방장 변경에 실패했습니다."));
                    }
                }
            }
            break;
            case "change_ready": {
                Room targetRoom = MainPageController.findRoomFromRoomList(jsonObject.getInt("roomNum"));
                targetRoom.getPlayerEqualSession(session).changeReadyState();
            }
            break;
            case "get_out_room": {
                Room targetRoom = MainPageController.findRoomFromRoomList(jsonObject.getInt("roomNum"));
                if (targetRoom.removePlayer(targetRoom.getPlayerEqualSession(session))) {
                    session.getBasicRemote().sendText(com.hangole.game.Util.makeSuccessLog("나가기 성공"));
                } else {
                    session.getBasicRemote().sendText(com.hangole.game.Util.makeErrorLog("나가기 성공"));
                }
            }
            break;
            case "game_start": {
                Room targetRoom = MainPageController.findRoomFromRoomList(jsonObject.getInt("roomNum"));
                if (targetRoom.isGamePossible()) {
                    for (Player player : targetRoom.getPlayerList()) {
                        player.setHp(100);
                        player.getSession().getBasicRemote().sendText(MainPageController.getGameStartInform(targetRoom));
                    }
                    targetRoom.changeRoomToPlaying(targetRoom);
                } else {
                    session.getBasicRemote().sendText(com.hangole.game.Util.makeErrorLog("레디를 안한 팀원이 있습니다."));
                }
            }
            break;
            case "change_map": {
                Room targetRoom = MainPageController.findRoomFromRoomList(jsonObject.getInt("roomNum"));
                if (targetRoom.chanegMap(jsonObject.getString("name"))) {
                    session.getBasicRemote().sendText(com.hangole.game.Util.makeSuccessLog("Map 변경 성공"));
                } else {
                    session.getBasicRemote().sendText(com.hangole.game.Util.makeErrorLog("올바르지 않은 Map 이름입니다."));
                }
            }
            case "move_character": {
                Room target = MainPageController.findRoomFromPlayingRoomList(jsonObject.getInt("roomNum"));

                Player player = target.getPlayerEqualSession(session);

                if (target != null) {
                    if (player != null) {
                        player.setPositionX(jsonObject.getDouble("x"));
                        player.setPositionY(jsonObject.getDouble("y"));

                        ArrayList<Session> roomMembers = target.getPlayerSession();

                        for (Session member : roomMembers) {
                            member.getBasicRemote().sendText(Player.getPositionAsJSON(target, session).put("type", "characterPosition").toString());
                        }
                    } else {
                        session.getBasicRemote().sendText(com.hangole.game.Util.makeErrorLog("player session 이 null"));
                    }
                } else {
                    session.getBasicRemote().sendText(com.hangole.game.Util.makeErrorLog("Room 이 null"));
                }
            }
            break;

            case "lose_hp": {
                Room targetRoom = MainPageController.findRoomFromPlayingRoomList(jsonObject.getInt("roomNum"));
                targetRoom.getPlayerEqualSession(session).minusHp(15);
                for (Player player : targetRoom.getPlayerList()) {
                    player.getSession().getBasicRemote().sendText(MainPageController.getPlayersHPInfo(targetRoom));
                }
            }
            break;
        }
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
