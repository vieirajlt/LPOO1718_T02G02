package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.gdx.game.utils.BodyInstance;

public class BallView extends EntityView {

  /* public BallView(Model model, btCollisionShape shape, float mass)
   {
       super(new BodyInstance(model, "ball", shape, mass));
   }*/

    private static final Material material = new Material(ColorAttribute.createDiffuse(Color.ORANGE));


    public BallView(float diameter, btCollisionShape shape, float mass)
    {
        super();
        setBodyInstance(new BodyInstance(buildModel(diameter),"ball",shape,mass));

    }

    private Model buildModel(float diameter) {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.node().id = String.format("ball");
        modelBuilder.part("sphere", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, material)
                .sphere(diameter,diameter,diameter, 30, 30);
        return modelBuilder.end();
    }


}
