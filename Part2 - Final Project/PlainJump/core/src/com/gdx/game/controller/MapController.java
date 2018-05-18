package com.gdx.game.controller;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.controller.entities.BallController;
import com.gdx.game.controller.entities.PlainController;
import com.gdx.game.model.MapModel;
import com.gdx.game.view.MapView;
import com.gdx.game.view.entities.BallView;
import com.gdx.game.view.entities.PlainView;

public class MapController {

    private static MapController instance = null;


    private MapModel model;
    private MapView view;

    private Array<PlainController> plains;
    private BallController ball;

    private MapController() {
        model = MapModel.getInstance();
        view = MapView.getInstance();

        buildPlains();

        buildBall();
    }

    private void buildPlains() {
        plains = new Array<PlainController>();

        PlainController p1 = new PlainController();

        plains.add(p1);

        for(PlainController pc : plains) {
            view.addInstance((PlainView) (pc.getView()));
        }
    }

    private void buildBall() {

        ball = BallController.getInstance();

        view.addInstance((BallView) (ball.getView()));
    }

    public void create() {
        view.create();
    }

    public void render(PerspectiveCamera camera) {
        view.render(camera);
    }

    public void dispose() {
        view.dispose();

        for(PlainController pc : plains) {
            pc.dispose();
        }
    }

    public static MapController getInstance() {
        if(instance == null)
            instance = new MapController();
        return instance;
    }
}
