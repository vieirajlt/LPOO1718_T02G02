package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.gdx.game.BodyInstance;

public class BallView extends EntityView {

   /* public BallView(Model model) {

       super(new ModelInstance(model, "ball"));
    }*/

   public BallView(Model model, btCollisionShape shape, float mass)
   {
       super(new BodyInstance(model, "ball", shape, mass));
   }

}
