package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.gdx.game.model.entities.BallModel;
import com.gdx.game.view.entities.BallView;

public class BallController extends EntityController{

    private static BallController instance = null;

    private float lateralSpeed;
    private float frontalSpeed;
    private float rotationDegree;

    private BallController() {
        super();

        BallModel ballModel = new BallModel();
        setModel(ballModel);
        setView(new BallView(getModel().getModel(),new btSphereShape(ballModel.getDiameter()/2),1f));
       // setView(new BallView(getModel().getModel()));
        lateralSpeed = 10;
        frontalSpeed = 0.2f;
        rotationDegree = (frontalSpeed/(ballModel.getDiameter() * (float)Math.PI))*360;
        updatePosition();
    }

    private BallController(float x, float y, float z, float d) {
        super();

        setModel(new BallModel(x, y, z, d));
        //setView(new BallView(getModel().getModel()));
        setView(new BallView(getModel().getModel(),new btSphereShape(d/2),1f));
        updatePosition();
    }

    public void setDiameter(float d) {
        if(getModel() instanceof BallModel) {
            ((BallModel) getModel()).setDiameter(d);
           // setView(new BallView(getModel().getModel()));
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
            getBody().applyCentralForce(new Vector3(0, 2000, -300)); //com 500 fica melhor, acho que -50 fica bem, nao sei
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

        //moveToPos(0, 0, -frontalSpeed);
        getModel().setPosZ(-frontalSpeed + getModel().getPosZ());
        getBody().translate(new Vector3(0,0,-frontalSpeed));
        getWorldTransform();
        //nao sei esta parte interfere com o x da bola, nao devia mas acho que as vezes faz isso
        getView().getModelInstance().transform.rotate(new Vector3(1,0,0),rotationDegree);
        getBody().setWorldTransform(getView().getModelInstance().transform);
        //////tenho que testar melhor
       updateModel();
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

}
