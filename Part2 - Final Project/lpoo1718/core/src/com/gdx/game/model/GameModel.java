package com.gdx.game.model;

import com.gdx.game.model.entities.BallModel;
import com.gdx.game.model.entities.PlainModel;

import java.util.LinkedList;


public class GameModel{


    private static GameModel instance;

    private BallModel ball;

    private LinkedList<PlainModel> plains; //not sure about this


    public GameModel()
    {
        plains = new LinkedList<PlainModel>();
        //fazer o primeiro plano onde a bola vais estar
        //criar a bola e adicionar lhe esse primeiro plano
        //criar mais planos aleatorios (dentro de um certo limite)

    }

    public static GameModel getInstance()
    {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    public BallModel getBall() {
        return ball;
    }

    public LinkedList<PlainModel> getPlains() {
        return plains;
    }

    public void setBall(BallModel ball) {
        this.ball = ball;
    }

    public void setPlains(LinkedList<PlainModel> plains) {
        this.plains = plains;
    }

    public void addPlain(PlainModel plain)
    {
        plains.add(plain);
    }
}