package com.gdx.game.controller.entities;

import com.gdx.game.model.entities.PlainModel;
import com.gdx.game.view.entities.PlainView;

public class PlainController extends EntityController{

    private static int idCounter = 0;

    private int id;

    public PlainController() {
        super();

        this.id = ++idCounter;

        setModel(new PlainModel(this.id));
        setView(new PlainView(this.id, getModel().getModel()));
        updatePosition();
    }

    public PlainController(float x, float y, float z, float w, float h, float d) {
        super();

        this.id = ++idCounter;

        setModel(new PlainModel(this.id, x, y, z, w, h, d));
        setView(new PlainView(this.id, getModel().getModel()));
        updatePosition();
    }

    public void setDimensions(float w, float h, float d) {
        if(getModel() instanceof PlainModel) {
            ((PlainModel) getModel()).setWidth(id, d);
            ((PlainModel) getModel()).setHeigth(id, h);
            ((PlainModel) getModel()).setDepth(id, d);
            setView(new PlainView(this.id, getModel().getModel()));
        }
    }

    public int getId() {
        return id;
    }



}
