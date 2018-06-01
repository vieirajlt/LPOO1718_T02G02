package com.gdx.game.model.entities;

/**
 * This class contains all the information transmitted in the main menu.
 */
public class MenuModel {

    /**
     * The singleton instance of this MenuModel
     */
    private static MenuModel instance = null;

    /**
     * This MenuModel current high score
     */
    private Integer Highscore;

    /*******************CONSTRUCTORS*******************/

    public MenuModel() {
        Highscore = 0;
    }

    /*******************SET FUNCTIONS*******************/
    /**
     * Set this MenuModel Highscore value.
     * @param highscore the new value of Highscore
     */
    public void setHighscore(Integer highscore) {
        Highscore = highscore;
    }

    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieve the value of this MenuModel Highscore.
     * @return this MenuModel Highscore
     */
    public Integer getHighscore() {
        return Highscore;
    }

    /**
     * Retrieves a singleton instance of a MenuModel.
     * @return the singleton instance
     */
    public static MenuModel getInstance() {
        if(instance == null)
            instance = new MenuModel();
        return instance;
    }
}
