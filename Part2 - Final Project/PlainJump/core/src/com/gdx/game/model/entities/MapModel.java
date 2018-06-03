package com.gdx.game.model.entities;

import com.badlogic.gdx.math.Vector3;

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

    /**
     * This MapModel score velocity limit
     * used to know when to increase the game's velocity
     */
    private int scoreVelocityLimit = 1000;

    /**
     * This MapModel counter
     * used to keep track of a bonus duration
     */
    private float counter = 0;

    /**
     * This MapModel counter Limit
     * limits a bonus duration
     */
    private float counterLimit = 40;

    /**
     * immune is a flag representing this MapModel current immunity
     */
    private boolean immune = false;

    /**
     * This map gravity (physics component)
     */
    private Vector3 gravity;

    /**
     * This map possible plain position along X axis
     */
    private float[] positionsX;

    /**
     * This map possible plain position along Y axis
     */
    private float[] positionsY;



    /*******************CONSTRUCTORS*******************/

    private MapModel() {
        positionsX = new float[]{-24, -20, -16, -12, -8, -4, 0, 4, 8, 12, 16, 20, 24};

        positionsY = new float[]{0, 4};

        gravity = new Vector3(0, -75f, 0);
    }


    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieve the value of this MapModel immune.
     * @return this MapModel diameter
     */
    public boolean isImmune() {
        return immune;
    }

    /**
     * Retrieve the value of this MapModel scoreCount.
     * @return this MapModel scoreCount
     */
    public int getScoreCount() {
        return scoreCount;
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

    /*******************SET FUNCTIONS*******************/

    /**
     * Set this MapModel jump value.
     * @param immune the new value of immune
     */
    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    /**
     * Set this MapModel jump value.
     * @param scoreMultiplier the new value of scoreMultiplier
     */
    public void setScoreMultiplier(int scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
        this.counter = 0;
    }


    /*********************OTHERS***********************/

    /**
     * Increase this MapModel counter by the given inc.
     * @param inc the value to increment the counter
     * @return true if "immunity" or "score multiplication" are not default
     * and this counter exceeded set limit, else false
     */
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

    /**
     * Increase this MapModel scoreCount by the given delta scoreMultiplier times.
     * @param delta the value increment the scoreCount
     * @return true if scoreCount is less than scoreVelocityLimit, false otherwise
     */
    public boolean updateScore(int delta) {
        scoreCount += scoreMultiplier*delta;
        if (scoreCount % scoreVelocityLimit == 0)
            return true;
        return false;
    }

    /**
     * Reset this MapModel instance (set to null)
     */
    public void reset() {
        instance = null;
    }

    /**
     * Gets minimum plain positioning on Y axis
     * @return the minimum value ([0]) on this map positionsY
     */
    public float getMinY() {
        return positionsY[0];
    }

    /**
     * Gets this map array of possible plain positioning on X axis
     * @return this model positionsX
     */
    public float[] getPositionsX() {
        return positionsX;
    }

    /**
     * Gets this map array of possible plain positioning on X axis
     * @return this model positionsY
     */
    public float[] getPositionsY() {
        return positionsY;
    }

    /**
     * Gets this model gravity vector
     * @return this model gravity
     */
    public Vector3 getGravity() {
        return gravity;
    }


}
