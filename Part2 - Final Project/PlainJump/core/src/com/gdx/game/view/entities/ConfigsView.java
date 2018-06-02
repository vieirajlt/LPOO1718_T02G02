package com.gdx.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gdx.game.controller.GameController;
import com.gdx.game.controller.entities.MapController;
import com.gdx.game.model.GameModel;
import com.gdx.game.model.entities.ConfigsModel;

import java.util.ArrayList;

/**
 * This class represents the view for the game's configuration menu.
 */
public class ConfigsView {

    /**
     * this used font path
     */
    private static final String FONT_PATH = "fonts/myfont.ttf";
    /**
     * this play button text
     */
    private static final String PLAY_BTN_TEXT = "PLAY";
    /**
     * this ball settings label text
     */
    private static final String BALL_LABEL_TEXT = "Ball";
    /**
     * this plain settings label text
     */
    private static final String PLAIN_LABEL_TEXT = "Plain";
    /**
     * this background settings label text
     */
    private static final String BG_LABEL_TEXT = "BackGround";
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
     * this ball configuration buttons list
     */
    private ArrayList<ImageButton> ballButtons;
    /**
     * this plain configuration buttons list
     */
    private ArrayList<ImageButton> plainsButtons;
    /**
     * this background configuration buttons list
     */
    private ArrayList<ImageButton> screenButtons;
    /**
     * this play button
     */
    private TextButton playButton;
    /**
     * this play button style
     */
    private TextButton.TextButtonStyle playButtonStyle;
    /**
     * this bitmap font
     */
    private BitmapFont playButtonFont;

    /**
     * this FreeType extension font generator
     */
    private FreeTypeFontGenerator generator;

    /**
     * this pixmap
     */
    private Pixmap pix;

    /**
     * this labels style
     */
    private Label.LabelStyle labelStyle;
    /**
     * this labels font
     */
    private BitmapFont labelFont;
    /**
     * this ball settings label
     */
    private Label ballLabel;
    /**
     * this plain settings label
     */
    private Label plainLabel;
    /**
     * this background settings label
     */
    private Label screenLabel;

    /**
     * Creates a default configs view menu.
     */
    public ConfigsView() {

        spriteBatch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(table);

        generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));

        addLabels();

        table.row();

        pix = new Pixmap(30,30, Pixmap.Format.RGBA8888);

        AddColorsButtons();

        addPlayButton();

    }

    private void addLabels() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameterLabel = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterLabel.size = 50;
        labelFont = generator.generateFont(parameterLabel);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = labelFont;
        ballLabel = new Label(BALL_LABEL_TEXT,labelStyle);
        table.add(ballLabel).width(100).height(45).left();

        screenLabel = new Label(BG_LABEL_TEXT, labelStyle);
        table.add(screenLabel).width(screenLabel.getWidth()).height(45).center();

        plainLabel = new Label(PLAIN_LABEL_TEXT,labelStyle);
        table.add(plainLabel).width(plainLabel.getWidth()).height(45).right();
    }

    private void addPlayButton() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameterButton = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterButton.size = 30;
        playButtonFont = generator.generateFont(parameterButton);
        playButtonStyle = new TextButton.TextButtonStyle();
        playButtonStyle.font = playButtonFont;
        playButton = new TextButton(PLAY_BTN_TEXT, playButtonStyle);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameController.getInstance().setGameState(GameController.State.MAP);
            }
        } );
        table.row();
        table.add(playButton).height(Gdx.graphics.getHeight()/2).expandX().bottom().left().maxHeight(parameterButton.size+10).padLeft(8);
    }

    private void AddColorsButtons() {
        ArrayList<Color> bColors = ConfigsModel.getInstance().getBallColors();
        ArrayList<Color> pColors = ConfigsModel.getInstance().getPlainColors();
        ArrayList<Color> sColors = ConfigsModel.getInstance().getBackgroundColors();

        ballButtons = new ArrayList<ImageButton>();
        plainsButtons = new ArrayList<ImageButton>();
        screenButtons = new ArrayList<ImageButton>();

        for (int i = 0; i < bColors.size(); ++i) {
            ballButtons.add(addBallColorButton(bColors.get(i)));
            screenButtons.add(addScreenColorButton(sColors.get(i)));
            plainsButtons.add(addPlainColorButton(pColors.get(i)));
            table.row();
        }
    }

    /**
     * Renders this ConfigsView.
     */
    public void render() {
        Gdx.input.setInputProcessor(stage);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    /**
     * Resizes this stage viewport accordingly to the given parameters.
     * @param width this stage new width
     * @param height this stage new height
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
    }

    /**
     * Disposes of this ConfigsView.
     */
    public void dispose() {
        spriteBatch.dispose();
        stage.dispose();
        generator.dispose();
    }


    private ImageButton addBallColorButton(final com.badlogic.gdx.graphics.Color color)
    {
        pix.setColor(color);
        pix.fill();
        ImageButton buttom = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(pix))));
        table.add(buttom).width(50).height(80).expandY().expandX().left();
        buttom.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               MapController.getInstance().setBallInitialColor(color);
                GameModel.getInstance().setBallColor(color);
            }
        } );

        return buttom;
    }

    private ImageButton addPlainColorButton(final com.badlogic.gdx.graphics.Color color)
    {
        pix.setColor(color);
        pix.fill();
        ImageButton buttom = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(pix))));
        table.add(buttom).width(50).height(80).expandY().expandX().right();
        buttom.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MapController.getInstance().setPlainsInitialColor(color);
               GameModel.getInstance().setPlainColor(color);
            }
        } );

        return buttom;
    }

    private ImageButton addScreenColorButton(final com.badlogic.gdx.graphics.Color color)
    {
        pix.setColor(color);
        pix.fill();
        ImageButton buttom = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(pix))));
        table.add(buttom).width(50).height(80).expandY().expandX().center();
        buttom.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                   MapController.getInstance().setScreenColor(color);
                GameModel.getInstance().setBackgroundColor(color);
            }
        } );

        return buttom;
    }
}
