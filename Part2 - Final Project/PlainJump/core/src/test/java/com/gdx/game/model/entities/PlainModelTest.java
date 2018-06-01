package com.gdx.game.model.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlainModelTest {

    PlainModel plainModel = new PlainModel();
    @Test
    public void getWidth() {
        assertEquals(plainModel.getWidth(),4,0.01f);
    }

    @Test
    public void getHeight() {
        assertEquals(plainModel.getHeight(),1,0.01f);
    }

    @Test
    public void getDepth() {
        assertEquals(plainModel.getDepth(),12,0.01f);
    }
}