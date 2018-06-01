package com.gdx.game.controller.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.gdx.game.model.entities.EntityModel;
import com.gdx.game.view.entities.EntityView;

public class EntityController {

    private EntityModel model;
    private EntityView view;

    public EntityController() {

    }

    public EntityModel getModel() {
        return model;
    }

    public EntityView getView() {
        return view;
    }

    public btRigidBody getBody()
    {
        return getView().getModelInstance().getRigidBody();
    }

    public void setModel(EntityModel model) {
        this.model = model;
    }

    public void setView(EntityView view) {
        this.view = view;
    }

    protected void updatePosition() {
        view.moveModelInstance(model.getPosX(), model.getPosY(), model.getPosZ());
    }

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

    public void moveToPos(float posX, float posY, float posZ) {
        view.moveModelInstance(posX, posY, posZ);
        setWorldTransform();
        updateModel();
    }

    public void getWorldTransform()
    {
        this.getView().getModelInstance().getRigidBody().getWorldTransform(this.getView().getModelInstance().transform);
    }

    public void setWorldTransform()
    {
        this.getView().getModelInstance().getRigidBody().setWorldTransform(this.getView().getModelInstance().transform);
    }


    public void updateModel()
    {
        getModel().setPosX(getBody().getCenterOfMassPosition().x);
        getModel().setPosY(getBody().getCenterOfMassPosition().y);
        getModel().setPosZ(getBody().getCenterOfMassPosition().z);
    }

    public void dispose() {
        view.dispose();
    }


    public void setInitialColor(Color color)
    {
        model.setInitialColor(color);
        setCurrentColor(color);
    }

    public Color getInitialColor()
    {
        return model.getInitialColor();
    }


    public void setCurrentColor(Color color)
    {
       ((ColorAttribute)getView().getModelInstance().materials.get(0).get(ColorAttribute.Diffuse)).color.set(color);
        model.setCurrentColor(color);
    }
}
