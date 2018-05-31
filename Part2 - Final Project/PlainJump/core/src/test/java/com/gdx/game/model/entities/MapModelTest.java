package com.gdx.game.model.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapModelTest {

    MapModel mapModel = MapModel.getInstance();

    @Test
    public void updateScore() {
        mapModel.updateScore(1);
        assertEquals(mapModel.getScoreCount(),1);
    }


    @Test
    public void isImmune() {
        assertEquals(mapModel.isImmune(),false);
    }

    @Test
    public void setImmune() {
        mapModel.setImmune(true);
        assertEquals(mapModel.isImmune(),true);
    }

}