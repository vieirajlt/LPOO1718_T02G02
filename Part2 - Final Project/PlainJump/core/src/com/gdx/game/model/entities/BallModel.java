package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class BallModel extends EntityModel{

    private float diameter;

    private boolean jump = true;

    private boolean falling = false;

    /******************************************************************/

    public BallModel() {
        this(0f, 2f, 3f, 2f);
    }

    public BallModel(float x, float y, float z, float d) {
        super(x, y, z);

        this.diameter = d;

        setInitialColor(Color.ORANGE);
    }

    public float getDiameter() {
        return diameter;
    }

    public boolean canJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

}
