package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class BonusModel extends EntityModel{

    public enum BonusType {DOUBLE,TRIPLE,QUADRUPLE, IMMUNITY}

    private BonusType type;

    private int value;

    private boolean immune;

    private float diameter;

    private static  Material material ;

    private String id;

    public BonusModel() {
        this(BonusType.DOUBLE);
    }

    public BonusModel(float x, float y, float z, float d, BonusType type) {
        super(x, y, z);

        this.diameter = d;

        this.type = type;

        setValueMaterialAndId();

        setModel(buildModel());
    }

    public BonusModel(BonusType type)
    {
        this(0f, 1f, 0f, 1f, type);
    }

    //falta mudar isto para cilindros? as capsulas se calhar sao mais engracadas xd, eu depois mudo
    private Model buildModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.node().id = id;
        modelBuilder.part("sphere", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, material)
                .sphere(this.diameter, this.diameter, this.diameter, 30, 30);
        return modelBuilder.end();
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float d) {
        this.diameter = d;
        setModel(buildModel());
    }

    public BonusType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    private void setValueMaterialAndId() {
        switch (getType())
        {
            case DOUBLE:
                this.value = 2;
                this.immune = false;
                material = new Material(ColorAttribute.createDiffuse(Color.WHITE));
                id = "DoubleBonus";
                break;
            case TRIPLE:
                this.value = 3;
                this.immune = false;
                material = new Material(ColorAttribute.createDiffuse(Color.YELLOW));
                id = "TripleBonus";
                break;
            case QUADRUPLE:
                this.value = 4;
                this.immune = false;
                material = new Material(ColorAttribute.createDiffuse(Color.RED));
                id = "QuadrupleBonus";
                break;
            case IMMUNITY:
                this.value = 1;
                this.immune = true;
                material = new Material(ColorAttribute.createDiffuse(Color.GREEN));
                id = "ImmunityBonus";

        }
    }

    public String getId() {
        return id;
    }

    public boolean isImmune() {
        return immune;
    }
}
