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


    private int scoreVelocityLimit = 1000;

    private float counter = 0;

    private float counterLimit = 8;

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

    public void incCounter(float inc)
    {
        this.counter += inc;
        //System.out.print("counter: ");
        //System.out.println(counter);
        //System.out.println(counterLimit);
        if (this.counter >= this.counterLimit && scoreMultiplier != 1)
        {
            //System.out.println("dwgcgchrkgegccbhejgcjeggcefhgchfhehcfvecvjfecvfvcejfgefchbfhebcj");
            scoreMultiplier = 1;
        }
    }





}
