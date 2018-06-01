package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.gdx.game.model.entities.PlainModel;
import com.gdx.game.view.entities.PlainView;

/**
 * This class controlles plain placement.
 * This are the areas where the player(Ball) will move.
 */
public class PlainController extends EntityController{

    /**
     * this class id counter for id distribution
     */
    private static int idCounter = 0;

    /**
     * this plain id
     */
    private int id;

    /**
     * Creates a default plain with sequential id.
     */
    public PlainController() {
        super();

        this.id = ++idCounter;

        PlainModel plainModel = new PlainModel();

        setModel(plainModel);

        setView(new PlainView(this.id, plainModel.getWidth(), plainModel.getHeight(), plainModel.getDepth(),plainModel.getInitialColor(), new btBoxShape(new Vector3(plainModel.getWidth()/2, plainModel.getHeight()/2, plainModel.getDepth()/2)),0f));

        updatePosition();
    }
}
