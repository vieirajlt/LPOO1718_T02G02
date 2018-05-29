package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.gdx.game.controller.GameController;
import com.gdx.game.controller.entities.MapController;

public class PlainJump extends ApplicationAdapter {


	private GameController controller;


	@Override
	public void create () {

		Bullet.init();
		controller = GameController.getInstance();
		controller.create();

		//cameraController = new CameraInputController(camera);
		//Gdx.input.setInputProcessor(cameraController);

	}

	@Override
	public void render () {
		controller.render();
	}

	@Override
	public void dispose () {
		controller.dispose();
	}
}
