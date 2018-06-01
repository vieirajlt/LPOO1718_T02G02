package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.gdx.game.controller.GameController;

public class PlainJump extends ApplicationAdapter {


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
