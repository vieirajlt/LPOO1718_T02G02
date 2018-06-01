package com.gdx.game.view.entities;

import com.gdx.game.utils.BodyInstance;

public abstract class EntityView {

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
