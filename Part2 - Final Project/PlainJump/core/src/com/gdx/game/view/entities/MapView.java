package com.gdx.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.gdx.game.controller.GameController;
import com.gdx.game.controller.entities.MapController;
import com.gdx.game.model.entities.MapModel;
import com.gdx.game.utils.BodyInstance;

//TODO descricao da classe mais 4 todos
public class MapView {

    /**
     * this used font path
     */
    private static final String FONT_PATH = "fonts/myfont.ttf";
    /**
     * this mute button text
     */
    private static final String MUTE_BTN_TEXT = "MUTE";
    //TODO QUE E ISTO?
    private static final String SCORE_LABEL_INI_TEXT = "score: 0";
    /**
     * this score label text
     */
    private static final String SCORE_LABEL_TEXT = "score: ";
    /**
     * this game over label text
     */
    private static final String GAME_OVER_LABEL_TEXT = "Game Over";
    /**
     * this exit label text
     */
    private static final String EXIT_BTN_TEXT = "EXIT";

    /**
     * this Singleton Instance
     */
    private static MapView instance = null;

    /**
     * This ambient light
     */
    private ColorAttribute ambientLigth;
    /**
     * This directional lights
     */
    private Array<DirectionalLight> directLigths;
    /**
     * This instances
     */
    private Array<BodyInstance> instances;
    /**
     * This environment
     */
    private Environment environment;
    /**
     * this model batch
     */
    private ModelBatch modelBatch;
    /**
     * This background rgb red value
     */
    private float screenRed;
    /**
     * This background rgb green value
     */
    private float screenGreen;
    /**
     * This background rgb blue value
     */
    private float screenBlue;
    /**
     * this sprite batch
     */
    private SpriteBatch spriteBatch;
    /**
     * this score text
     */
    private String scoreText;
    /**
     * this bitmap font
     */
    private BitmapFont scoreFont;
    /**
     * this stage
     */
    private  Stage stage;
    /**
     * this stage table
     */
    private Table table;
    /**
     * This score label
     */
    private Label text;
    /**
     * This game over label
     */
    private Label gameOverLabel;
    /**
     * This labels style
     */
    private Label.LabelStyle textStyle;
    /**
     * This exit button
     */
    private TextButton exitButton;
    /**
     * This mute button
     */
    private TextButton muteButton;
    /**
     * This buttons style
     */
    private TextButton.TextButtonStyle textButtonStyle;
    /**
     * This buttons bitmap font
     */
    private BitmapFont buttonFont;
    /**
     * this FreeType extension font generator
     */
    private FreeTypeFontGenerator generator;

    //TODO
    private boolean gameOverView;

