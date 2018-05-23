package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.gdx.game.model.entities.BallModel;
import com.gdx.game.view.entities.BallView;

public class BallController extends EntityController{

    private static BallController instance = null;


    private BallController() {
        super();

        BallModel ballModel = new BallModel();
        setModel(ballModel);
        setView(new BallView(getModel().getModel(),new btSphereShape(ballModel.getDiameter()/2),1f));
       // setView(new BallView(getModel().getModel()));
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
        if (canJump()) {
            getBody().applyCentralForce(new Vector3(0, 500, -100)); //com 500 fica melhor, acho que -100 fica bem, nao sei
            getModel().setPosY(getBody().getCenterOfMassPosition().y);
        }
    }

    public void moveLeft() {
       getBody().applyCentralImpulse(new Vector3(-2f,0,0));
       getModel().setPosX(getBody().getCenterOfMassPosition().x);
    }

    public void moveRight() {
        getBody().applyCentralImpulse(new Vector3(2f,0,0));
        getModel().setPosX(getBody().getCenterOfMassPosition().x);
    }

    public void moveFront(float velocity) {
        getBody().translate(new Vector3(0,0,-velocity));
        getWorldTransform();
        //nao sei esta parte interfere com o x da bola, nao devia mas acho que as vezes faz isso
        getView().getModelInstance().transform.rotate(new Vector3(1,0,0),2);
        getBody().setWorldTransform(getView().getModelInstance().transform);
        //////tenho que testar melhor
        getModel().setPosX(getBody().getCenterOfMassPosition().x);
        getModel().setPosY(getBody().getCenterOfMassPosition().y);
        getModel().setPosZ(getBody().getCenterOfMassPosition().z);
    }


    public boolean canJump() {
        return ((BallModel)getModel()).canJump();
    }

    public void setJump(boolean jump) {
        ((BallModel)getModel()).setJump(jump);
    }


}
