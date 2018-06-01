package com.gdx.game.controller.entities;

import com.gdx.game.model.entities.MenuModel;
import com.gdx.game.view.entities.MenuView;

public class MenuController {

    private static MenuController instance = null;

    private MenuModel model;
    private MenuView view;

    public MenuController() {
       model = MenuModel.getInstance();
        view = new MenuView();
    }

    public void render() {
        view.render();
    }

    public void dispose() {
        view.dispose();
    }

    public static MenuController getInstance() {
        if(instance == null)
            instance = new MenuController();
        return instance;
    }

    public Integer getBestScore() {
        return model.getHighscore();
    }

    public void setBestScore(Integer bs) {
        model.setHighscore(bs);
        view.setBestScore(bs);
    }

    public void resize(int width, int height) {
        view.resize(width,height);
    }
}
