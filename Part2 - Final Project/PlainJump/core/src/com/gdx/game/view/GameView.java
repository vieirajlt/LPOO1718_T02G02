package com.gdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class GameView {

    private static GameView instance = null;

    private PerspectiveCamera camera;

    public GameView() {
        setCamera();
    }

    private void setCamera() {
        camera = new PerspectiveCamera(80, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 7f, 10f);
        camera.lookAt(0, 4f, 0);
        camera.far = 235f;
        camera.near = 1f;
        camera.update();
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public static GameView getInstance() {
        if(instance == null)
            instance = new GameView();
        return instance;
    }

    public void reset() {
        instance = null;
    }

}
