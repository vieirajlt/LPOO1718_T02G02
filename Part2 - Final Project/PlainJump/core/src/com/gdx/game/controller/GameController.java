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

    private enum State {
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

        gameState = State.MAP;
    }

    public void create() {
        switch(gameState) {
            case MAP:
                map.create();
                break;
            case MENU:
                menu.create();
                break;
            case CONFIGS:
                configs.create();
                break;
        }
    }

    public void render(PerspectiveCamera camera) {
        switch(gameState) {
            case MAP:
                map.render(camera);
                break;
            case MENU:
                menu.render(camera);
                break;
            case CONFIGS:
                configs.render(camera);
                break;
        }
    }

    public void dispose() {
        map.dispose();
        menu.dispose();
        configs.dispose();
    }

    public static GameController getInstance() {
        if(instance == null)
            instance = new GameController();
        return instance;
    }


}
