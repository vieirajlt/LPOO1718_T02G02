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

    private static final int[] position =  {-16, -12, -8, -4, 0, 4, 8, 12, 16}; //nao sei se devia ser aqui ou na view ou no model


    public BonusController() {
        super();
        BonusModel bonusModel = new BonusModel();
        setModel(bonusModel);
        setView(new BonusView(getModel().getModel(),new btSphereShape(bonusModel.getDiameter()/2),0f, bonusModel.getId()));
       updatePosition();
    }

    public BonusController(BonusModel.BonusType type){
        super();
        BonusModel bonusModel = new BonusModel(type);
        setModel(bonusModel);
        setView(new BonusView(getModel().getModel(),new btSphereShape(bonusModel.getDiameter()/2),0f, bonusModel.getId()));
        updatePosition();

    }

    public BonusController(float x, float y, float z, float d, BonusModel.BonusType type) {
        super();

        BonusModel bonusModel = new BonusModel(x, y, z, d, type);
        setModel(bonusModel);
        setView(new BonusView(getModel().getModel(),new btSphereShape(d/2),0f, bonusModel.getId()));
        updatePosition();
    }

    public void setDiameter(float d) {
        if(getModel() instanceof BonusModel) {
            ((BonusModel) getModel()).setDiameter(d);
            setView(new BonusView(getModel().getModel(),new btSphereShape(d/2),1f, ((BonusModel) getModel()).getId()));
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    //falta atualizar no model eu acho
    public void placeBonus(float ballCurrentZ)
    {
        Random rand = new Random();
        int max = 16;
        int min = -16;
        int r = rand.nextInt(position.length);
        int z = rand.nextInt(200);
        getView().moveModelInstance(r, 1,-z+ballCurrentZ); //nao funciona quando y= 0 (fica dentro do plano
      // updateModel(); nao faz sentido aqui
    }

    //falta atualizar no model eu acho
    public void replaceBonus(float ballCurrentZ)
    {
        setVisible(true);
        Random rand = new Random();
        int max = 16;
        int min = -16;
         int r = rand.nextInt(position.length);
        int z = rand.nextInt(200);
        getBody().translate(new Vector3(r,0,-z+ballCurrentZ));
        updateModel(); /// aqui faz
    }
}
