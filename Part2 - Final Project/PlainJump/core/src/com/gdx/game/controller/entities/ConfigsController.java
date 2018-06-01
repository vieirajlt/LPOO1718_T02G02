package com.gdx.game.controller.entities;

import com.gdx.game.model.entities.ConfigsModel;
import com.gdx.game.view.entities.ConfigsView;

/**
 * This class is used to control the configurations menu.
 */
public class ConfigsController {

    private static ConfigsController instance = null;

    /**
     * This menu model
     */
    private ConfigsModel model;

    /**
     * this menu view
     */
    private ConfigsView view;

    /**
     * Creates a configurations menu.
     */
    public ConfigsController() {
      model = ConfigsModel.getInstance();
      view = new ConfigsView();
    }

    /**
     * Renders this menu.
     */
    public void render() {
        view.render();
    }

    /**
     * Disposes of this menu.
     */
    public void dispose() {
        view.dispose();
    }

    /**
     * Gets this menu Singleton instance.
     * @return this ConfigsController instance.
     */
    public static ConfigsController getInstance() {
        if(instance == null)
            instance = new ConfigsController();
        return instance;
    }

    /**
     * Resizes this menu to specified size.
     * @param width this menu view new width
     * @param height this menu view new height
     */
    public void resize(int width, int height) {
        view.resize(width,height);
    }
}
