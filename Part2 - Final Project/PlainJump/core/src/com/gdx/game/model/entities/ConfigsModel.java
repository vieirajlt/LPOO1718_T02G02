package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

/**
 * This class contains all the information regarding the configuration menu colors possibilities.
 */
public class ConfigsModel {

    /**
     * The singleton instance of this ConfigsModel
     */
    private static ConfigsModel instance = null;

    /**
     * Contains this ConfigsModel ball colors
     */
    private ArrayList<Color> ballColors;

    /**
     * Contains this ConfigsModel plain colors
     */
    private ArrayList<Color> plainColors;

    /**
     * Contains this ConfigsModel background colors
     */
    private ArrayList<Color> backgroundColors;


    /*******************CONSTRUCTORS*******************/


    public ConfigsModel() {
        ballColors = new ArrayList<Color>();

        ballColors.add(Color.ORANGE);
        ballColors.add(Color.CYAN);
        ballColors.add(Color.PURPLE);
        ballColors.add(Color.FOREST);

        plainColors = new ArrayList<Color>();

        plainColors.add(Color.VIOLET);
        plainColors.add(Color.NAVY);
        plainColors.add(Color.ROYAL);
        plainColors.add(Color.LIME);

        backgroundColors = new ArrayList<Color>();

        backgroundColors.add(Color.BLACK);
        backgroundColors.add(Color.GRAY);
        backgroundColors.add(Color.SKY);
        backgroundColors.add(Color.FIREBRICK);
    }


    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieves the value of this ConfigsModel ballColors.
     * @return this ConfigsModel ballColors
     */
    public ArrayList<Color> getBallColors() {
        return ballColors;
    }

    /**
     * Retrieves the value of this ConfigsModel plainColors.
     * @return this ConfigsModel plainColors
     */
    public ArrayList<Color> getPlainColors() {
        return plainColors;
    }

    /**
     * Retrieves the value of this ConfigsModel backgroundColors.
     * @return this ConfigsModel backgroundColors
     */
    public ArrayList<Color> getBackgroundColors() {
        return backgroundColors;
    }


    /**
     * Retrieves a singleton instance of a ConfigsModel.
     * @return the singleton instance
     */
    public static ConfigsModel getInstance() {
        if(instance == null) {
            instance = new ConfigsModel();
        }
        return instance;
    }

}
