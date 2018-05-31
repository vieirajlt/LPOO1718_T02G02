package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.gdx.game.model.entities.PlainModel;
import com.gdx.game.view.entities.PlainView;

public class PlainController extends EntityController{

    private static int idCounter = 0;

    private int id;

    public PlainController() {
        super();

        this.id = ++idCounter;

        PlainModel plainModel = new PlainModel(this.id);

        setModel(plainModel);

        setView(new PlainView(this.id, getModel().getModel(), new btBoxShape(new Vector3(plainModel.getWidth()/2, plainModel.getHeight()/2, plainModel.getDepth()/2)),0f));
        updatePosition();
    }
}
