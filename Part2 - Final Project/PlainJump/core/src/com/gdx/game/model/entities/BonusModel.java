package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class BonusModel extends EntityModel{

    public enum BonusType {DOUBLE,TRIPLE,QUADRUPLE, IMMUNITY};

    private BonusType type;

    private int value;

    private boolean immune;

    private float diameter;

    private String id;

    public BonusModel(float x, float y, float z, float d, BonusType type) {
        super(x, y, z);

        this.diameter = d;

        this.type = type;

        setValues();
    }

    public BonusModel(BonusType type)
    {
        this(0f, 1f, 0f, 1f, type);
    }

    public float getDiameter() {
        return diameter;
    }

    public BonusType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    private void setValues() {
        switch (getType())
        {
            case DOUBLE:
                setBonusValues(2, false,Color.WHITE, "DoubleBonus");
                break;
            case TRIPLE:
                setBonusValues(3, false,Color.YELLOW,"TripleBonus");
                break;
            case QUADRUPLE:
                setBonusValues(4, false,Color.RED, "QuadrupleBonus");
                break;
            case IMMUNITY:
                setBonusValues(1, true,Color.GREEN,"ImmunityBonus");
                break;
        }
    }

    private void setBonusValues(int i, boolean b, Color color, String id) {
        this.value = i;
        this.immune = b;
        this.id = id;
        setInitialColor(color);
    }

    public boolean isImmune() {
        return immune;
    }

    public String getId() {
        return id;
    }
}
