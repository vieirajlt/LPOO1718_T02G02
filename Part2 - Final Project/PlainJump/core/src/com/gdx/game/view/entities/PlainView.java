package com.gdx.game.view.entities;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class PlainView extends EntityView {

    public PlainView(int id, Model model) {
        super(new ModelInstance(model, String.format("plain%d", id)));
    }

}
