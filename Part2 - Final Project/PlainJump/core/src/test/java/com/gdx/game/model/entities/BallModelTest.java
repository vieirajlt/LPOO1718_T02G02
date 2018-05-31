package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;

import org.junit.Test;

import static org.junit.Assert.*;

public class BallModelTest {
    BallModel ballModel = new BallModel();

    @Test
    public void getDiameter() {
        assertEquals(ballModel.getDiameter(),2f,0.02f);
    }

    @Test
    public void canJump() {
        assertEquals(ballModel.canJump(),true);
    }

    @Test
    public void setJump() {
        ballModel.setJump(false);
        assertEquals(ballModel.canJump(),false);
    }

    @Test
    public void isFalling() {
        assertEquals(ballModel.isFalling(),false);
    }

    @Test
    public void setFalling() {
        ballModel.setFalling(true);
        assertEquals(ballModel.isFalling(),true);
    }

    @Test
    public void getPosX() {
        assertEquals(ballModel.getPosX(),0f,0.01f);
    }

    @Test
    public void getPosY() {
        assertEquals(ballModel.getPosY(),2f,0.01f);
    }

    @Test
    public void getPosZ() {
        assertEquals(ballModel.getPosZ(),3f,0.01f);
    }

    @Test
    public void setPosX() {
        ballModel.setPosX(2);
        assertEquals(ballModel.getPosX(),2f,0.01f);
    }

    @Test
    public void setPosY() {
        ballModel.setPosY(2);
        assertEquals(ballModel.getPosY(),2f,0.01f);
    }

    @Test
    public void setPosZ() {
        ballModel.setPosZ(2);
        assertEquals(ballModel.getPosZ(),2f,0.01f);
    }

    @Test
    public void getInitialColor() {
        assertEquals(ballModel.getInitialColor(), Color.ORANGE);
    }

    @Test
    public void setInitialColor() {
        ballModel.setInitialColor(Color.BLACK);
        assertEquals(ballModel.getInitialColor(), Color.BLACK);
    }

}