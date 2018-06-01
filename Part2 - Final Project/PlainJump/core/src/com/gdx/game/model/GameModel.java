package com.gdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

/**
 * This class is responsible for loading and saving the ball, plains and background colours as well as the high score
 */
public class GameModel {

    /**
     * Contains the path to the file where the high score data is stored
     */
    private static final String HS_DATA_FILE = "data/pj-v1.json";

    /**
     * The singleton instance of this GameModel
     */
    private static GameModel instance = null;

    /**
     * The previous highest score achieved
     */
    private Integer highscore;

    /**
     * The previous ball color
     */
    private Color ballColor;

    /**
     * The previous plain color
     */
    private Color plainColor;

    /**
     * The previous background color
     */
    private Color backgroundColor;

    /**
     * This music status flag
     */
    private boolean musicOnFlag;

    private GameModel() {
        highscore = 0;
        ballColor = Color.ORANGE;
        plainColor = Color.VIOLET;
        backgroundColor = Color.GRAY;
        musicOnFlag = true;
    }

    /*******************GET FUNCTIONS*******************/

    /**
     * Retrieves a singleton instance of a GameModel.
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if(instance == null)
            instance = new GameModel();
        return instance;
    }

    /**
     * Retrieves the value of this GameModel highscore.
     * @return this GameModel highscore
     */
    public int getHighscore() {
        return highscore;
    }

    /**
     * Retrieves the value of this GameModel ballColor.
     * @return this GameModel ballColor
     */
    public Color getBallColor() {
        return ballColor;
    }

    /**
     * Retrieves the value of this GameModel plainColor.
     * @return this ConfigsModel plainColor
     */
    public Color getPlainColor() {
        return plainColor;
    }

    /**
     * Retrieves the value of this GameModel backgroundColor.
     * @return this GameModel backgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Retrieves the value of this GameModel musicOnFlag.
     * @return this GameModel musicOnFlag
     */
    public boolean isMusicOnFlag() {
        return musicOnFlag;
    }

    /*******************SET FUNCTIONS*******************/

    /**
     * Set and save this GameModel backgroundColor value.
     * @param backgroundColor the new value of backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        saveSettings();
    }

    /**
     * Set and save this GameModel highscore value.
     * @param highscore the new value of highscore
     */
    public void setHighscore(int highscore) {
        this.highscore = highscore;
        saveSettings();
    }

    /**
     * Set and save this GameModel ballColor value.
     * @param ballColor the new value of ballColor
     */
    public void setBallColor(Color ballColor) {
        this.ballColor = ballColor;
        saveSettings();
        System.out.println("\tBall set");
    }

    /**
     * Set and save this GameModel plainColor value.
     * @param plainColor the new value of plainColor
     */
    public void setPlainColor(Color plainColor) {
        this.plainColor = plainColor;
        saveSettings();
        System.out.println("\tPlain set");
    }

    /**
     * Set and save this GameModel musicOnFlag value.
     * @param musicOnFlag the new value of musicOnFlag
     */
    public void setMusicOnFlag(boolean musicOnFlag) {
        this.musicOnFlag = musicOnFlag;
        saveSettings();
        System.out.printf("MusicFlag set to %s\n", musicOnFlag ? "true" : "false");
    }

    /**
     * Save this class to a json file, encoded for game preferences saving.
     */
    public void saveSettings() {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        FileHandle file = Gdx.files.local(HS_DATA_FILE);
        System.out.println(json.toJson(this));
        file.writeString(Base64Coder.encodeString(json.toJson(this)), false);
    }

    /**
     * Reads json file with this class, decoding the info and setting this class
     * accordingly in order to load game preferences.
     */
    public void loadSettings() {
        Json json = new Json();
        FileHandle file = Gdx.files.local(HS_DATA_FILE);
        GameModel tmp;
        try {
            tmp = json.fromJson(GameModel.class, Base64Coder.decodeString(file.readString()));
            if(tmp != null) {
                highscore = tmp.getHighscore();
                ballColor = tmp.getBallColor();
                plainColor = tmp.getPlainColor();
                backgroundColor = tmp.getBackgroundColor();
                musicOnFlag = tmp.isMusicOnFlag();
            }
        } catch (GdxRuntimeException rt) {
        }
    }

}
