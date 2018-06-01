package com.gdx.game.controller.entities;

import com.gdx.game.model.entities.EntryModel;
import com.gdx.game.view.entities.EntryView;

/**
 * This class is used to control the entry menu.
 */
public class EntryController {

    /**
     * this Singleton Instance
     */
    private static EntryController instance = null;

    /**
     * This entry menu model
     */
    private EntryModel model;

    /**
     * This entry menu view
     */
    private EntryView view;

    /**
     * Creates the entry menu.
     */
    public EntryController() {
       model = EntryModel.getInstance();
        view = new EntryView();
    }

    /**
     * Renders this entry menu view
     */
    public void render() {
        view.render();
    }

    /**
     * Disposes of this entry menu view
     */
    public void dispose() {
        view.dispose();
    }

    /**
     * Gets this entry menu Singleton instance.
     * @return this EntryController instance.
     */
    public static EntryController getInstance() {
        if(instance == null)
            instance = new EntryController();
        return instance;
    }

    /**
     * Gets this entry menu set highscore
     * @return this EntryModel highscore
     */
    public Integer getBestScore() {
        return model.getHighscore();
    }

    /**
     * Sets this entry menu set highscore
     * @param bs this EntryModel highscore new value
     */
    public void setBestScore(Integer bs) {
        model.setHighscore(bs);
        view.setBestScore(bs);
    }

    /**
     * Resizes this entry menu to specified size.
     * @param width this entry menu view new width
     * @param height this entry menu view new height
     */
    public void resize(int width, int height) {
        view.resize(width,height);
    }
}
