//package com.gdx.game.view;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import com.gdx.game.LpooGame;
//import com.gdx.game.view.entities.HudView;
//
//public class GameView implements Screen {
//    private LpooGame game;
//    private OrthographicCamera gameCamera;
//    private Viewport gamePort;
//    private HudView hud;
//
//    public static final int WIDTH = 1280;
//    public static final int HEIGHT = 720;
//
//
//    public GameView(LpooGame game) {
//        this.game = game;
//        gameCamera = new OrthographicCamera();
//        gamePort = new FitViewport(WIDTH, HEIGHT, gameCamera);
//        hud = new HudView(game.getBatch());
//    }
//
//    /**
//     * Called when this screen becomes the current screen for a {@link Game}.
//     */
//    @Override
//    public void show() {
//
//    }
//
//    /**
//     * Called when the screen should render itself.
//     *
//     * @param delta The time in seconds since the last render.
//     */
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        //game.getBatch().setProjectionMatrix(gameCamera.combined);
//        game.getBatch().setProjectionMatrix(hud.getStage().getCamera().combined);
//        hud.getStage().draw();
//    }
//
//    /**
//     * @param width
//     * @param height
//     * @see ApplicationListener#resize(int, int)
//     */
//    @Override
//    public void resize(int width, int height) {
//        gamePort.update(width, height);
//    }
//
//    /**
//     * @see ApplicationListener#pause()
//     */
//    @Override
//    public void pause() {
//
//    }
//
//    /**
//     * @see ApplicationListener#resume()
//     */
//    @Override
//    public void resume() {
//
//    }
//
//    /**
//     * Called when this screen is no longer the current screen for a {@link Game}.
//     */
//    @Override
//    public void hide() {
//
//    }
//
//    /**
//     * Called when this screen should release all resources.
//     */
//    @Override
//    public void dispose() {
//
//    }
//}
