package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.gdx.game.model.entities.BonusModel;
import com.gdx.game.view.entities.BonusView;

import java.util.Random;

/**
 * This class is used to control bonus placement.
 */
public class BonusController extends EntityController{

    /**
     * this ball visibility
     */
    private boolean visible = true;

    /**
     * Creates a new bonus with said type.
     * @param type this bonus type of advantage
     */
    public BonusController(BonusModel.BonusType type){
        super();
        BonusModel bonusModel = new BonusModel(type);
        setModel(bonusModel);
        setView(new BonusView(bonusModel.getId(),bonusModel.getInitialColor(),bonusModel.getDiameter(),new btSphereShape(bonusModel.getDiameter()/2),0f));
        updatePosition();
    }

    /**
     * Indicates if this bonus is set to be visible or not.
     * @return this BallController visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets this bonus visibility options.
     * @param visible this BallController visible new value
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    /**
     * Places this bonus in a semi random position
     * the x position is an int between -16 and 16
     * the y position is always set to 1
     * the z position is an int between -500 and 0 plus a given z coordinate
     * @param ballCurrentZ represents a z position from which the bonus placement will be done
     */
    public void placeBonus(float ballCurrentZ)
    {
        Random rand = new Random();
        int max = 16;
        int min = -16;
        int r = min + rand.nextInt(max*2 + 1);
        int z = rand.nextInt(500);
        getView().moveModelInstance(r, 1,-z+ballCurrentZ);
        setWorldTransform();
        updateModel();
    }

    /**
     * Replaces this bonus in a semi random position
     * the x position is an int between -16 and 16
     * the y position is always set to 0
     * the z position is an int between -500 and 0 plus a given z coordinate
     * @param ballCurrentZ represents a z position from which the bonus placement will be done
     */
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
