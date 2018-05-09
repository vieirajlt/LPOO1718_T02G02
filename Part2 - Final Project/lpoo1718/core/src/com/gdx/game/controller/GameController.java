package com.gdx.game.controller;

public class GameController {

    private static GameController instance;

    private GameController(){

    }

    public static GameController getInstance()
    {
        if(instance == null)
            instance = new GameController();
        return instance;
    }
}