package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.gdx.game.model.entities.BonusModel;
import com.gdx.game.utils.BodyInstance;


public class BonusView extends EntityView {

    private float diameter;

    private static  Material material ;

    private String id;



  /* public BonusView(Model model, btCollisionShape shape, float mass, String id )
   {
       super(new BodyInstance(model, id, shape, mass));
   }*/

    public BonusView(BonusModel.BonusType type, float diameter, btCollisionShape shape, float mass)
    {
        super();
        setMaterialAndId(type);
        setBodyInstance(new BodyInstance(buildModel(diameter), id, shape,mass));

    }

    private Model buildModel(float diameter) {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        modelBuilder.node().id = id;
        modelBuilder.part("sphere", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, material)
                .sphere(diameter, diameter, diameter, 30, 30);
        return modelBuilder.end();
    }

    private void setMaterialAndId(BonusModel.BonusType type) {
        switch (type)
        {
            case DOUBLE:
                buildBonus(Color.WHITE, "DoubleBonus");
                break;
            case TRIPLE:
                buildBonus(Color.YELLOW, "TripleBonus");
                break;
            case QUADRUPLE:
                buildBonus(Color.RED, "QuadrupleBonus");
                break;
            case IMMUNITY:
                buildBonus(Color.GREEN, "ImmunityBonus");
                break;
        }
    }

    private void buildBonus(Color color, String id) {
        this.material = new Material(ColorAttribute.createDiffuse(color));
       this.id = id;
    }

}
