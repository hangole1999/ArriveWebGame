package com.hangole.game.controller;

import com.hangole.game.common.Player;
import com.hangole.game.common.Room;
import org.json.JSONObject;

import javax.websocket.Session;

import static com.hangole.game.common.Player.getPlayerEqualSession;

/**
 * Created by dsm_025 on 2017-04-04.
 */
public class MainPageController {

    public static Room createRoom(String name, Boolean lock, String password, Session session){
        Room room = new Room(name, password, getPlayerEqualSession(session), lock, Room.getRoomList().size() + 1);
        Room.addRoomToList(room);
        return room;
    }

    public static Room enterRoom(int roomNum, Session session){
        Room targetRoom = findRoomFromNum(roomNum);

        if(targetRoom != null){
            targetRoom.addPlayer(getPlayerEqualSession(session));
            return targetRoom;
        }

        return null;
    }


    public static Room findRoomFromNum(int rooNum){
        for(Room room : Room.getRoomList()){
            if(room.getRoomNum() == rooNum){
                return room;
            }
        }
        return null;
    }

    /*
    public static Room changeMaster(int roomNum, Session session){
        Room targetRoom = findRoomFromNum(roomNum);

        if(targetRoom != null){
            targetRoom.changeRoomMaster(Player.getPlayerEqualSession(session));
            return targetRoom;
        }
        return null;
    }
    */
}
