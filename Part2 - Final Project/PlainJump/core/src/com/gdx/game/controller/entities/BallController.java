package com.gdx.game.controller.entities;

import com.gdx.game.model.entities.BallModel;
import com.gdx.game.view.entities.BallView;

public class BallController extends EntityController{

    private static BallController instance = null;

    private BallController() {
        super();

        setModel(new BallModel());
        setView(new BallView(getModel().getModel()));
        updatePosition();
    }

    private BallController(float x, float y, float z, float d) {
        super();

        setModel(new BallModel(x, y, z, d));
        setView(new BallView(getModel().getModel()));
        updatePosition();
    }

    public void setDiameter(float d) {
        if(getModel() instanceof BallModel) {
            ((BallModel) getModel()).setDiameter(d);
            setView(new BallView(getModel().getModel()));
        }
    }

    public static BallController getInstance() {
        if(instance == null)
            instance = new BallController();
        return instance;
    }

}
