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

public class PlainView extends EntityView {


    public  PlainView(int id, float w, float h, float d,Color color, btCollisionShape shape, float mass)
    {
        super();
        setBodyInstance(new BodyInstance(buildModel(id,w,h,d, color), String.format("plain%d", id), shape, mass));
    }


    private Model buildModel(int id,float w, float h, float d, Color color) {
        Material material = new Material(ColorAttribute.createDiffuse(color));
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.node().id = String.format("plain%d", id);
        modelBuilder.part("box", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, material)
                .box(w, h, d);
        return modelBuilder.end();
    }
}