    private MapView() {
        modelBatch = new ModelBatch();
        spriteBatch = new SpriteBatch();

        environment = new Environment();

        ambientLigth = new ColorAttribute(ColorAttribute.AmbientLight, 0.4f,0.4f,0.4f,1f);

        directLigths = new Array<DirectionalLight>();
        directLigths.add(new DirectionalLight().set(0.8f,0.8f,0.8f,-1f,-0.8f,-0.2f));

        addLigthToEnvironment();

        instances = new Array<BodyInstance>();

        stage = new Stage();

        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));

        addScoreLabel();

        addGameOverLabel();

        addExitButton();

        addMuteButton();

        gameOverView = false;
    }

    private void addMuteButton() {
        int letterSize;FreeTypeFontGenerator.FreeTypeFontParameter parameterMuteButton = new FreeTypeFontGenerator.FreeTypeFontParameter();
        letterSize = 30;
        parameterMuteButton.size = letterSize;
        buttonFont = generator.generateFont(parameterMuteButton);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        muteButton = new TextButton(MUTE_BTN_TEXT, textButtonStyle);
        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Muted");
                switch (MapController.getInstance().getMusicState()) {
                    case PLAY:
                        System.out.println("from PLAY");
                        MapController.getInstance().pauseBgMusic();
                        MapController.getInstance().setMusicOnFlag(false);
                        break;
                    case PAUSE:
                        System.out.println("from PAUSE");
                        MapController.getInstance().resumeBgMusic();
                        MapController.getInstance().setMusicOnFlag(true);
                        break;
                    case STOP:
                        System.out.println("from STOP");
                        MapController.getInstance().startBgMusic();
                        MapController.getInstance().setMusicOnFlag(true);
                        break;
                }
            }
        } );
        table.add(muteButton).height(Gdx.graphics.getHeight()/3).bottom().right().maxHeight(letterSize+10).padRight(8);
    }

    private void addExitButton() {
        int letterSize;FreeTypeFontGenerator.FreeTypeFontParameter parameterButton = new FreeTypeFontGenerator.FreeTypeFontParameter();
        letterSize = 30;
        parameterButton.size = letterSize;
        buttonFont = generator.generateFont(parameterButton);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        exitButton = new TextButton(EXIT_BTN_TEXT, textButtonStyle);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MAP Over");
                GameController.getInstance().setGameState(GameController.State.MENU);
            }
        } );
        table.row();
        table.add(exitButton).height(Gdx.graphics.getHeight()/3).bottom().left().maxHeight(letterSize+10).padLeft(8);
    }

    private void addScoreLabel() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameterScore = new FreeTypeFontGenerator.FreeTypeFontParameter();
        int letterSize = 40;
        parameterScore.size = letterSize;
        scoreFont = generator.generateFont(parameterScore);

        textStyle = new Label.LabelStyle();
        textStyle.font = scoreFont;
        text = new Label(SCORE_LABEL_INI_TEXT,textStyle);
        table.add(text).height(Gdx.graphics.getHeight()/3).expandX().top().left().maxHeight(letterSize+10).padLeft(8).colspan(2);
    }

    private void addGameOverLabel() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameterScore = new FreeTypeFontGenerator.FreeTypeFontParameter();
        int letterSize = 90;
        parameterScore.size = letterSize;
        scoreFont = generator.generateFont(parameterScore);

        textStyle = new Label.LabelStyle();
        textStyle.font = scoreFont;
        gameOverLabel = new Label(GAME_OVER_LABEL_TEXT,textStyle);
        gameOverLabel.setVisible(false);
        table.row();
        table.add(gameOverLabel).height(Gdx.graphics.getHeight()/3).expandX().colspan(2);
    }

    private void addLigthToEnvironment() {
        environment.set(ambientLigth);

        for (DirectionalLight dl : directLigths) {
            environment.add(dl);
        }
    }

    /**
     * Add to this MapView instances list the preferred PlainView
     * @param pv PlainView to be added
     */
    public void addInstance(PlainView pv) {
        instances.add(pv.getBodyInstance());
    }

    /**
     * Add to this MapView instances list the preferred BallView
     * @param bv BallView to be added
     */
    public void addInstance(BallView bv) {
        instances.add(bv.getBodyInstance());
    }

    /**
     * Add to this MapView instances list the preferred BonusView
     * @param bv BonusView to be added
     */
    public void addInstance(BonusView bv) {
        instances.add(bv.getBodyInstance());
    }

    /**
     * Renders this MapView
     * @param camera perspective camera
     * @param moving TODO
     */
    public void render(PerspectiveCamera camera, boolean moving) {

        clearScreen();
        scoreText = SCORE_LABEL_TEXT + MapModel.getInstance().getScoreCount();
        modelBatch.begin(camera);
        modelBatch.render(instances, environment);
        modelBatch.end();

        spriteBatch.begin();

        if(moving) {
            stage.draw();
            Gdx.input.setInputProcessor(stage);
        }
        text.setText(scoreText);
        spriteBatch.end();
    }

    /**
     * Disposes of this MapView.
     */
    public void dispose() {
        modelBatch.dispose();
        spriteBatch.dispose();
        stage.dispose();
        generator.dispose();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(screenRed, screenGreen, screenBlue, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Sets this background rgb values to the ones from the preferred color
     * @param color to set the background values to
     */
    public void setScreenColor(com.badlogic.gdx.graphics.Color color)
    {
        screenRed = color.r;
        screenGreen = color.g;
        screenBlue = color.b;
    }

    /**
     * Restarts this instance.
     */
    public void reset() {
        instance = null;
    }

    /**
     * Retrieve the value of this MapView instances.
     * @return this MapView instances
     */
    public Array<BodyInstance> getInstances() {
        return instances;
    }

    /**
     * Gets MapView Singleton instance.
     * @return this MapView
     */
    public static MapView getInstance() {
        if(instance == null)
            instance = new MapView();
        return instance;
    }

    //TODO
    public void setGameOverView() {
        if(!gameOverView) {
            gameOverLabel.setVisible(true);
            gameOverView = true;
        }
    }

    /**
     * Resizes this stage viewport accordingly to the given parameters.
     * @param width this stage new width
     * @param height this stage new height
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }
}