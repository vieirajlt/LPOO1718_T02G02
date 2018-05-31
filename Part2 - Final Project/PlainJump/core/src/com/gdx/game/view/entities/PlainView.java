package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.gdx.game.utils.BodyInstance;

public class PlainView extends EntityView {

    public  PlainView(int id, Model model, btCollisionShape shape, float mass)
    {
        super(new BodyInstance(model, String.format("plain%d", id), shape, mass));
    }


}
