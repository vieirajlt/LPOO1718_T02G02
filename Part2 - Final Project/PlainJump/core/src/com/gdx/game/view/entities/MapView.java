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
import com.gdx.game.BodyInstance;
import com.gdx.game.controller.GameController;
import com.gdx.game.controller.entities.MapController;

public class MapView {

    private static MapView instance = null;

    private ColorAttribute ambientLigth;
    private Array<DirectionalLight> directLigths;
    private Array<BodyInstance> instances;
    private Environment environment;
    private ModelBatch modelBatch;

    private SpriteBatch spriteBatch;

    private int score;
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

        score = 0;
        scoreText = "score: 0";

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));

        //Score Label
        FreeTypeFontGenerator.FreeTypeFontParameter parameterScore = new FreeTypeFontGenerator.FreeTypeFontParameter();
        int letterSize = 40;
        parameterScore.size = letterSize;
        scoreFont = generator.generateFont(parameterScore);

        textStyle = new Label.LabelStyle();
        textStyle.font = scoreFont;
        text = new Label("score: 0",textStyle);
        table.add(text).height(Gdx.graphics.getHeight()/3).expandX().top().left().maxHeight(letterSize+10).padLeft(8).colspan(2);
        //

        addGameOverLabel();

        //Exit Button
        FreeTypeFontGenerator.FreeTypeFontParameter parameterButton = new FreeTypeFontGenerator.FreeTypeFontParameter();
        letterSize = 30;
        parameterButton.size = letterSize;
        buttonFont = generator.generateFont(parameterButton);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        exitButton = new TextButton("EXIT", textButtonStyle);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MAP Over");
                GameController.getInstance().setGameState(GameController.State.MENU);
            }
        } );
        table.row();
        table.add(exitButton).height(Gdx.graphics.getHeight()/3).bottom().left().maxHeight(letterSize+10).padLeft(8);
        //

        //Mute Button
        FreeTypeFontGenerator.FreeTypeFontParameter parameterMuteButton = new FreeTypeFontGenerator.FreeTypeFontParameter();
        letterSize = 30;
        parameterMuteButton.size = letterSize;
        buttonFont = generator.generateFont(parameterMuteButton);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = buttonFont;
        muteButton = new TextButton("MUTE", textButtonStyle);
        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Muted");
                if(MapController.getInstance().getMusicState() == MapController.MusicState.PLAY) {
                    MapController.getInstance().pauseBgMusic();
                } else {
                    MapController.getInstance().resumeBgMusic();
                }
            }
        } );
        table.add(muteButton).height(Gdx.graphics.getHeight()/3).bottom().right().maxHeight(letterSize+10).padRight(8);
        //

        //table.debug();

        gameOverView = false;
    }

    private void addGameOverLabel() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameterScore = new FreeTypeFontGenerator.FreeTypeFontParameter();
        int letterSize = 90;
        parameterScore.size = letterSize;
        scoreFont = generator.generateFont(parameterScore);

        textStyle = new Label.LabelStyle();
        textStyle.font = scoreFont;
        gameOverLabel = new Label("Game Over",textStyle);
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
        scoreText = "score: " + score;
        modelBatch.begin(camera);
        modelBatch.render(instances, environment);
        modelBatch.end();

        spriteBatch.begin();

        if(moving) {
            stage.draw();
            Gdx.input.setInputProcessor(stage);
        }
        //scoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        // scoreFont.draw(spriteBatch, scoreText, 550, 450);
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
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    public void reset() {
        instance = null;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameOverView() {
        if(!gameOverView) {
            gameOverLabel.setVisible(true);
            gameOverView = true;
        }
    }
}