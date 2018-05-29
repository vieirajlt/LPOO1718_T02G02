package com.gdx.game.controller.entities;

import com.badlogic.gdx.graphics.PerspectiveCamera;

public class MenuController {

    private static MenuController instance = null;

    public MenuController() {

    }

    public void create() {

    }

    public void render(PerspectiveCamera camera) {

    }

    public void dispose() {

    }

    public static MenuController getInstance() {
        if(instance == null)
            instance = new MenuController();
        return instance;
    }
}
