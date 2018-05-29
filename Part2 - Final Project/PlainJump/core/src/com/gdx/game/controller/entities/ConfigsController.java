package com.gdx.game.controller.entities;

import com.badlogic.gdx.graphics.PerspectiveCamera;

public class ConfigsController {

    private static ConfigsController instance = null;

    public ConfigsController() {

    }

    public void create() {

    }

    public void render(PerspectiveCamera camera) {

    }

    public void dispose() {

    }

    public static ConfigsController getInstance() {
        if(instance == null)
            instance = new ConfigsController();
        return instance;
    }
}
