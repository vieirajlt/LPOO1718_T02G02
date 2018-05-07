package com.gdx.game.model.entities;

import java.util.LinkedList;

public class PlainModel extends EntityModel {

    private float width;

    private float length;

    private float height;  // acho que nao vale a pena (metemos sempre igual nao?)

    private LinkedList<CoinModel> coins;  //se fizermos obstaculos no meio da pista podemos mete los aqui e os bonus tambem
    //talvez outra estrutura de dados tipo map para nao haverem 2 no mesmo sitio sendo a key um par de coordenadas (x,z)

    PlainModel(float x, float y, float z, float width, float length)
    {
        super(x,y,z);
        this.width = width;
        this.length = length;
        this.coins = new LinkedList<CoinModel>();
    }

    public float getWidth() {
        return width;
    }

    public float getLength() {
        return length;
    }

    public LinkedList<CoinModel> getCoins() {
        return coins;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setCoins(LinkedList<CoinModel> coins) {
        this.coins = coins;
    }

    public void addCoin(CoinModel coin)
    {
        this.coins.add(coin);
    }

    public MType getType()
    {
        return MType.PLAIN;
    }
}
