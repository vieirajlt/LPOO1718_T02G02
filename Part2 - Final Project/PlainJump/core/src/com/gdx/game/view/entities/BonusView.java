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

/**
 * This class is used to store a BodyInstance that represents the visual
 * concept of the bonus that we represent to play the game as the bonus
 * the player can catch.
 */
public class BonusView extends EntityView {

    /**
     * Creates a bonus with a sphere BodyInstance with
     * specified diameter, color, shape and mass.
     * @param diameter this BodyInstance sphere diameter value
     * @param color this BodyInstance sphere color
     * @param shape this BodyInstance sphere shape
     * @param mass this BodyInstance sphere mass value
     */
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
