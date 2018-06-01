package com.gdx.game.model.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class BonusModelTest {
    BonusModel doubleBonus = new BonusModel(BonusModel.BonusType.DOUBLE);
    BonusModel tripleBonus = new BonusModel(BonusModel.BonusType.TRIPLE);
    BonusModel quadBonus = new BonusModel(BonusModel.BonusType.QUADRUPLE);
    BonusModel imBonus = new BonusModel(BonusModel.BonusType.IMMUNITY);

    @Test
    public void getDiameter() {
        assertEquals(doubleBonus.getDiameter(),1,0.01f);
        assertEquals(tripleBonus.getDiameter(),1,0.01f);
        assertEquals(quadBonus.getDiameter(),1,0.01f);
        assertEquals(imBonus.getDiameter(),1,0.01f);
    }

    @Test
    public void getType() {
        assertEquals(doubleBonus.getType(), BonusModel.BonusType.DOUBLE);
        assertEquals(tripleBonus.getType(), BonusModel.BonusType.TRIPLE);
        assertEquals(quadBonus.getType(), BonusModel.BonusType.QUADRUPLE);
        assertEquals(imBonus.getType(), BonusModel.BonusType.IMMUNITY);
    }

    @Test
    public void getValue() {
        assertEquals(doubleBonus.getValue(), 2);
        assertEquals(tripleBonus.getValue(), 3);
        assertEquals(quadBonus.getValue(), 4);
        assertEquals(imBonus.getValue(), 1);
    }

    @Test
    public void isImmune() {
        assertEquals(doubleBonus.isImmune(),false);
        assertEquals(tripleBonus.isImmune(),false);
        assertEquals(quadBonus.isImmune(),false);
        assertEquals(imBonus.isImmune(),true);
    }

    @Test
    public void getId() {
        assertEquals(doubleBonus.getId(), "DoubleBonus");
        assertEquals(tripleBonus.getId(), "TripleBonus");
        assertEquals(quadBonus.getId(), "QuadrupleBonus");
        assertEquals(imBonus.getId(), "ImmunityBonus");
    }


}