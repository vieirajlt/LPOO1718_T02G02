package com.gdx.game.controller;

import com.gdx.game.controller.entities.ConfigsController;
import com.gdx.game.controller.entities.EntryController;
import com.gdx.game.controller.entities.MapController;
import com.gdx.game.model.GameModel;
import com.gdx.game.view.GameView;

public class GameController {

    private static GameController instance = null;

    private GameModel model;
    private GameView view;

    private MapController map;
    private EntryController menu;
    private ConfigsController configs;

    public enum State {
        MAP,
        MENU,
        CONFIGS;
    }

    private State gameState;

    public GameController() {
        map = MapController.getInstance();
        menu = EntryController.getInstance();
        configs = ConfigsController.getInstance();

        model = GameModel.getInstance();
        view = new GameView();

        model.loadSettings();

        setGameState(State.MENU);
    }

    public void setSettings() {
        menu.setBestScore(model.getHighscore());
        map.setBallInitialColor(model.getBallColor());
        map.setPlainsInitialColor(model.getPlainColor());
        map.setScreenColor(model.getBackgroundColor());
        map.setMusicOnFlag(model.isMusicOnFlag());
        System.out.println(model.isMusicOnFlag());
    }



    public void create() {
        map.create();
    }

    public void render() {
        map.render(view.getCamera());
        switch(gameState) {
            case MENU:
                menu.render();
                map.setMoving(false);
                break;
            case CONFIGS:
                configs.render();
                map.setMoving(false);
                break;
            default:
                break;
        }
    }

    public void dispose() {
        map.dispose();
        menu.dispose();
        configs.dispose();
    }

    public void resize(int width, int height) {

       menu.resize(width,height);
       map.resize(width,height);
       configs.resize(width,height);
    }

    public void setGameState(State gs) {
        this.gameState = gs;
        switch(gs) {
            case MENU:
                updateBestScore();
                resetGame();
                break;
            case CONFIGS:
                break;
            case MAP:
                map.setMoving(true);
                break;
        }
    }

    private void updateBestScore() {
        if(menu.getBestScore() < map.getScore()) {
            menu.setBestScore(map.getScore());
            model.setHighscore(map.getScore());
        }
    }

    public static GameController getInstance() {
        if(instance == null)
            instance = new GameController();
        return instance;
    }

    private void resetGame()
    {
        view.reset();
        view = GameView.getInstance();
        map.reset();
        map = MapController.getInstance();
        map.create();
        setSettings();
    }
}
