package com.gdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.gdx.game.controller.entities.ConfigsController;
import com.gdx.game.controller.entities.MapController;
import com.gdx.game.controller.entities.MenuController;
import com.gdx.game.model.GameModel;
import com.gdx.game.view.GameView;

public class GameController {

    private PerspectiveCamera camera;
    private CameraInputController cameraController;

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

        setCamera();

        setGameState(State.MENU);
    }

    private void setCamera() {
        camera = new PerspectiveCamera(80, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 7f, 10f);
        camera.lookAt(0, 4f, 0);
        camera.far = 100f;
        camera.near = 1f;
        camera.update();
    }

    public void create() {
        map.create();
        menu.create();
        configs.create();
    }

    public void render() {
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

    public void setGameState(State gs) {
        this.gameState = gs;
        switch(gs) {
            case MENU:
                updateBestScore();
                setCamera();
                resetMap();
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
            menu.saveBestScore();
        }
    }

    public static GameController getInstance() {
        if(instance == null)
            instance = new GameController();
        return instance;
    }

    private void resetMap()
    {
        map.reset();
        map = MapController.getInstance();
        map.create();
    }
}
