package com.gdx.game.model.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntryModelTest {

    EntryModel menuModel = EntryModel.getInstance();

    @Test
    public void getHighscore() {
        assertEquals(menuModel.getHighscore(),0,0.01);
    }

    @Test
    public void setHighscore() {
        menuModel.setHighscore(1);
        assertEquals(menuModel.getHighscore(),1,0.01);

    }
}