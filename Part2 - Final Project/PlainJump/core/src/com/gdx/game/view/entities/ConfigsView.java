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
import com.gdx.game.controller.entities.BallController;
import com.gdx.game.controller.entities.MapController;
import com.gdx.game.model.GameModel;
import com.gdx.game.model.entities.MenuModel;


public class ConfigsView {


    private SpriteBatch spriteBatch;
    private Table table;
    private Stage stage;

    private ImageButton orangeButton;
    private ImageButton cyanButton;
    private ImageButton purpleButton;
    private ImageButton forestButton;


    private ImageButton violetButton;
    private ImageButton navyButton;
    private ImageButton royalButton;
    private ImageButton limeButton;


    private ImageButton grayButton;
    private ImageButton blackButton;
    private ImageButton skyButton;
    private ImageButton fireButton;


    private TextButton exitButton;
    private TextButton.TextButtonStyle exitButtonStyle;
    BitmapFont exitButtonFont;

    private FreeTypeFontGenerator generator;

    Pixmap pix;

    private Label.LabelStyle labelStyle;
    BitmapFont labelFont;
    private Label ballLabel;
    private Label plainLabel;
    private Label screenLabel;


    public ConfigsView() {

        spriteBatch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(table);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));


        FreeTypeFontGenerator.FreeTypeFontParameter parameterLabel = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterLabel.size = 50;
        labelFont = generator.generateFont(parameterLabel);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = labelFont;
        ballLabel = new Label("Ball",labelStyle);
        table.add(ballLabel).width(100).height(45).left();
        //table.row();


        screenLabel = new Label("BackGround", labelStyle);
        table.add(screenLabel).width(screenLabel.getWidth()).height(45).center();

        plainLabel = new Label("Plain",labelStyle);
        table.add(plainLabel).width(plainLabel.getWidth()).height(45).right();

        table.row();


        pix = new Pixmap(30,30, Pixmap.Format.RGBA8888);


        addBallColorButton(com.badlogic.gdx.graphics.Color.ORANGE,orangeButton);
        addScreenColorButton(Color.BLACK, blackButton);
        addPlainColorButton(com.badlogic.gdx.graphics.Color.VIOLET,violetButton);
        table.row();

        addBallColorButton(com.badlogic.gdx.graphics.Color.CYAN,cyanButton);
        addScreenColorButton(Color.GRAY, grayButton);
        addPlainColorButton(com.badlogic.gdx.graphics.Color.NAVY,navyButton);
        table.row();

        addBallColorButton(com.badlogic.gdx.graphics.Color.PURPLE,purpleButton);
        addScreenColorButton(Color.SKY, skyButton);
        addPlainColorButton(com.badlogic.gdx.graphics.Color.ROYAL,royalButton);
        table.row();

        addBallColorButton(com.badlogic.gdx.graphics.Color.FOREST,forestButton);
        addScreenColorButton(Color.FIREBRICK, fireButton);
        addPlainColorButton(com.badlogic.gdx.graphics.Color.LIME,limeButton);
        table.row();




        //exit button
        FreeTypeFontGenerator.FreeTypeFontParameter parameterButton = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameterButton.size = 30;
        exitButtonFont = generator.generateFont(parameterButton);
        exitButtonStyle = new TextButton.TextButtonStyle();
        exitButtonStyle.font = exitButtonFont;
        exitButton = new TextButton("PLAY", exitButtonStyle);
        exitButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MAP Over");
                GameController.getInstance().setGameState(GameController.State.MAP);
            }
        } );
        table.row();
        table.add(exitButton).height(Gdx.graphics.getHeight()/2).expandX().bottom().left().maxHeight(parameterButton.size+10).padLeft(8);

      // table.debug();

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


    private void addBallColorButton(final com.badlogic.gdx.graphics.Color color, ImageButton buttom)
    {
        pix.setColor(color);
        pix.fill();
        buttom = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(pix))));
        table.add(buttom).width(50).height(80).expandY().expandX().left();
        //table.add(buttom);
        buttom.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               MapController.getInstance().setBallInitialColor(color);
                GameModel.getInstance().setBallColor(color);
            }
        } );
    }

    private void addPlainColorButton(final com.badlogic.gdx.graphics.Color color, ImageButton buttom)
    {
        pix.setColor(color);
        pix.fill();
        buttom = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(pix))));
        table.add(buttom).width(50).height(80).expandY().expandX().right();
        //table.add(buttom);
        buttom.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MapController.getInstance().setPlainsInitialColor(color);
               GameModel.getInstance().setPlainColor(color);
            }
        } );
    }

    private void addScreenColorButton(final com.badlogic.gdx.graphics.Color color, ImageButton buttom)
    {
        pix.setColor(color);
        pix.fill();
        buttom = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(pix))));
        table.add(buttom).width(50).height(80).expandY().expandX().center();
        //table.add(buttom);
        buttom.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                   MapController.getInstance().setScreenColor(color);
                GameModel.getInstance().setBackgroundColor(color);
            }
        } );
    }


}
