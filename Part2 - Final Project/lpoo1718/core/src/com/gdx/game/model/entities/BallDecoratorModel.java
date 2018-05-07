package com.gdx.game.model.entities;

public abstract class BallDecoratorModel extends BallModel{

   private BallModel ball;

   public BallDecoratorModel(BallModel ball)
   {

       super(0,0,0);  //not relevant
       this.ball = ball;
   }

    public void increaseScoreCount(int value)
    {
        ball.increaseScoreCount(value);
    }

    public boolean isFatality() {
        return ball.isFatality();
    }

    public boolean isFalling() {
        return ball.isFalling();
    }

    public MType getType()
    {
        return ball.getType();
    }
}

