package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 * This class represents the user ball.
 */
public class BallModel extends EntityModel{

    /**
     * This BallModel posX default value
     */
    private static final float DEFAULT_POS_X = 0;
    /**
     * This BallModel posY default value
     */
    private static final float DEFAULT_POS_Y = 2;
    /**
     * This BallModel posZ default value
     */
    private static final float DEFAULT_POS_Z = 3;
    /**
     * This BallModel diameter default value
     */
    private static final float DEFAULT_DIAMETER = 2;

    /**
     * This BallModel diameter
     */
    private float diameter;

    /**
     * jump is a flag indicating if this BallModel can jump
     * if so the flag is set to true, otherwise is set to false
     */
    private boolean jump = true;

    /**
     * falling is a flag indicating if this BallModel is currently falling
     * if so the flag is set to true, otherwise is set to false
     */
    private boolean falling = false;


    /*******************CONSTRUCTORS*******************/

    /**
     * Creates a new BallModel with the stipulated default values.
     */
    public BallModel() {
       // this(0f, 2f, 3f, 2f);
        this(DEFAULT_POS_X,DEFAULT_POS_Y,DEFAULT_POS_Z,DEFAULT_DIAMETER);
    }

    /**
     * Creates a new BallModel with the preferred x, y, z and d values.
     * @param x the new value of posX
     * @param y the new value of posY
     * @param z the new value of posZ
     * @param d the new value of diameter
     */
    public BallModel(float x, float y, float z, float d) {
        super(x, y, z);

        this.diameter = d;

        setInitialColor(Color.ORANGE);
    }

    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieve the value of this BallModel diameter.
     * @return this BallModel diameter
     */
    public float getDiameter() {
        return diameter;
    }

    /**
     * Retrive the value of this BallModel jump.
     * @return this BallModel jump
     */
    public boolean canJump() {
        return jump;
    }

    /**
     * Retrive the value of this BallModel falling.
     * @return this BallModel falling
     */
    public boolean isFalling() {
        return falling;
    }

    /*******************SET FUNCTIONS*******************/

    /**
     * Set this BallModel jump value.
     * @param jump the new value of jump
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }


    /**
     * Set this BallModel falling value.
     * @param falling the new value of falling
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

}
