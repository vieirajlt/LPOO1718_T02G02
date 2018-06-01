package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class PlainModel extends EntityModel {

    private float width;
    private float heigth;
    private float depth;

    public PlainModel() {
        this(0, 0, 0, 4, 1, 12);
    }

    public PlainModel(float x, float y, float z, float w, float h, float d) {

        super(x, y, z);

        this.width = w;
        this.heigth = h;
        this.depth = d;

        setInitialColor(Color.VIOLET);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return heigth;
    }

    public float getDepth() {
        return depth;
    }
}
