package com.gdx.game.controller.entities;

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

    public void render() {
        view.render();
    }



    public void dispose() {
        view.dispose();
    }

    public static ConfigsController getInstance() {
        if(instance == null)
            instance = new ConfigsController();
        return instance;
    }

    public void resize(int width, int height) {
        view.resize(width,height);
    }
}
