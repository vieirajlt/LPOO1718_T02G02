package com.gdx.game.controller;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.controller.entities.BallController;
import com.gdx.game.controller.entities.PlainController;
import com.gdx.game.model.MapModel;
import com.gdx.game.model.entities.PlainModel;
import com.gdx.game.view.MapView;
import com.gdx.game.view.entities.BallView;
import com.gdx.game.view.entities.PlainView;

import java.util.Random;

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

    private void addPlains() {
        PlainController p1 = new PlainController();
        PlainController p2 = new PlainController();
        PlainController p3 = new PlainController();
        PlainController p4 = new PlainController();
        PlainController p5 = new PlainController();
        PlainController p6 = new PlainController();
        PlainController p7 = new PlainController();
        PlainController p8 = new PlainController();

        PlainController p9 = new PlainController();
        PlainController p10 = new PlainController();
        PlainController p11 = new PlainController();
        PlainController p12 = new PlainController();
        PlainController p13 = new PlainController();
        PlainController p14 = new PlainController();
        PlainController p15 = new PlainController();
        PlainController p16 = new PlainController();

        PlainController p17 = new PlainController();
        PlainController p18 = new PlainController();
        PlainController p19 = new PlainController();
        PlainController p20 = new PlainController();
        PlainController p21 = new PlainController();
        PlainController p22 = new PlainController();
        PlainController p23 = new PlainController();
        PlainController p24 = new PlainController();

        plains.add(p1);
        plains.add(p2);
        plains.add(p3);
        plains.add(p4);
        plains.add(p5);
        plains.add(p6);
        plains.add(p7);
        plains.add(p8);

        plains.add(p9);
        plains.add(p10);
        plains.add(p11);
        plains.add(p12);
        plains.add(p13);
        plains.add(p14);
        plains.add(p15);
        plains.add(p16);

        plains.add(p17);
        plains.add(p18);
        plains.add(p19);
        plains.add(p20);
        plains.add(p21);
        plains.add(p22);
        plains.add(p23);
        plains.add(p24);
    }

    private void placePlains() {
        boolean spanwPlain = true;

        Random rand = new Random();
        int position[] = {-16, -12, -8, -4, 0, 4, 8, 12, 16};
        int loc = 0;
        for(PlainController pc : plains) {
            view.addInstance((PlainView) (pc.getView()));

            int r = rand.nextInt(position.length);

            if(spanwPlain)
                spanwPlain = false;
            else
                pc.getView().moveModelInstance(position[r], 0, -loc*(((PlainModel) (pc.getModel())).getDepth()));
            ++loc;
            if(loc%8 == 0) {
                loc = 0;
            }
        }
    }

    private void buildPlains() {
        plains = new Array<PlainController>();

        addPlains();

        placePlains();
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
