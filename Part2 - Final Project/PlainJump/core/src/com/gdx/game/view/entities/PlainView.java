package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.gdx.game.BodyInstance;
import com.sun.org.apache.xpath.internal.operations.Mod;

public class PlainView extends EntityView {

    /*
   public PlainView(int id, Model model) {
        super(new ModelInstance(model, String.format("plain%d", id)));
    }*/

    public  PlainView(int id, Model model, btCollisionShape shape, float mass)
    {
        super(new BodyInstance(model, String.format("plain%d", id), shape, mass));
    }


}
