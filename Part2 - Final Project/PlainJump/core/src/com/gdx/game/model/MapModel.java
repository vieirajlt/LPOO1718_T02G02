package com.gdx.game.model;

public class MapModel {

    private static MapModel instance = null;

    public MapModel() {

    }

    public static MapModel getInstance() {
        if(instance == null)
            instance = new MapModel();
        return instance;
    }



}
