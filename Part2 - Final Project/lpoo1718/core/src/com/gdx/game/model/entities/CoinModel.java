package com.gdx.game.model.entities;

public class CoinModel extends EntityModel {

    private int value = 1;  //nao sei quanto é

    CoinModel(float x, float y, float z)
    {
        super(x,y,z);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MType getType()
    {
        return MType.COIN;
    }
}
