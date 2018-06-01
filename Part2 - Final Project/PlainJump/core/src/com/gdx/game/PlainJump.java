package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.gdx.game.controller.GameController;

/**
 * Base game class for general control.
 */
public class PlainJump extends ApplicationAdapter {

	/**
	 * this game controller for game management
	 */
	private GameController controller;

	@Override
	public void create () {
		Bullet.init();
		controller = GameController.getInstance();
		controller.create();
	}

	@Override
	public void render () {
		controller.render();
	}

	@Override
	public void dispose () {
		controller.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		controller.resize(width,height);
	}
}
