package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.gdx.game.controller.entities.MapController;

public class PlainJump extends ApplicationAdapter {


	private MapController controller;
	private PerspectiveCamera camera;
	private CameraInputController cameraController;


	@Override
	public void create () {

		Bullet.init();
		controller = MapController.getInstance();
		controller.create();


		setCamera();

		//cameraController = new CameraInputController(camera);
		//Gdx.input.setInputProcessor(cameraController);

	}

	private void setCamera() {
		camera = new PerspectiveCamera(80, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0f, 7f, 10f);
		camera.lookAt(0, 4f, 0);
		camera.far = 100f;
		camera.near = 1f;
		camera.update();
	}

	@Override
	public void render () {

		handleInputs();
		controller.render(camera);

	}

	private void handleInputs() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			controller.moveLeft();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			controller.moveRight();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			controller.jump();
		}
		if (Gdx.input.getGyroscopeX() > 0.2) {
			controller.moveRight();
		}
		if (Gdx.input.getGyroscopeX() < -0.2) {
			controller.moveLeft();
		}
		if (Gdx.input.isTouched()) {
			controller.jump();
		}
	}


	@Override
	public void dispose () {
		controller.dispose();
	}
}
