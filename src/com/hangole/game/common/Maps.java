package com.hangole.game.common;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by dsm_025 on 2017-04-11.
 */
public enum Maps {
    FIRST_MAP("/maps/first.json", "firstMap"),
    SECOND_MAP("/maps/first.json", "secondMap"),
    THIRD_MAP("/maps/first.json", "thirdMap");
    private String path;
    private String name;

    Maps(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }



    public static ArrayList<String> getMapNames(){
        ArrayList<String> list = new ArrayList<>();
        for(Maps map : values()){
            list.add(map.getName());
        }
        return list;
    }

    public static Maps getMapFromName(String name){
        for(Maps map : values()){
            if(map.getName().equals(name)){
                return map;
            }
        }
        return null;
    }


    public static Maps[] getMaps(){
        return values();
    }
}
