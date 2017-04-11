package com.hangole.game.common;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

/**
 * Created by dsm_025 on 2017-04-11.
 */
public enum Maps {
    FIRST_MAP("/maps/first.json", "firstMap", new File("./maps/firstMap").listFiles()),
    SECOND_MAP("/maps/first.json", "secondMap", new File("./maps/secondMap").listFiles()),
    THIRD_MAP("/maps/first.json", "thirdMap", new File("./maps/thirdMap").listFiles());
    private String path;
    private String name;
    ArrayList<String> resourceList;

    Maps(String path, String name, File[] files) {
        this.path = path;
        this.name = name;
        for(File file : files){
            resourceList.add(file.getPath());
        }
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }


    public ArrayList<String> getResourcePaths(){
        return resourceList;
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
