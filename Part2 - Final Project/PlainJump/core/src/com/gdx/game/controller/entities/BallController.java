package com.gdx.game.controller.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.gdx.game.model.entities.BallModel;
import com.gdx.game.view.entities.BallView;

public class BallController extends EntityController{

    private static BallController instance = null;

    private float lateralSpeed;
    private float frontalSpeed;
    private float rotationDegree;
    private float jumpImpulse;
    private int currentPlainIndex = 0;


    private BallController() {
        super();
        BallModel ballModel = new BallModel();
        setModel(ballModel);
        setView(new BallView(((BallModel)getModel()).getDiameter(), getModel().getInitialColor(),new btSphereShape(ballModel.getDiameter()/2),1f));
        lateralSpeed = 1.5f;
        frontalSpeed = 0.3f;
        jumpImpulse = 1000f;
        rotationDegree = (frontalSpeed/(ballModel.getDiameter() * (float)Math.PI))*360;
        updatePosition();
    }

    public void reset() {
        instance = null;
    }

    public static BallController getInstance() {
        if(instance == null)
            instance = new BallController();
        return instance;
    }

    public void jump() {
        updatePosition();
        if (canJump()) {
            getBody().applyCentralForce(new Vector3(0, jumpImpulse, 0));
            getModel().setPosY(getBody().getCenterOfMassPosition().y);
            getModel().setPosZ(getBody().getCenterOfMassPosition().z);
        }
    }

    public void moveLeft() {
        updatePosition();
       getBody().applyCentralImpulse(new Vector3(-lateralSpeed,0,0));
       getModel().setPosX(getBody().getCenterOfMassPosition().x);
    }

    public void moveRight() {
        updatePosition();
        getBody().applyCentralImpulse(new Vector3(lateralSpeed,0,0));
        getModel().setPosX(getBody().getCenterOfMassPosition().x);
    }

    public void moveFront() {
        updatePosition();
        getBody().translate(new Vector3(0,0,-frontalSpeed));
        getWorldTransform();
        getView().getModelInstance().transform.rotate(new Vector3(1,0,0),rotationDegree);
        getBody().setWorldTransform(getView().getModelInstance().transform);
        updateModel();
        updateFalling();
    }

    private void updateFalling()
    {
        if (getModel().getPosY() < MapController.getInstance().getMinY() - 1)
            ((BallModel) getModel()).setFalling(true);
        else
            ((BallModel) getModel()).setFalling(false);
    }


    public boolean isFalling()
    {
        return ((BallModel) getModel()).isFalling();
    }

    public void setIsFalling(boolean falling)
    {
        ((BallModel) getModel()).setFalling(falling);
    }

    public void setLinearVelocity()
    {
        Vector3 v = getBody().getLinearVelocity();
        v.z = -frontalSpeed;
        getBody().setLinearVelocity(v);
    }

    public void incLinearVelocity(float inc)
    {
        frontalSpeed += inc;
        setLinearVelocity();
    }

    public boolean canJump() {
        return ((BallModel)getModel()).canJump();
    }

    public void setJump(boolean jump) {
        ((BallModel)getModel()).setJump(jump);
    }

    public int getCurrentPlainIndex() {
        return currentPlainIndex;
    }

    public void setCurrentPlainIndex(int currentPlainIndex) {
        this.currentPlainIndex = currentPlainIndex;
    }

}
