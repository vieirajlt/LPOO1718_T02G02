package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class LpooGame extends ApplicationAdapter implements InputProcessor{

	private PerspectiveCamera camera;
	//private CameraInputController camController;
	private ModelBatch batch;
	private Model model;
	private ModelInstance sphere;
    private ModelInstance floor;
    private Array<ModelInstance> instances;
	private Environment environment;

//	public CameraInputController camController;

	@Override
	public void create () {
		batch = new ModelBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1000f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(0f, 7f, 10f);
		camera.lookAt(0f, 4f, 0f);
		camera.near = 1f;
		camera.far = 300f;
		camera.update();

//		camController = new CameraInputController(camera);
//		Gdx.input.setInputProcessor(camController);

		ModelBuilder builder = new ModelBuilder();
		builder.begin();
		builder.node().id = "sphere";
		builder.part("sphere", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal, new Material(ColorAttribute.createDiffuse(Color.GREEN)))
				.sphere(1f, 1f, 1f, 10, 10);
		builder.node().id = "floor";
		builder.part("box", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal, new Material(ColorAttribute.createDiffuse(Color.FIREBRICK)))
				.box(10f, 1f, 50f);
		model = builder.end();

		floor = new ModelInstance(model, "floor");
		sphere = new ModelInstance(model, "sphere");
		sphere.transform.setToTranslation(0, 1f, 0);
		floor.transform.setToTranslation(0, 0f, -24);

		instances = new Array<ModelInstance>();
		instances.add(floor);
		instances.add(sphere);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.update();
//		camController.update();
		batch.begin(camera);
		batch.render(instances, environment);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		model.dispose();
	}


	@Override
	public boolean keyDown(int keycode) {
		Vector3 vertex;
		if(keycode == Input.Keys.LEFT) {
			vertex = new Vector3(0f, 0f, 1f);
			sphere.transform.rotate(vertex, 10f);
			floor.transform.translate(1, 0, 0);
		}
		if(keycode == Input.Keys.RIGHT) {
			vertex = new Vector3(0f, 0f, 1f);
			sphere.transform.rotate(vertex, -10f);
			floor.transform.translate(-1, 0, 0);
		}
		if(keycode == Input.Keys.UP) {
			vertex = new Vector3(1f, 0f, 0f);
			sphere.transform.rotate(vertex, -10f);
			floor.transform.translate(0, 0, 1);
		}
		if(keycode == Input.Keys.DOWN) {
			vertex = new Vector3(1f, 0f, 0f);
			sphere.transform.rotate(vertex, 10f);
			floor.transform.translate(0, 0, -1);
		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
