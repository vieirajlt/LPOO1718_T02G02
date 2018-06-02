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

    /**
     * this used font path
     */
    private static final String FONT_PATH = "fonts/myfont.ttf";

    /**
     * this settings button text
     */
    private static final String SETTINGS_BTN_TEXT = "SETTINGS";

    /**
     * this play button text
     */
    private static final String PLAY_BTN_TEXT = "PLAY";

    /**
     * this highscore label text
     */
    private static final String HIGHSCORE_LABEL_TEXT = "highscore";

    /**
     * this play button
     */
    private TextButton playButton;

    /**
     * this play button style
     */
    private TextButton.TextButtonStyle playButtonStyle;

    /**
     * this settings button
     */
    private TextButton configsButton;

    /**
     * this settings button style
     */
    private TextButton.TextButtonStyle configsButtonStyle;

    /**
     * this bitmap font
     */
    BitmapFont font;

    /**
     * this highscore label
     */
    private Label highscoreLabel;

    /**
     * this highscore actual value label
     */
    private Label highscore;

    /**
     * this labels style
     */
    private Label.LabelStyle labelStyle;

    /**
     * this FreeType extension font generator
     */
    private FreeTypeFontGenerator generator;

    /**
     * this sprite batch
     */
    private SpriteBatch spriteBatch;

    /**
     * this stage table
     */
    private Table table;

    /**
     * this stage
     */
    private Stage stage;

    /**
     * Creates a default entry view menu.
     */
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
        font = generator.generateFont(parameterScore);

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
        playButtonStyle.font = font;
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
        font = generator.generateFont(parameterLabel);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        highscoreLabel = new Label(HIGHSCORE_LABEL_TEXT,labelStyle);
        table.add(highscoreLabel);
        table.row();
        highscore = new Label(EntryModel.getInstance().getHighscore().toString(),labelStyle);
        table.add(highscore);
        table.row();
    }

    /**
     * Renders this EntryView.
     */
    public void render() {
        Gdx.input.setInputProcessor(stage);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    /**
     * Disposes of this EntryView.
     */
    public void dispose() {
        spriteBatch.dispose();
        stage.dispose();
        generator.dispose();
    }

    /**
     * Sets this shown highscore.
     * @param bestScore the new value of this highscore
     */
    public void setBestScore(Integer bestScore) {
        highscore.setText(bestScore.toString());
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
