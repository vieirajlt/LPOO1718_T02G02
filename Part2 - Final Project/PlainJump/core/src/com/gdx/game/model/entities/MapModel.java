package com.gdx.game.model.entities;

/**
 * This class contains all the information regarding the game's high score count.
 */
public class MapModel {

    /**
     * The singleton instance of this MapModel
     */
    private static MapModel instance = null;

    /**
     * This MapModel current scoreCount
     */
    private int scoreCount = 0;

    /**
     * This MapModel current scoreMultiplier
     */
    private int scoreMultiplier = 1;

    private int scoreVelocityLimit = 1000;

    private float counter = 0;

    private float counterLimit = 40;

    /**
     * immune is a flag representing this MapModel current immunity
     */
    private boolean immune = false;

    private MapModel() {

    }

    /**
     * Retrieves a singleton instance of a MapModel.
     * @return the singleton instance
     */
    public static MapModel getInstance() {
        if(instance == null)
            instance = new MapModel();
        return instance;
    }

    /******************************************/


    //update ScoreCount tendo em considerecao o multiplier (parte dos bonus)
    public boolean updateScore(int delta) {
        scoreCount += scoreMultiplier*delta;
        if (scoreCount % scoreVelocityLimit == 0)
            return true;
        return false;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreMultiplier(int scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
        this.counter = 0;
    }

    public boolean incCounter(float inc)
    {
        this.counter += inc;
        if (this.counter >= this.counterLimit && (scoreMultiplier != 1 || immune == true))
        {
            scoreMultiplier = 1;
            immune = false;
            return true;
        }
        return false;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    public void reset() {
        instance = null;
    }

}
