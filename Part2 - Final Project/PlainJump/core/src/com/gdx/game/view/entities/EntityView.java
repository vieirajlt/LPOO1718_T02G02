package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.gdx.game.BodyInstance;

public class EntityView {

   // private ModelInstance modelInstance;
    private float x;

    private BodyInstance bodyInstance;

    public EntityView(BodyInstance bi) {

        bodyInstance = bi;
    }

    public void moveModelInstance(float mX, float mY, float mZ) {
        bodyInstance.transform.translate(mX, mY, mZ);
    }

    public BodyInstance getModelInstance() {
        return bodyInstance;
    }
}
