package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class BallView extends EntityView {

    public BallView(Model model) {

       super(new ModelInstance(model, "ball"));
    }

}
