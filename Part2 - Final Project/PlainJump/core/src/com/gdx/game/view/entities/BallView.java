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
 * concept of the ball that we represent to play the game as the player.
 */
public class BallView extends EntityView {

    /*******************CONSTRUCTORS*******************/

    /**
     * Creates a ball with a sphere BodyInstance with
     * specified diameter, color, shape and mass.
     * @param diameter this BodyInstance sphere diameter value
     * @param color this BodyInstance sphere color
     * @param shape this BodyInstance sphere shape
     * @param mass this BodyInstance sphere mass value
     */
    public BallView(float diameter, Color color, btCollisionShape shape, float mass)
    {
        super();
        setBodyInstance(new BodyInstance(buildModel(diameter,color),"ball",shape,mass));
    }

    private Model buildModel(float diameter, Color color) {
        Material material = new Material(ColorAttribute.createDiffuse(color));
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.node().id = String.format("ball");
        modelBuilder.part("sphere", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, material)
                .sphere(diameter,diameter,diameter, 30, 30);
        return modelBuilder.end();
    }
}
