package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.gdx.game.utils.BodyInstance;

public class EntityView {

    private BodyInstance bodyInstance;

    //private Model model;

    public EntityView(){

    }

    public EntityView(BodyInstance bi) {

        bodyInstance = bi;
    }

    public void moveModelInstance(float mX, float mY, float mZ) {
        bodyInstance.transform.translate(mX, mY, mZ);
    }

    public BodyInstance getModelInstance() {
        return bodyInstance;
    }


    public void setBodyInstance(BodyInstance bodyInstance) {
        this.bodyInstance = bodyInstance;
    }

    /*public void setModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void dispose() {
        model.dispose();
    }*/
}
