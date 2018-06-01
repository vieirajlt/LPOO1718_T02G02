package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;

//isto se calhar devia ser abstrato nao?

/**
 *
 */
public class EntityModel {


    /**
     * The x position of this EntityModel
     */
    private float posX;
    /**
     * The y position of this EntityModel
     */
    private float posY;
    /**
     * The z position of this EntityModel
     */
    private float posZ;

    /**
     * The initial color of this EntityModel
     */
    private Color initialColor = Color.BLACK;

    /**
     * The current color of this EntityModel
     */
    private Color currentColor = initialColor;

    /*******************CONSTRUCTORS*******************/

    /**
     * Creates an EntityModel in preferred x, y and z values.
     * @param x the new value of posX
     * @param y the new value of posY
     * @param z the new value of posZ
     */
    public EntityModel(float x, float y, float z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieve the value of this EntityModel posX.
     * @return this EntityModel posX
     */
    public float getPosX() {
        return posX;
    }

    /**
     * Retrieve the value of this EntityModel posY.
     * @return this EntityModel posY
     */
    public float getPosY() {
        return posY;
    }

    /**
     * Retrieve the value of this EntityModel posZ.
     * @return this EntityModel posZ
     */
    public float getPosZ() {
        return posZ;
    }

    /**
     * Retrieve the value of this EntityModel initialColor.
     * @return this EntityModel initialColor
     */
    public Color getInitialColor() {
        return initialColor;
    }

    /*******************SET FUNCTIONS*******************/

    /**
     * Set this EntityModel posX value.
     * @param posX the new value of posX
     */
    public void setPosX(float posX) {
        this.posX = posX;
    }

    /**
     * Set this EntityModel posY value.
     * @param posY the new value of posY
     */
    public void setPosY(float posY) {
        this.posY = posY;
    }

    /**
     * Set this EntityModel posZ value.
     * @param posZ the new value of posZ
     */
    public void setPosZ(float posZ) {
        this.posZ = posZ;
    }

    /**
     * Set this EntityModel initialColor value.
     * @param initialColor the new value of initialColor
     */
    public void setInitialColor(Color initialColor) {
        this.initialColor = initialColor;
        setCurrentColor(initialColor);
    }

    /**
     *  Set this EntityModel currentColor value.
     * @param currentColor the new value of currentColor
     */
    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}
