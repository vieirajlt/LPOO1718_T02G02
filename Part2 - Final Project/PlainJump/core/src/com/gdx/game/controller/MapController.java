package com.gdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
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


    private btCollisionConfiguration collisionConfig;
    private btDispatcher dispatcher;
    private btBroadphaseInterface broadphase;
    private btDynamicsWorld world;
    private btConstraintSolver constraintSolver;

    private MapController() {
        model = MapModel.getInstance();
        view = MapView.getInstance();

        buildWorld();

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


      /*  world.addRigidBody(p1.getView().getModelInstance().getRigidBody());

        p1.getView().getModelInstance().getRigidBody().setContactCallbackFlag(1 << 8);
        p1.getView().getModelInstance().getRigidBody().setContactCallbackFilter(0);*/
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

    private void buildWorld()
    {
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        world = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfig);

        world.setGravity(new Vector3(0, -10f, 0));

    }

    private void buildPlains() {
        plains = new Array<PlainController>();

        addPlains();

        placePlains();
    }

    private void buildBall() {

        ball = BallController.getInstance();

        view.addInstance((BallView) (ball.getView()));

        world.addRigidBody(ball.getView().getModelInstance().getRigidBody());

    }

    public void create() {
        view.create();
    }

    public void render(PerspectiveCamera camera) {


        final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());

        world.stepSimulation(delta, 5, 1f / 60f);

        ball.getView().getModelInstance().getRigidBody().getWorldTransform(ball.getView().getModelInstance().transform);


        view.render(camera);

    }

    public void dispose() {
        view.dispose();

        for(PlainController pc : plains) {
            pc.dispose();
        }

        world.dispose();
        constraintSolver.dispose();
        broadphase.dispose();
        dispatcher.dispose();
        collisionConfig.dispose();
    }

    public static MapController getInstance() {
        if(instance == null)
            instance = new MapController();
        return instance;
    }
}
