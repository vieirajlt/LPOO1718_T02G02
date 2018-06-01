package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;

//isto se calhar devia ser abstrato nao?
public class EntityModel {

    private float posX;
    private float posY;
    private float posZ;

    private Color initialColor = Color.BLACK;

    private Color currentColor = initialColor;

    public EntityModel(float x, float y, float z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getPosZ() {
        return posZ;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setPosZ(float posZ) {
        this.posZ = posZ;
    }

    public Color getInitialColor() {
        return initialColor;
    }

    public void setInitialColor(Color initialColor) {
        this.initialColor = initialColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}
