package com.gdx.game.model.entities;

public class MenuModel {

    private static MenuModel instance = null;

    private Integer Highscore;

    public MenuModel() {
        Highscore = 0;
    }

    public static MenuModel getInstance() {
        if(instance == null)
            instance = new MenuModel();
        return instance;
    }

    public Integer getHighscore() {
        return Highscore;
    }

    public void setHighscore(Integer highscore) {
        Highscore = highscore;
    }
}
