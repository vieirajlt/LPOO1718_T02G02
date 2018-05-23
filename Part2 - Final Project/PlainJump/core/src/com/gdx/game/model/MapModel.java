package com.gdx.game.model;

//honestly  nao sei para que e que isto serve, nao sei se vai ter a interface ou que vai ser aqui, not sure
// vou por aqui a cena dos scores, se nao concordares mudo para a bola
//falei com o senhor professor e ele concordou que fazer o decorator para isto ia ser muito complicado e que mais valia
//fazer como estou a fazer agora, fun, temos de arranjar mais padroes nesta PORRA
public class MapModel {

    private static MapModel instance = null;

    //score atual
    private int scoreCount = 0;

    //score multiplier atual
    private int scoreMultiplier = 1;

    //score multiplier anterior
    private int previousScoreMultiplier = 1;

    public MapModel() {

    }

    public static MapModel getInstance() {
        if(instance == null)
            instance = new MapModel();
        return instance;
    }

    /******************************************/


    //update ScoreCount tendo em considerecao o multiplier (parte dos bonus)
    public void updateScore(int delta) {
        scoreCount += scoreMultiplier*delta;
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
        this.previousScoreMultiplier = this.scoreMultiplier;
        this.scoreMultiplier *= scoreMultiplier;
    }

    public int getPreviousScoreMultiplier() {
        return previousScoreMultiplier;
    }

    public void setPreviousScoreMultiplier(int previousScoreMultiplier) {
        this.previousScoreMultiplier = previousScoreMultiplier;
    }

    public void setToPreviousScoreMultiplier()
    {
        setScoreMultiplier(this.previousScoreMultiplier);
    }
}
