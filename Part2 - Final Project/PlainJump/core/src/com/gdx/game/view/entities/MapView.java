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

public class MapView {

    private static final String FONT_PATH = "fonts/myfont.ttf";
    private static final String MUTE_BTN_TEXT = "MUTE";
    private static final String SCORE_LABEL_INI_TEXT = "score: 0";
    private static final String SCORE_LABEL_TEXT = "score: ";
    private static final String GAME_OVER_LABEL_TEXT = "Game Over";
    private static final String EXIT_BTN_TEXT = "EXIT";

    private static MapView instance = null;

    private ColorAttribute ambientLigth;
    private Array<DirectionalLight> directLigths;
    private Array<BodyInstance> instances;
    private Environment environment;
    private ModelBatch modelBatch;

    private float screenRed;
    private float screenGreen;
    private float screenBlue;

    private SpriteBatch spriteBatch;

    private String scoreText;
    private BitmapFont scoreFont;

    private  Stage stage;
    private Table table;
    private Label text;
    private Label gameOverLabel;
    private Label.LabelStyle textStyle;

    private TextButton exitButton;
    private TextButton muteButton;
    private TextButton.TextButtonStyle textButtonStyle;
    private BitmapFont buttonFont;

    private FreeTypeFontGenerator generator;

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

        //Score Label
        addScoreLabel();
        //

        addGameOverLabel();

        //Exit Button
        addExitButton();
        //

        //Mute Button
        addMuteButton();
        //

        //table.debug();

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

    public void addInstance(PlainView pv) {
        instances.add(pv.getModelInstance());
    }

    public void addInstance(BallView bv) {
        instances.add(bv.getModelInstance());
    }

    public void addInstance(BonusView bv) {
        instances.add(bv.getModelInstance());
    }

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


    public void setScreenColor(com.badlogic.gdx.graphics.Color color)
    {
        screenRed = color.r;
        screenGreen = color.g;
        screenBlue = color.b;
    }

    public void reset() {
        instance = null;
    }

    public Array<BodyInstance> getInstances() {
        return instances;
    }

    public static MapView getInstance() {
        if(instance == null)
            instance = new MapView();
        return instance;
    }

    public void setGameOverView() {
        if(!gameOverView) {
            gameOverLabel.setVisible(true);
            gameOverView = true;
        }
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }
}