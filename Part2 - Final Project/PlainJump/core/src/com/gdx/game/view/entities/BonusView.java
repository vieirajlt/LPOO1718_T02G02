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


public class BonusView extends EntityView {

    public BonusView(String id, Color color, float diameter, btCollisionShape shape, float mass)
    {
        super();
        setBodyInstance(new BodyInstance(buildModel(diameter,id,color), id, shape,mass));
    }

    private Model buildModel(float diameter, String id, Color color) {
        Material material = new Material(ColorAttribute.createDiffuse(color));
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.node().id = id;
        modelBuilder.part("sphere", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, material)
                .sphere(diameter, diameter, diameter, 30, 30);
        return modelBuilder.end();
    }
}
