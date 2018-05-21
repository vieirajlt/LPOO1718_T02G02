package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.gdx.game.model.entities.BallModel;
import com.gdx.game.model.entities.BonusModel;
import com.gdx.game.view.entities.BallView;
import com.gdx.game.view.entities.BonusView;

public class BonusController extends EntityController{

    private boolean visible = true;


    public BonusController() {
        super();
        BonusModel bonusModel = new BonusModel();
        setModel(bonusModel);
        setView(new BonusView(getModel().getModel(),new btSphereShape(bonusModel.getDiameter()/2),0f, bonusModel.getId()));
       // setView(new BallView(getModel().getModel()));
        updatePosition();
    }

   /* public BonusController(float x, float y, float z, float d) {
        super();

        setModel(new BallModel(x, y, z, d));
        //setView(new BallView(getModel().getModel()));
        setView(new BallView(getModel().getModel(),new btSphereShape(d/2),0f));
        updatePosition();
    }

    public void setDiameter(float d) {
        if(getModel() instanceof BonusModel) {
            ((BonusModel) getModel()).setDiameter(d);
           // setView(new BallView(getModel().getModel()));
            setView(new BonusView(getModel().getModel(),new btSphereShape(d/2),1f, "double"));
        }
    }*/

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
