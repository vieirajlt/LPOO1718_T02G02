package com.gdx.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.gdx.game.controller.GameController;

public class MenuView {

    private TextButton playButton;
    private TextButton.TextButtonStyle playButtonStyle;

    private TextButton configsButton;
    private TextButton.TextButtonStyle configsButtonStyle;

    BitmapFont buttonFont;

    private Label highscoreLabel;
    private Label highscore;

    private Integer bestScore = 0;

    private Label.LabelStyle labelStyle;

    BitmapFont labelFont;

    private FreeTypeFontGenerator generator;

    private SpriteBatch spriteBatch;

    private Table table;
    private Stage stage;

    public MenuView() {

        spriteBatch = new SpriteBatch();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(table);


        //Font managing
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameterScore = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterScore.size = 45;
        buttonFont = generator.generateFont(parameterScore);
        //

        //loadBestScore();
        FreeTypeFontGenerator.FreeTypeFontParameter parameterLabel = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterLabel.size = 50;
        labelFont = generator.generateFont(parameterLabel);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = labelFont;
        highscoreLabel = new Label("highscore",labelStyle);
        table.add(highscoreLabel);
        table.row();
        highscore = new Label(bestScore.toString(),labelStyle);
        table.add(highscore);
        table.row();
        //saveBestScore();

        //Play Button
        playButtonStyle = new TextButton.TextButtonStyle();
        playButtonStyle.font = buttonFont;
        playButton = new TextButton("PLAY", playButtonStyle);
        playButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Into map");
                GameController.getInstance().setGameState(GameController.State.MAP);

            }
        } );

        table.add(playButton).height(250).maxHeight(50).bottom();
        //
        table.row();
        //Configs Button
        configsButtonStyle = playButtonStyle;
        configsButton = new TextButton("SETTINGS", configsButtonStyle);
        configsButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Into configs");
                //notificar o mapController nao sei bem como e terminar/ir para outro menu

            }
        } );

        table.add(configsButton);
        //
        //table.debug();
    }

    public void render() {
        Gdx.input.setInputProcessor(stage);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
        stage.dispose();
        generator.dispose();
    }

    public Integer getBestScore() {
        return bestScore;
    }

    public void setBestScore(Integer bestScore) {
        this.bestScore = bestScore;
        highscore.setText(bestScore.toString());
    }
}
