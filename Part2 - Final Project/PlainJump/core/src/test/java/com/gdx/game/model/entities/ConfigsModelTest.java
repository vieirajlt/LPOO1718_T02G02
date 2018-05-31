package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConfigsModelTest {

    ConfigsModel configsModel = ConfigsModel.getInstance();

    @Test
    public void getBallColors() {
        ArrayList<Color> ballColors = new ArrayList<Color>();
        ballColors.add(Color.ORANGE);
        ballColors.add(Color.CYAN);
        ballColors.add(Color.PURPLE);
        ballColors.add(Color.FOREST);
        for (int i = 0; i < configsModel.getBallColors().size(); i++)
            assertEquals(configsModel.getBallColors().get(i),ballColors.get(i));
    }

    @Test
    public void getPlainColors() {
        ArrayList<Color>  plainColors = new ArrayList<Color>();
        plainColors.add(Color.VIOLET);
        plainColors.add(Color.NAVY);
        plainColors.add(Color.ROYAL);
        plainColors.add(Color.LIME);
        for (int i = 0; i < configsModel.getPlainColors().size(); i++)
            assertEquals(configsModel.getPlainColors().get(i),plainColors.get(i));
    }

    @Test
    public void getBackgroundColors() {
        ArrayList<Color>   backgroundColors = new ArrayList<Color>();
        backgroundColors.add(Color.BLACK);
        backgroundColors.add(Color.GRAY);
        backgroundColors.add(Color.SKY);
        backgroundColors.add(Color.FIREBRICK);
        for (int i = 0; i < configsModel.getBackgroundColors().size(); i++)
            assertEquals(configsModel.getBackgroundColors().get(i),backgroundColors.get(i));
    }
}