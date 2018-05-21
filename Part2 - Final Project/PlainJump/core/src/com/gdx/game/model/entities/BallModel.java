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

    private boolean jump = false;

    //pensar melhor nisto;
    private int scoreCount = 0;
    //update score = scorecount += scoremultiplier * value
    private int scoreMultiplier = 1;

    private static final Material material = new Material(ColorAttribute.createDiffuse(Color.ORANGE));

    public BallModel() {
        this(0f, 10f, 0f, 2f);
    }

    public BallModel(float x, float y, float z, float d) {
        super(x, y, z);

        this.diameter = d;

        setModel(buildModel());
    }

    private Model buildModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.node().id = String.format("ball");
        modelBuilder.part("sphere", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, material)
                .sphere(this.diameter, this.diameter, this.diameter, 30, 30);
        return modelBuilder.end();
        //return modelBuilder.createBox(this.width, this.heigth, this.depth, GL20.GL_TRIANGLES, material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float d) {
        this.diameter = d;
        setModel(buildModel());
    }

    public boolean canJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    public int getScoreMultiplier() {
        return scoreMultiplier;
    }

    public void setScoreMultiplier(int scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }
}
