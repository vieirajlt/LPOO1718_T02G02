package com.gdx.game.model.entities;

import com.badlogic.gdx.graphics.Color;

/**
 * This class represents the model for the bonus
 */
public class BonusModel extends EntityModel{

    /**
     * This BonusModel posX default value
     */
    private static final float DEFAULT_POS_X = 0;
    /**
     * This BonusModel posY default value
     */
    private static final float DEFAULT_POS_Y = 1;
    /**
     * This BonusModel posZ default value
     */
    private static final float DEFAULT_POS_Z = 0;
    /**
     * This BonusModel diameter default value
     */
    private static final float DEFAULT_DIAMETER = 1;

    /**
     * Possible BonusModel types
     */
    public enum BonusType {DOUBLE,TRIPLE,QUADRUPLE, IMMUNITY};

    /**
     * This BonusModel type
     */
    private BonusType type;

    /**
     * This BonusModel value
     * is set according to the type
     */
    private int value;

    /**
     * This BonusModel immune
     * is set according to the type
     */
    private boolean immune;

    /**
     * This BonusModel diameter
     */
    private float diameter;

    /**
     * This BonusModel id
     * is set according to the type
     */
    private String id;

    /*******************CONSTRUCTORS*******************/

    /**
     * Creates a new BallModel with the stipulated default values and the preferred type.
     * @param type the new value of type
     */
    public BonusModel(BonusType type)
    {
        // this(0f, 1f, 0f, 1f, type);
        this(DEFAULT_POS_X,DEFAULT_POS_Y,DEFAULT_POS_Z,DEFAULT_DIAMETER, type);
    }


    /**
     * Creates a new BallModel with the preferred x, y, z, d and type values.
     * @param x the new value of posX
     * @param y the new value of posY
     * @param z the new value of posZ
     * @param d the new value of diameter
     * @param type the new value of type
     */
    public BonusModel(float x, float y, float z, float d, BonusType type) {
        super(x, y, z);

        this.diameter = d;

        this.type = type;

        setValues();
    }


    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieve the value of this BonusModel diameter.
     * @return this BonusModel diameter
     */
    public float getDiameter() {
        return diameter;
    }

    /**
     * Retrieve the value of this BonusModel type.
     * @return this BonusModel type
     */
    public BonusType getType() {
        return type;
    }

    /**
     * Retrieve the value of this BonusModel value.
     * @return this BonusModel value
     */
    public int getValue() {
        return value;
    }

    /**
     * Retrieve the value of this BonusModel immune.
     * @return this BonusModel immune
     */
    public boolean isImmune() {
        return immune;
    }

    /**
     * Retrive the value of this BonusModel id.
     * @return this BonusModel id
     */
    public String getId() {
        return id;
    }

    /*******************SET FUNCTIONS*******************/

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
}
