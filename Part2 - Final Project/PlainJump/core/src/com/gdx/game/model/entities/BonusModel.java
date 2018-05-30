package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;


//Nao sei se devia fazer classes derivadas
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
                buildBonus(2, false, Color.WHITE, "DoubleBonus");
                break;
            case TRIPLE:
                buildBonus(3, false, Color.YELLOW, "TripleBonus");
                break;
            case QUADRUPLE:
                buildBonus(4, false, Color.RED, "QuadrupleBonus");
                break;
            case IMMUNITY:
                buildBonus(1, true, Color.GREEN, "ImmunityBonus");
                break;
        }
    }

    private void buildBonus(int i, boolean b, Color color, String doubleBonus) {
        this.value = i;
        this.immune = b;
        material = new Material(ColorAttribute.createDiffuse(color));
        id = doubleBonus;
        setInitialColor(color);
    }

    public String getId() {
        return id;
    }

    public boolean isImmune() {
        return immune;
    }
}
