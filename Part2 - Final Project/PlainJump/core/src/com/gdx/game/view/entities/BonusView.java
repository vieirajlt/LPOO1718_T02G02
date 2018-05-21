package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.gdx.game.BodyInstance;

public class BonusView extends EntityView {

   /* public BallView(Model model) {

       super(new ModelInstance(model, "ball"));
    }*/

   public BonusView(Model model, btCollisionShape shape, float mass, String id )
   {
       super(new BodyInstance(model, id, shape, mass));
   }

}
