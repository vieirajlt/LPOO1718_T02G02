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


    private int currentPlainIndex = 0;

    private int switchCounterLimit = 10;

    private int switchCounter = switchCounterLimit;

    private BallController() {
        super();

        BallModel ballModel = new BallModel();
        setModel(ballModel);
        setView(new BallView(getModel().getModel(),new btSphereShape(ballModel.getDiameter()/2),1f));
        lateralSpeed = 10;
        frontalSpeed = 0.2f;
        rotationDegree = (frontalSpeed/(ballModel.getDiameter() * (float)Math.PI))*360;
        updatePosition();
    }

    private BallController(float x, float y, float z, float d) {
        super();

        setModel(new BallModel(x, y, z, d));
        setView(new BallView(getModel().getModel(),new btSphereShape(d/2),1f));
        updatePosition();
    }

    public void setDiameter(float d) {
        if(getModel() instanceof BallModel) {
            ((BallModel) getModel()).setDiameter(d);
            setView(new BallView(getModel().getModel(),new btSphereShape(d/2),1f));
        }
    }


    public static BallController getInstance() {
        if(instance == null)
            instance = new BallController();
        return instance;
    }

    public void jump() {
        updatePosition();
        if (canJump()) {
            getBody().applyCentralForce(new Vector3(0, 3000, 0));
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
        if (getModel().getPosY() < 0)
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

    public float getLateralSpeed() {
        return lateralSpeed;
    }

    public void setLateralSpeed(float lateralSpeed) {
        this.lateralSpeed = lateralSpeed;
    }

    public float getFrontalSpeed() {
        return frontalSpeed;
    }

    public void setFrontalSpeed(float frontalSpeed) {
        this.frontalSpeed = frontalSpeed;
    }


   /* public boolean isImmune()
    {
        return ((BallModel)getModel()).isImmune();
    }

    public void setImmune(boolean immune)
    {
        ((BallModel)getModel()).setImmune(immune);
    }*/

    public int getCurrentPlainIndex() {
        return currentPlainIndex;
    }

    public void setCurrentPlainIndex(int currentPlainIndex) {
        this.currentPlainIndex = currentPlainIndex;
    }

    public void switchColor(Color c1, Color c2)
    {
        Color c;
      if (getColor().equals(c1))
          c = c2;
      else
          c = c1;
      setColor(c);
    }

    public boolean incCounter()
    {
        this.switchCounter += 1;
        if (this.switchCounter >= this.switchCounterLimit)
            return true;
       return false;
    }

    public int getSwitchCounter() {
        return switchCounter;
    }

    public void setSwitchCounter(int switchCounter) {
        this.switchCounter = switchCounter;
    }

    public int getSwitchCounterLimit() {
        return switchCounterLimit;
    }

    public void setSwitchCounterLimit(int switchCounterLimit) {
        this.switchCounterLimit = switchCounterLimit;
    }
}
