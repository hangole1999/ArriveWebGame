package com.hangole.game.common;


import org.json.JSONArray;
import org.json.JSONObject;

import javax.websocket.Session;
import java.util.ArrayList;

/**
 * Created by dsm_025 on 2017-04-03.
 */

public class Player {
    private static ArrayList<Player> playerList = new ArrayList<>();
    private String id;
    private boolean roomMaster;
    private Session session;
    private double characPositionX = 0;
    private double characPositionY = 0;
    private int hp;
    private int killCount;


    public Player(String id, boolean roomMaster, Session session) {
        this.id = id;
        this.roomMaster = roomMaster;
        this.session = session;
    }

    public double getPositionX() {
        return characPositionX;
    }

    public double getPositionY() {
        return characPositionY;
    }

    public void setPositionX(double x) {
        this.characPositionX = x;
    }

    public void setPosition(double y) {
        this.characPositionY = y;
    }

    public static String getPositionAsJSON(Session session){
        JSONObject message = new JSONObject();
        message.put("type","Position");
        message.put("x",room.getPlayerEqualSession(session).getPositionX());
        message.put("y",room.getPlayerEqualSession(session).getPositionY());
        return message.toString();
    }

    public String getId() {
        return id;
    }

    public boolean isRoomMaster() {
        return roomMaster;
    }

    public void setRoomMaster(boolean roomMaster) {
        this.roomMaster = roomMaster;
    }

    public Session getSession() {
        return session;
    }

    public static void addPlayerToList(Player player) {
        playerList.add(player);
    }

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public boolean isReadyState() {
        return readyState;
    }

    public void setReadyState(boolean readyState) {
        this.readyState = readyState;
    }

    public void changeReadyState() {
        this.readyState = !readyState;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    public void upCountKill(){
        this.killCount++;
    }

    public boolean minusHp(int amount){
        this.hp -= amount;
        return  this.hp <= 0;
    }

    public static Player getEqualPlayer(Player player) {
        for (Player temp : Player.getPlayerList()) {
            if (temp.getSession().equals(player.getSession())) {
                return temp;
            }
        }
        return null;
    }

    public static Player getPlayerEqualSession(Session session) {
        for (Player player : Player.getPlayerList()) {
            if (player.getSession().equals(session)) {
                return player;
            }
        }
        return null;
    }
}
