package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.gdx.game.utils.BodyInstance;

public class EntityView {

    private BodyInstance bodyInstance;

    public EntityView(){
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

    public void dispose() {
        bodyInstance.dispose();
    }
}
