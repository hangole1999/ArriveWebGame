package com.hangole.game.controller;

import com.hangole.game.common.Maps;
import com.hangole.game.common.Player;
import com.hangole.game.common.Room;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.websocket.Session;

import java.util.ArrayList;
import java.util.Map;

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

    public static String getGameStartInform(Room room){
        JSONObject object = new JSONObject();
        object.put("type", "game_inform");
        object.put("map_json", room.getMap().getPath());
        JSONArray array = new JSONArray();
        for(String s : room.getMap().getResourcePaths()){
            JSONObject path = new JSONObject();
            path.put("path" , s);
            array.put(path);
        }
        object.put("map_resources", array);
        return object.toString();
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
