package com.gdx.game.model.entities;

public class TripleScore extends BallDecoratorModel{

    private int scoreBonus = 3;

    public TripleScore(BallModel ball)
    {
       super(ball);
    }

    public void increaseScoreCount(int value)
    {
        super.increaseScoreCount(value*scoreBonus);
    }
}

