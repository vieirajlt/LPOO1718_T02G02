package com.gdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;

/**
 * This class is responsible for the entire game's camera management
 */
public class GameView {

    /**
     * this Singleton Instance
     */
    private static GameView instance = null;

    /**
     * this prespective camera
     */
    private PerspectiveCamera camera;

    /**
     * Creates a GameView with camera on specific position for game start.
     */
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

    /**
     * Gets this camera.
     * @return this camera
     */
    public PerspectiveCamera getCamera() {
        return camera;
    }

    /**
     * Gets this Singleton instance.
     * @return this GameView instance.
     */
    public static GameView getInstance() {
        if(instance == null)
            instance = new GameView();
        return instance;
    }

    /**
     * Sets this instance to null.
     */
    public void reset() {
        instance = null;
    }

}
