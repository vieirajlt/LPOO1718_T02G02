package com.gdx.game.model.entities;

import com.gdx.game.model.entities.BallDecoratorModel;
import com.gdx.game.model.entities.BallModel;

public class DoubleScore extends BallDecoratorModel{

    private int scoreBonus = 2;

    public DoubleScore(BallModel ball)
    {
       super(ball);
    }

    public void increaseScoreCount(int value)
    {
        super.increaseScoreCount(value*scoreBonus);
    }
}

