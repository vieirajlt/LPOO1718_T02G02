package com.gdx.game.model.entities;

public class CoinModel extends EntityModel {

    private int value = 1;  //acho que 1 faz sentido, pode ser mais se quiseres

    public CoinModel(float x, float y, float z)
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
