package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class EntityView {

    private ModelInstance modelInstance;

    public EntityView(ModelInstance mi) {

        modelInstance = mi;
    }

    public void moveModelInstance(float mX, float mY, float mZ) {
        modelInstance.transform.translate(mX, mY, mZ);
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }
}
