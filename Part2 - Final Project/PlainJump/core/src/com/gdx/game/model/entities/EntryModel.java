package com.gdx.game.model.entities;

/**
 * This class contains all the information transmitted in the main menu.
 */
public class EntryModel {

    /**
     * The singleton instance of this EntryModel
     */
    private static EntryModel instance = null;

    /**
     * This EntryModel current high score
     */
    private Integer Highscore;

    /*******************CONSTRUCTORS*******************/

    private EntryModel() {
        Highscore = 0;
    }

    /*******************SET FUNCTIONS*******************/
    /**
     * Set this EntryModel Highscore value.
     * @param highscore the new value of Highscore
     */
    public void setHighscore(Integer highscore) {
        Highscore = highscore;
    }

    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieve the value of this EntryModel Highscore.
     * @return this EntryModel Highscore
     */
    public Integer getHighscore() {
        return Highscore;
    }

    /**
     * Retrieves a singleton instance of a EntryModel.
     * @return the singleton instance
     */
    public static EntryModel getInstance() {
        if(instance == null)
            instance = new EntryModel();
        return instance;
    }
}
