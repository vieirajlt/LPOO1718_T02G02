package com.gdx.game.controller;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.gdx.game.controller.entities.ConfigsController;
import com.gdx.game.controller.entities.MapController;
import com.gdx.game.controller.entities.MenuController;
import com.gdx.game.model.GameModel;
import com.gdx.game.view.GameView;

public class GameController {

    private static GameController instance = null;

    private GameModel model;
    private GameView view;

    private MapController map;
    private MenuController menu;
    private ConfigsController configs;

    public enum State {
        MAP,
        MENU,
        CONFIGS;
    }

    private State gameState;

    public GameController() {
        map = MapController.getInstance();
        menu = MenuController.getInstance();
        configs = ConfigsController.getInstance();

        model = new GameModel();
        view = new GameView();

        gameState = State.MENU;
    }

    public void create() {
        map.create();
        switch(gameState) {
            case MENU:
                menu.create();
                break;
            case CONFIGS:
                configs.create();
                break;
            default:
                break;
        }
    }

    public void render(PerspectiveCamera camera) {
        map.render(camera);
        switch(gameState) {
            case MENU:
                menu.render();
                map.setMoving(false);
                break;
            case CONFIGS:
                configs.render(camera);
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

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public static GameController getInstance() {
        if(instance == null)
            instance = new GameController();
        return instance;
    }


}
