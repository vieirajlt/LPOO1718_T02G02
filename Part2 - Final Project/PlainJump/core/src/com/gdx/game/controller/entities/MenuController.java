package com.gdx.game.controller.entities;

import com.gdx.game.model.entities.MenuModel;
import com.gdx.game.view.entities.MenuView;

public class MenuController {

    private static MenuController instance = null;

    private MenuModel model;
    private MenuView view;

    public MenuController() {
        model = new MenuModel();
        view = new MenuView();
    }

    public void create() {

    }

    public void render() {
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

    public static MenuController getInstance() {
        if(instance == null)
            instance = new MenuController();
        return instance;
    }
}
