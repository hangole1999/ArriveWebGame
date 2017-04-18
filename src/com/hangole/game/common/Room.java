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
    private static ArrayList<Room> playingRoomList = new ArrayList<>();
    private ArrayList<Player> playerList;
    private final int MAX_PLAYER = 4;
    private boolean lock;

    private int playerNumber;
    private int roomNum;
    private String name;
    private String password;
    private Player roomMaster;
    private Maps map = Maps.FIRST_MAP;

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

    public void setRoomMaster(Player player){
        roomMaster = player;
    }

    public static ArrayList<Room> getRoomList() {
        return roomList;
    }

    public static ArrayList<Room> getPlayingRoomList() {
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

    public Maps getMap(){
        return this.map;
    }

    public String getRoomDetailInfomToJSON(){
        JSONObject object = new JSONObject();
        object.put("type", "room_detail");
        object.put("name", name);
        object.put("roomNum", roomNum);
        object.put("lock", lock);
        object.put("playerNum", playerList.size());
        object.put("roomMaster", roomMaster.getId());
        JSONArray playerArray = new JSONArray();
        for(Player player : getPlayerList()){
            JSONObject temp = new JSONObject();
            temp.put("id", player.getId());
            temp.put("ready", player.isReadyState());
            playerArray.put(temp);
        }
        JSONArray mapsArray = new JSONArray();
        for(String name : Maps.getMapNames()){
            JSONObject temp = new JSONObject();
            temp.put("name", name);
            mapsArray.put(temp);
        }
        object.put("playerList", playerArray);
        object.put("mapList", mapsArray);
        return object.toString();
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

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public boolean removePlayer(Player player) {
        return playerList.remove(player);
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

    public boolean changeMap(String name){
        this.map = Maps.getMapFromName(name);
        if(map != null ) {
            return true;
        }else{
            this.map = Maps.FIRST_MAP;
            return false;
        }
    }

    public Player getPlayerEqualSession(Session session) {
        for (Player player : getPlayerList()) {
            if (player.getSession().equals(session)) {
                return player;
            }
        }
        return null;
    }

    public Player getPlayerEqualId(String id){
        for (Player player : getPlayerList()) {
            if (player.getId().equals(id)) {
                return player;
            }
        }
        return null;
    }

    public boolean isGamePossible() {
        for(Player player : playerList){
            if(player.equals(roomMaster) || player.isReadyState()){
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean changeRoomToPlaying(Room room){
        roomList.remove(room);
        return playingRoomList.add(room);
    }

    public static void removeRoom(Room room){
        roomList.remove(room);
    }
}
