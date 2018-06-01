package com.gdx.game.view.entities;

import com.gdx.game.utils.BodyInstance;

/**
 * This Abstract class is used as base for all view classes
 * used that require physics for their movements.
 */
public abstract class EntityView {

    private BodyInstance bodyInstance;

    /**
     * Creates an EntityView without defined BodyInstance.
     */
    public EntityView(){
    }

    /**
     * Applies translation with coordinates (mX, mY, mz) to this bodyInstance.
     * @param mX this bodyInstance translation x component
     * @param mY this bodyInstance translation y component
     * @param mZ this bodyInstance translation z component
     */
    public void moveModelInstance(float mX, float mY, float mZ) {
        bodyInstance.transform.translate(mX, mY, mZ);
    }

    /**
     * Gets this view bodyInstance.
     * @return this bodyInstance
     */
    public BodyInstance getBodyInstance() {
        return bodyInstance;
    }

    /**
     * Sets this view bodyInstance.
     * @param bodyInstance this bodyInstance new value
     */
    public void setBodyInstance(BodyInstance bodyInstance) {
        this.bodyInstance = bodyInstance;
    }

    /**
     * Disposes of this bodyInstance.
     */
    public void dispose() {
        bodyInstance.dispose();
    }
}
