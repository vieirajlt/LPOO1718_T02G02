package com.pj.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * A view representing a bullet
 */
public class BulletView extends EntityView{
    /**
     * Constructs a bullet view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BulletView(com.pj.game.PlainJump game) {
        super(game);
    }

    /**
     * Creates a sprite representing this bullet.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this bullet
     */
    public Sprite createSprite(com.pj.game.PlainJump game) {
        Texture texture = game.getAssetManager().get("bullet.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
