package com.gdx.game.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.gdx.game.model.entities.MenuModel;
import com.gdx.game.view.entities.MenuView;

public class MenuController {

    private static final String HS_DATA_FILE = "/data/hs-v1.json";

    private static MenuController instance = null;

    private MenuModel model;

    private MenuView view;

    public MenuController() {
        model = new MenuModel();
        view = new MenuView();
        loadBestScore();
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

    public void saveBestScore() {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        FileHandle file = Gdx.files.local(HS_DATA_FILE);
        file.writeString(Base64Coder.encodeString(json.toJson(view.getBestScore())), false);
    }

    public void loadBestScore() {
        Json json = new Json();
        FileHandle file = Gdx.files.local(HS_DATA_FILE);
        Integer bs = json.fromJson(Integer.class, Base64Coder.decodeString(file.readString()));
        view.setBestScore(bs);
    }

    public Integer getBestScore() {
        return view.getBestScore();
    }

    public void setBestScore(Integer bs) {
        view.setBestScore(bs);
    }

}
