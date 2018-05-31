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
import com.gdx.game.model.entities.MenuModel;

public class MenuView {

    private TextButton playButton;
    private TextButton.TextButtonStyle playButtonStyle;

    private TextButton configsButton;
    private TextButton.TextButtonStyle configsButtonStyle;

    BitmapFont buttonFont;

    private Label highscoreLabel;
    private Label highscore;

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

        addHighScorelabels();

        //Play Button
        addPlayButton();
        //
        table.row();
        //Configs Button
        addConfigsButton();
        //
        //table.debug();
    }

    private void addConfigsButton() {
        configsButtonStyle = playButtonStyle;
        configsButton = new TextButton("SETTINGS", configsButtonStyle);
        configsButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Into configs");
                GameController.getInstance().setGameState(GameController.State.CONFIGS);
                //notificar o mapController nao sei bem como e terminar/ir para outro menu

            }
        } );

        table.add(configsButton);
    }

    private void addPlayButton() {
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
    }

    private void addHighScorelabels() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameterLabel = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterLabel.size = 50;
        labelFont = generator.generateFont(parameterLabel);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = labelFont;
        highscoreLabel = new Label("highscore",labelStyle);
        table.add(highscoreLabel);
        table.row();
        highscore = new Label(MenuModel.getInstance().getHighscore().toString(),labelStyle);
        table.add(highscore);
        table.row();
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

    public void setBestScore(Integer bestScore) {
        highscore.setText(bestScore.toString());
    }
}
