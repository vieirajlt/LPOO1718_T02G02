package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class ConfigsModel {

    private static ConfigsModel instance = null;

    private ArrayList<Color> ballColors;
    private ArrayList<Color> plainColors;
    private ArrayList<Color> backgroundColors;

    public ConfigsModel() {
        ballColors = new ArrayList<Color>();

        ballColors.add(Color.ORANGE);
        ballColors.add(Color.CYAN);
        ballColors.add(Color.PURPLE);
        ballColors.add(Color.FOREST);

        plainColors = new ArrayList<Color>();

        plainColors.add(Color.BLACK);
        plainColors.add(Color.GRAY);
        plainColors.add(Color.SKY);
        plainColors.add(Color.FIREBRICK);

        backgroundColors = new ArrayList<Color>();

        backgroundColors.add(Color.VIOLET);
        backgroundColors.add(Color.NAVY);
        backgroundColors.add(Color.ROYAL);
        backgroundColors.add(Color.LIME);
    }

    public ArrayList<Color> getBallColors() {
        return ballColors;
    }

    public ArrayList<Color> getPlainColors() {
        return plainColors;
    }

    public ArrayList<Color> getBackgroundColors() {
        return backgroundColors;
    }

    public static ConfigsModel getInstance() {
        if(instance == null) {
            instance = new ConfigsModel();
        }
        return instance;
    }

}
