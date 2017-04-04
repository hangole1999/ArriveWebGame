package com.hangole.game.common;


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

    public Player(String id, boolean roomMaster, Session session) {
        this.id = id;
        this.roomMaster = roomMaster;
        this.session = session;
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

    public static void addPlayerToList(Player player){
        playerList.add(player);
    }

    public static ArrayList<Player> getPlayerList(){
        return playerList;
    }

    public static Player getEqualPlayer(Player player){
        for(Player temp : Player.getPlayerList()){
            if(temp.getSession().equals(player.getSession())){
                return temp;
            }
        }
        return null;
    }

    public static Player getPlayerEqualSession(Session session){
        for(Player player : Player.getPlayerList()){
            if(player.getSession().equals(session)){
                return player;
            }
        }
        return null;
    }
}
