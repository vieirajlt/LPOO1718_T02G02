package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.gdx.game.controller.MapController;
import com.gdx.game.view.MapView;

public class PlainJump extends ApplicationAdapter  {

	private MapController controller;

	private PerspectiveCamera camera;
	private CameraInputController cameraController;

	@Override
	public void create () {

		Bullet.init();
		controller = MapController.getInstance();

		controller.create();

		setCamera();

		cameraController = new CameraInputController(camera);
		Gdx.input.setInputProcessor(cameraController);
	}

	private void setCamera() {
		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(3f, 7f, 10f);
		camera.lookAt(0, 4f, 0);
		camera.far = 100f;
		camera.near = 1f;
		camera.update();
	}

	@Override
	public void render () {
		controller.render(camera);
	}

	@Override
	public void dispose () {
		controller.dispose();
	}

}
