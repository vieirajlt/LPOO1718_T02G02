package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.gdx.game.model.entities.BallModel;
import com.gdx.game.view.entities.BallView;

/**
 * This class is used to control ball movements and status.
 * This represents the player.
 */
public class BallController extends EntityController{

    /**
     * this Singleton Instance
     */
    private static BallController instance = null;

    /**
     * ball movement lateral speed
     */
    private float lateralSpeed;
    /**
     * ball movement frontal speed
     */
    private float frontalSpeed;
    /**
     * ball movement constant rotation
     */
    private float rotationDegree;
    /**
     * ball movement jump force
     */
    private float jumpImpulse;
    /**
     * ball placement on plains
     */
    private int currentPlainIndex;


    private BallController() {
        super();
        BallModel ballModel = new BallModel();
        setModel(ballModel);
        setView(new BallView(((BallModel)getModel()).getDiameter(), getModel().getInitialColor(),new btSphereShape(ballModel.getDiameter()/2),1f));
        lateralSpeed = 1.5f;
        frontalSpeed = 0.3f;
        jumpImpulse = 1000f;
        rotationDegree = (frontalSpeed/(ballModel.getDiameter() * (float)Math.PI))*360;
        currentPlainIndex = 0;
        updatePosition();
    }

    /**
     * Restarts this instance.
     */
    public void reset() {
        instance = null;
    }

    /**
     * Gets BallController Singleton instance.
     * @return this BallController
     */
    public static BallController getInstance() {
        if(instance == null)
            instance = new BallController();
        return instance;
    }

    /**
     * Makes this ball jump.
     */
    public void jump() {
        updatePosition();
        if (canJump()) {
            getBody().applyCentralForce(new Vector3(0, jumpImpulse, 0));
            getModel().setPosY(getBody().getCenterOfMassPosition().y);
            getModel().setPosZ(getBody().getCenterOfMassPosition().z);
        }
    }

    /**
     * Make this ball go left.
     */
    public void moveLeft() {
        updatePosition();
       getBody().applyCentralImpulse(new Vector3(-lateralSpeed,0,0));
       getModel().setPosX(getBody().getCenterOfMassPosition().x);
    }

    /**
     * Make this ball go right.
     */
    public void moveRight() {
        updatePosition();
        getBody().applyCentralImpulse(new Vector3(lateralSpeed,0,0));
        getModel().setPosX(getBody().getCenterOfMassPosition().x);
    }

    /**
     * Make this ball move to the front.
     */
    public void moveFront() {
        updatePosition();
        getBody().translate(new Vector3(0,0,-frontalSpeed));
        getWorldTransform();
        getView().getBodyInstance().transform.rotate(new Vector3(1,0,0),rotationDegree);
        getBody().setWorldTransform(getView().getBodyInstance().transform);
        updateModel();
        updateFalling();
    }

    private void updateFalling()
    {
        if (getModel().getPosY() < MapController.getInstance().getMinY() - 10)
            ((BallModel) getModel()).setFalling(true);
        else
            ((BallModel) getModel()).setFalling(false);
    }

    /**
     * Gets info about this ball y position - if it is playable or not.
     * @return this BallModel falling
     */
    public boolean isFalling()
    {
        return ((BallModel) getModel()).isFalling();
    }

    /**
     * Sets this BallModel falling accordingly to  ball y positioning playability.
     * @param falling this BallModel falling value
     */
    public void setIsFalling(boolean falling)
    {
        ((BallModel) getModel()).setFalling(falling);
    }

    /**
     * Assures that ball frontal speed stays the same accordingly to this frontalSpeed
     */
    public void setFrontalLinearVelocity()
    {
        Vector3 v = getBody().getLinearVelocity();
        v.z = -frontalSpeed;
        getBody().setLinearVelocity(v);
    }

    /**
     * Increases frontal speed by inc.
     * @param inc the increment to this frontalSpeed
     */
    public void incLinearVelocity(float inc)
    {
        frontalSpeed += inc;
        setFrontalLinearVelocity();
    }

    /**
     * Checks if ball is allowed to jump.
     * @return this BallModel jump
     */
    public boolean canJump() {
        return ((BallModel)getModel()).canJump();
    }

    /**
     * Sets this ball ability to jump.
     * @param jump this BallModel jump new value
     */
    public void setJump(boolean jump) {
        ((BallModel)getModel()).setJump(jump);
    }

    /**
     * Gets this ball current plain placement.
     * @return this currentPlainIndex
     */
    public int getCurrentPlainIndex() {
        return currentPlainIndex;
    }

    /**
     * Sets this ball plain placement indicator.
     * @param currentPlainIndex this currentPlainIndex new value
     */
    public void setCurrentPlainIndex(int currentPlainIndex) {
        this.currentPlainIndex = currentPlainIndex;
    }

}
