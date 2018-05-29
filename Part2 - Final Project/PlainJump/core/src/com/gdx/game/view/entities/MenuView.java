package com.gdx.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.gdx.game.controller.GameController;

public class MenuView {

    private TextButton playButton;
    private TextButton.TextButtonStyle playButtonStyle;

    private TextButton configsButton;
    private TextButton.TextButtonStyle configsButtonStyle;

    BitmapFont buttonFont;

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

        table.debug();
    }

    public void render() {
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
        stage.dispose();
        generator.dispose();
    }


}
