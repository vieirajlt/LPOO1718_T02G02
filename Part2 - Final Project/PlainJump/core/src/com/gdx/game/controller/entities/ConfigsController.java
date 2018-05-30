package com.gdx.game.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.gdx.game.model.entities.ConfigsModel;
import com.gdx.game.view.entities.ConfigsView;

public class ConfigsController {

    private static ConfigsController instance = null;

    private ConfigsModel model;

    private ConfigsView view;

    public ConfigsController() {
        model = new ConfigsModel();
        view = new ConfigsView();
    }

    public void create() {

    }

    public void render(PerspectiveCamera camera) {
        update();
        view.render();
    }

    private void update() {
        handleInputs();
    }

    private void handleInputs() {

    }

    public void dispose() {
        view.dispose();
    }

    public static ConfigsController getInstance() {
        if(instance == null)
            instance = new ConfigsController();
        return instance;
    }
}
