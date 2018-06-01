package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;

/**
 * This class represents the model for the plain
 */
public class PlainModel extends EntityModel {

    /**
     * This PlainModel posX default value
     */
    private static final float DEFAULT_POS_X = 0;
    /**
     * This PlainModel posY default value
     */
    private static final float DEFAULT_POS_Y = 0;
    /**
     * This PlainModel posZ default value
     */
    private static final float DEFAULT_POS_Z = 0;
    /**
     * This PlainModel width default value
     */
    private static final float DEFAULT_WIDTH = 4;
    /**
     * This PlainModel heigth default value
     */
    private static final float DEFAULT_HEIGTH = 1;
    /**
     * This PlainModel depth default value
     */
    private static final float DEFAULT_DEPTH = 12;

    /**
     * This PlainModel width
     */
    private float width;
    /**
     * This PlainModel heigth
     */
    private float heigth;
    /**
     * This PlainModel depth
     */
    private float depth;

    /*******************CONSTRUCTORS*******************/

    /**
     * Creates a new BallModel with the stipulated default values.
     */
    public PlainModel() {
       // this(0, 0, 0, 4, 1, 12);
        this(DEFAULT_POS_X,DEFAULT_POS_Y,DEFAULT_POS_Z,DEFAULT_WIDTH,DEFAULT_HEIGTH,DEFAULT_DEPTH);
    }

    /**
     *  Creates a new BallModel with the preferred x, y, z, w, h and d values.
     * @param x the new value of posX
     * @param y the new value of posY
     * @param z the new value of posZ
     * @param w the new value of width
     * @param h the new value of heigth
     * @param d the new value of depth
     */
    public PlainModel(float x, float y, float z, float w, float h, float d) {

        super(x, y, z);

        this.width = w;
        this.heigth = h;
        this.depth = d;

        setInitialColor(Color.VIOLET);
    }

    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieve the value of this PlainModel width
     * @return this PlainModel width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Retrieve the value of this PlainModel heigth
     * @return this PlainModel heigth
     */
    public float getHeight() {
        return heigth;
    }

    /**
     * Retrieve the value of this PlainModel depth
     * @return this PlainModel depth
     */
    public float getDepth() {
        return depth;
    }
}
