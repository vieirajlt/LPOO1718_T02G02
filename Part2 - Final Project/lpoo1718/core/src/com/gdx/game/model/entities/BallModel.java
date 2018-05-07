package com.gdx.game.model.entities;

public class BallModel extends EntityModel{

    private PlainModel currentPlain;

    private int scoreCount = 0;

    private boolean jumping = false;

    private boolean colliding = false;

    private boolean falling = false;

    private boolean fatality = false;


    BallModel(float x, float y, float z, PlainModel currentPlain)
    {
       super(x,y,z);
       this.currentPlain = currentPlain;
    }

    public PlainModel getCurrentPlain() {
        return currentPlain;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public boolean isJumping() {
        return jumping;
    }
    public boolean isColliding() {
        return colliding;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isFatality() {
        return fatality;
    }

    public void setCurrentPlain(PlainModel currentPlain) {
        this.currentPlain = currentPlain;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setFatality(boolean fatality) {
        this.fatality = fatality;
    }

    public void increaseScoreCount(int value)
    {
        this.scoreCount += value;
    }

    public void moveLeft()
    {
        float newX = this.getX() + 1;
        this.setX(newX); //nao sei se e 1 ou mais
    }

    public void moveRight()
    {
        float newX = this.getX() - 1;
        this.setX(newX);
    }

    public void moveFront()
    {
        float newZ = this.getZ() + 1;
        this.setZ(newZ);
    }

    public void jump()
    {
        float newY = this.getY() + 1; // nao se quanto é que ele salta
        this.setZ(newY);
    }

    public MType getType()
    {
        return MType.BALL;
    }
}

