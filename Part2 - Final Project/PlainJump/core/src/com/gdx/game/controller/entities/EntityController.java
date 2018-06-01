package com.gdx.game.controller.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.gdx.game.model.entities.EntityModel;
import com.gdx.game.view.entities.EntityView;

/**
 * This Abstract class is used as base for all classes that
 * have a virtual world placement, controlling mostly positioning.
 */
public abstract class EntityController {

    /**
     * this entity model
     */
    private EntityModel model;

    /**
     * this entity view
     */
    private EntityView view;

    /**
     * Creates a EntityController with no specified model or view.
     */
    public EntityController() {
    }

    /**
     * Gets this entity model.
     * @return this EntityController model
     */
    public EntityModel getModel() {
        return model;
    }

    /**
     * Gets this entity view.
     * @return this EntityController view
     */
    public EntityView getView() {
        return view;
    }

    /**
     * Gets this entity view rigidBody.
     * @return this EntityController view rigidBody
     */
    public btRigidBody getBody()
    {
        return getView().getBodyInstance().getRigidBody();
    }

    /**
     * Sets this EntityController model.
     * @param model this EntityController model new value
     */
    public void setModel(EntityModel model) {
        this.model = model;
    }

    /**
     * Sets this EntityController view.
     * @param view this EntityController view new value
     */
    public void setView(EntityView view) {
        this.view = view;
    }

    protected void updatePosition() {
        view.moveModelInstance(model.getPosX(), model.getPosY(), model.getPosZ());
    }

    /**
     * Sets this entity into a new position
     * @param posX this entity new x coordinate
     * @param posY this entity new y coordinate
     * @param posZ this entity new z coordinate
     */
    public void setPos(float posX, float posY, float posZ) {
        float mX = posX - model.getPosX();
        model.setPosX(posX);
        float mY = posY - model.getPosY();
        model.setPosX(posY);
        float mZ = posZ - model.getPosZ();
        model.setPosX(posZ);

        view.moveModelInstance(mX, mY, mZ);
        setWorldTransform();
    }

    /**
     * Translates this entity to a new position
     * @param posX this entity x translation
     * @param posY this entity y translation
     * @param posZ this entity z translation
     */
    public void moveToPos(float posX, float posY, float posZ) {
        view.moveModelInstance(posX, posY, posZ);
        setWorldTransform();
        updateModel();
    }

    /**
     * Calls bullet's getWorldTransform function
     *  For kinematic bodies this method is called to inform about the position every update
     *  For dynamic bodies this method is called once to get the initial state
     */
    public void getWorldTransform()
    {
        this.getView().getBodyInstance().getRigidBody().getWorldTransform(this.getView().getBodyInstance().transform);
    }

    /**
     * Calls bullet's setWorldTransform function
     *  For dynamic bodies this method is called to inform about the body's position every update
     */
    public void setWorldTransform()
    {
        this.getView().getBodyInstance().getRigidBody().setWorldTransform(this.getView().getBodyInstance().transform);
    }

    /**
     * Updates this model coordinates accordingly to this view rigidBody positioning.
     */
    public void updateModel()
    {
        getModel().setPosX(getBody().getCenterOfMassPosition().x);
        getModel().setPosY(getBody().getCenterOfMassPosition().y);
        getModel().setPosZ(getBody().getCenterOfMassPosition().z);
    }

    /**
     * Disposes of this Entity.
     */
    public void dispose() {
        view.dispose();
    }

    /**
     * Sets this model initial color.
     * @param color this model color new value
     */
    public void setInitialColor(Color color)
    {
        model.setInitialColor(color);
        setCurrentColor(color);
    }

    /**
     * Gets this model color.
     * @return this model color
     */
    public Color getInitialColor()
    {
        return model.getInitialColor();
    }


    /**
     * Sets this model current color.
     * @param color this view modelInstance and model new color
     */
    public void setCurrentColor(Color color)
    {
       ((ColorAttribute)getView().getBodyInstance().materials.get(0).get(ColorAttribute.Diffuse)).color.set(color);
        model.setCurrentColor(color);
    }
}
