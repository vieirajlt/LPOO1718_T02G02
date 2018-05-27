package com.gdx.game.model;


public class MapModel {

    private static MapModel instance = null;

    private int scoreCount = 0;

    private int scoreMultiplier = 1;

    private int scoreVelocityLimit = 1000;

    private float counter = 0;

    private float counterLimit = 10;

    //nao sei se isto devia ser aqui ou na bola
    private boolean immune = false;

    public MapModel() {

    }

    public static MapModel getInstance() {
        if(instance == null)
            instance = new MapModel();
        return instance;
    }

    /******************************************/


    //update ScoreCount tendo em considerecao o multiplier (parte dos bonus)
    public boolean updateScore(int delta) {
        scoreCount += scoreMultiplier*delta;
        if (scoreCount % scoreVelocityLimit == 0)
            return true;
        return false;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    public int getScoreMultiplier() {
        return scoreMultiplier;
    }

    public void setScoreMultiplier(int scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
        this.counter = 0;
    }

   /* public void incCounter(float inc)
    {
        this.counter += inc;
        if (this.counter >= this.counterLimit && (scoreMultiplier != 1 || immune == true))
        {
            scoreMultiplier = 1;
            immune = false;
        }
    }*/

    public boolean incCounter(float inc)
    {
        this.counter += inc;
        if (this.counter >= this.counterLimit && (scoreMultiplier != 1 || immune == true))
        {
            scoreMultiplier = 1;
            immune = false;
            return true;
        }
        return false;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }





}
