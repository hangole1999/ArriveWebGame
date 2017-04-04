package com.hangole.game.common;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by dsm_025 on 2017-04-03.
 */
public class Room {
    private static ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Player> playerList;
    private final int MAX_PLAYER = 4;
    private int playerNumber;
    private int roomNum;
    private String name;
    private String password;

    public Room(String name, String password, Player roomMaster) {
        this.name = name;
        this.password = password;
    }

    public static ArrayList<Room> getRoomList(){
        return roomList;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public int getPlayerNumber() {

        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getRoomListAsJSON(){
        JSONObject message = new JSONObject();
        message.put("type", "entire_roomList");
        JSONArray jsonArray = new JSONArray();
        for(Room room : roomList){
            JSONObject object = new JSONObject();
            object.put("roomNum", room.getRoomNum());
            object.put("name", room.getName());
            object.put("password", room.getPassword());
            object.put("playerNum", 2);
            jsonArray.put(object);
        }
        message.put("roomList", jsonArray);
        return message.toString();
    }
}
