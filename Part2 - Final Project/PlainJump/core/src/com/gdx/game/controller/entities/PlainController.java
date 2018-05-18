package com.gdx.game.controller.entities;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.gdx.game.model.entities.PlainModel;
import com.gdx.game.view.entities.PlainView;
import com.sun.security.cert.internal.x509.X509V1CertImpl;

public class PlainController extends EntityController{

    private static int idCounter = 0;

    private int id;

    public PlainController() {
        super();

        this.id = ++idCounter;

        setModel(new PlainModel(this.id));
        //setView(new PlainView(this.id, getModel().getModel()));


        setView(new PlainView(this.id, getModel().getModel(), new btBoxShape(new Vector3(4f, 1f, 12f)),1f));
        updatePosition();
    }

    public PlainController(float x, float y, float z, float w, float h, float d) {
        super();

        this.id = ++idCounter;

        setModel(new PlainModel(this.id, x, y, z, w, h, d));
        //setView(new PlainView(this.id, getModel().getModel()));
        setView(new PlainView(this.id, getModel().getModel(), new btBoxShape(new Vector3(w, h, d)),1f));

        updatePosition();
    }

    public void setDimensions(float w, float h, float d) {
        if(getModel() instanceof PlainModel) {
            ((PlainModel) getModel()).setWidth(id, w);
            ((PlainModel) getModel()).setHeigth(id, h);
            ((PlainModel) getModel()).setDepth(id, d);
           // setView(new PlainView(this.id, getModel().getModel()));
            //nao sei se é isto
            setView(new PlainView(this.id, getModel().getModel(), new btBoxShape(new Vector3(w, h, d)),1f));
        }
    }

    public int getId() {
        return id;
    }



}
