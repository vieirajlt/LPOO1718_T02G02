package com.gdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.BodyInstance;
import com.gdx.game.view.entities.BallView;
import com.gdx.game.view.entities.PlainView;

public class MapView {

    private static MapView instance = null;

    private ColorAttribute ambientLigth;
    private Array<DirectionalLight> directLigths;

   // private Array<ModelInstance> instances;

    private Array<BodyInstance> instances;
    private Environment environment;
    private ModelBatch modelBatch;

    private MapView() {
        modelBatch = new ModelBatch();

        environment = new Environment();

        ambientLigth = new ColorAttribute(ColorAttribute.AmbientLight, 0.4f,0.4f,0.4f,1f);

        directLigths = new Array<DirectionalLight>();
        directLigths.add(new DirectionalLight().set(0.8f,0.8f,0.8f,-1f,-0.8f,-0.2f));

        addLigthToEnvironment();

        instances = new Array<BodyInstance>();
    }

    private void addLigthToEnvironment() {
        environment.set(ambientLigth);

        for (DirectionalLight dl : directLigths) {
            environment.add(dl);
        }
    }

   public void addInstance(PlainView pv) {
        instances.add(pv.getModelInstance());
    }

    public void addInstance(BallView bv) {
        instances.add(bv.getModelInstance());
    }

    public void create() {

    }

    public void render(PerspectiveCamera camera) {
        clearScreen();

        modelBatch.begin(camera);
        modelBatch.render(instances, environment);
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    public ColorAttribute getAmbientLigth() {
        return ambientLigth;
    }

    public Array<DirectionalLight> getDirectLigths() {
        return directLigths;
    }

    public Array<BodyInstance> getInstances() {
        return instances;
    }

    public ModelBatch getModelBatch() {
        return modelBatch;
    }

    public static MapView getInstance() {
        if(instance == null)
            instance = new MapView();
        return instance;
    }

    public Environment getEnvironment() {
        return environment;
    }


}
