package com.gdx.game.model.entities.boost;

import com.gdx.game.model.entities.EntityModel;

//se o BoostModel for so para as imagens isto deve chegar
    public class BoostModel extends EntityModel {

    public enum BoostType {DOUBLESCORE, TRIPLESCORE,INVULNERABILITY};

    private BoostType type;

    public BoostModel(float x, float y, float z, BoostType type)
    {
        super(x,y,z);
        this.type = type;
    }

    public BoostType getBoostType()
    {
        return type;
    }

    //nao sei se aqui tambem tem de diferenciar os tipos de boost
    public MType getType()
    {
        return MType.BOOST;
    }


}
