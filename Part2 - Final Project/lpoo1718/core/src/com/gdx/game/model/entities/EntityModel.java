package com.gdx.game.model.entities;

public abstract class EntityModel {

    private float x;

    private float y;

    private float z;

    private boolean toRemove = false;

    public enum MType {BALL, PLAIN, COIN, BOOST};

    public EntityModel(float x, float y, float z)
    {
       this.x = x;
       this.y = y;
       this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public boolean isToRemove() {
        return toRemove;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setToRemove(boolean toRemove) {
        this.toRemove = toRemove;
    }

    public void setPosition(float x, float y, float z)
    {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public abstract MType getType();


}
