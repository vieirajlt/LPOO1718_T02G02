package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.gdx.game.model.entities.BallModel;
import com.gdx.game.model.entities.BonusModel;
import com.gdx.game.view.entities.BallView;
import com.gdx.game.view.entities.BonusView;

import java.util.Random;

public class BonusController extends EntityController{

    private boolean visible = true; //nao sei se devia ser aqui ou na view ou no model

    public BonusController(BonusModel.BonusType type){
        super();
        BonusModel bonusModel = new BonusModel(type);
        setModel(bonusModel);
        setView(new BonusView(getModel().getModel(),new btSphereShape(bonusModel.getDiameter()/2),0f, bonusModel.getId()));
        updatePosition();

    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    public void placeBonus(float ballCurrentZ)
    {
        Random rand = new Random();
        int max = 16;
        int min = -16;
        int r = min + rand.nextInt(max*2 + 1);
        int z = rand.nextInt(500);
        getView().moveModelInstance(r, 1,-z+ballCurrentZ); //nao funciona quando y= 0 (fica dentro do plano)
        setWorldTransform();
        updateModel();
    }


    public void replaceBonus(float ballCurrentZ)
    {
        setVisible(true);
        Random rand = new Random();
        int max = 16;
        int min = -16;
        int r = min + rand.nextInt(max*2 + 1);
        int z = rand.nextInt(500);
        getBody().translate(new Vector3(r,0,-z+ballCurrentZ));
        updateModel();
    }
}
