package com.gdx.game.controller;

import com.gdx.game.controller.entities.ConfigsController;
import com.gdx.game.controller.entities.EntryController;
import com.gdx.game.controller.entities.MapController;
import com.gdx.game.model.GameModel;
import com.gdx.game.view.GameView;

//TODO Commenting
public class GameController {

    /**
     * this Singleton Instance
     */
    private static GameController instance = null;

    //TODO
    private GameModel model;
    private GameView view;

    private MapController map;
    private EntryController menu;
    private ConfigsController configs;

    /**
     * This GameController possible states
     */
    public enum State {
        MAP,
        MENU,
        CONFIGS;
    }

    /**
     * This GameController current state
     */
    private State gameState;

    private GameController() {
        map = MapController.getInstance();
        menu = EntryController.getInstance();
        configs = ConfigsController.getInstance();

        model = GameModel.getInstance();
        view = new GameView();

        model.loadSettings();

        setGameState(State.MENU);
    }

    /**
     * TODO
     */
    public void setSettings() {
        menu.setBestScore(model.getHighscore());
        map.setBallInitialColor(model.getBallColor());
        map.setPlainsInitialColor(model.getPlainColor());
        map.setScreenColor(model.getBackgroundColor());
        map.setMusicOnFlag(model.isMusicOnFlag());
        System.out.println(model.isMusicOnFlag());
    }


    /**
     * Creates this GameController map
     */
    public void create() {
        map.create();
    }

    /**
     * Renders this GameController.
     */
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

    /**
     * Disposes of this GameController.
     */
    public void dispose() {
        map.dispose();
        menu.dispose();
        configs.dispose();
    }

    /**
     * Resizes this EntryController, MapController and ConfigsController to specified size.
     * @param width this entry menu view new width
     * @param height this entry menu view new height
     */
    public void resize(int width, int height) {

       menu.resize(width,height);
       map.resize(width,height);
       configs.resize(width,height);
    }

    /**
     * TODO
     * @param gs
     */
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

    /**
     * Gets GameController Singleton instance.
     * @return this GameController
     */
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
