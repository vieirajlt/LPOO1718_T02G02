package com.gdx.game.view.entities;

import com.gdx.game.utils.BodyInstance;

public class EntityView {

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
