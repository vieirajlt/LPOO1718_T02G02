package com.gdx.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gdx.game.controller.GameController;
import com.gdx.game.model.entities.EntryModel;

public class EntryView {

    private static final String FONT_PATH = "fonts/myfont.ttf";
    private static final String SETTINGS_BTN_TEXT = "SETTINGS";
    private static final String PLAY_BTN_TEXT = "PLAY";
    private static final String HIGHSCORE_LABEL_TEXT = "highscore";

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

    public EntryView() {

        spriteBatch = new SpriteBatch();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(table);

        generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameterScore = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterScore.size = 45;
        buttonFont = generator.generateFont(parameterScore);

        addHighScorelabels();

        addPlayButton();
        table.row();
        addConfigsButton();
    }

    private void addConfigsButton() {
        configsButtonStyle = playButtonStyle;
        configsButton = new TextButton(SETTINGS_BTN_TEXT, configsButtonStyle);
        configsButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameController.getInstance().setGameState(GameController.State.CONFIGS);
            }
        } );

        table.add(configsButton);
    }

    private void addPlayButton() {
        playButtonStyle = new TextButton.TextButtonStyle();
        playButtonStyle.font = buttonFont;
        playButton = new TextButton(PLAY_BTN_TEXT, playButtonStyle);
        playButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
        highscoreLabel = new Label(HIGHSCORE_LABEL_TEXT,labelStyle);
        table.add(highscoreLabel);
        table.row();
        highscore = new Label(EntryModel.getInstance().getHighscore().toString(),labelStyle);
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

    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }
}
