package com.hangole.game.common;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.websocket.Session;
import java.util.ArrayList;

/**
 * Created by dsm_025 on 2017-04-03.
 */

public class Room {

    private static ArrayList<Room> roomList = new ArrayList<>();
    private ArrayList<Player> playerList;
    private final int MAX_PLAYER = 4;
    private boolean lock;

    private int playerNumber;
    private int roomNum;
    private String name;
    private String password;
    private Player roomMaster;

    public Room(String name, String password, Player roomMaster, boolean lock, int roomNum) {
        this.name = name;
        this.password = password;
        this.lock = lock;
        this.roomMaster = roomMaster;
        this.roomNum = roomNum;
        playerList = new ArrayList<>();
        addPlayer(roomMaster);
    }

    public Player getRoomMaster(){
        return this.roomMaster;
    }

    public static ArrayList<Room> getRoomList() {
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

    public boolean isLock() {
        return lock;
    }

    public static void addRoomToList(Room room) {
        roomList.add(room);
    }

    public static String getRoomListAsJSON() {
        JSONObject message = new JSONObject();
        message.put("type", "entire_roomList");
        JSONArray jsonArray = new JSONArray();
        for (Room room : roomList) {
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

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public void removePlayer(Player player) {
        playerList.remove(player);
    }

    public ArrayList<Player> getPlayerList(){
        return playerList;
    }

    public ArrayList<Session> getPlayerSession(){
        ArrayList<Session> sessionList = new ArrayList<>();
        for(Player player : getPlayerList()){
            sessionList.add(player.getSession());
        }
        return sessionList;
    }

    public JSONObject getRoomInfomToJSON(){
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("roomNum", roomNum);
        object.put("lock", lock);
        object.put("playerNum", playerList.size());
        object.put("roomMaster", roomMaster.getId());
        JSONArray array = new JSONArray();
        for(Player player : getPlayerList()){
            JSONObject temp = new JSONObject();
            array.put(temp.put("id", player.getId()));
        }
        object.put("playerList", array);
        return object;
    }

    public boolean changeRoomMaster(Player beforePlayer){
        Boolean isSuccess = false;

        for(Player player : playerList){
            if(roomMaster.getId().equals(beforePlayer.getId())){
                roomMaster = beforePlayer;
                isSuccess = true;
            }
        }
        return isSuccess;
    }


}
