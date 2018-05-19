package com.gdx.game.controller.entities;

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
    }

    public void getWorldTransform()
    {
        this.getView().getModelInstance().getRigidBody().getWorldTransform(this.getView().getModelInstance().transform);
    }

    public void dispose() {
        model.dispose();
    }
}
