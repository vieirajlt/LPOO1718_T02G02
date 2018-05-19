package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.controller.MapController;

public class BodyInstance extends ModelInstance implements Disposable {

	private final btRigidBody rigidBody;
	private btRigidBody.btRigidBodyConstructionInfo info;
	private static Vector3 inertia = new Vector3();
	public final btCollisionShape shape;
	public boolean moving ;

	public BodyInstance(Model model, String node, btCollisionShape shape, float mass) {
		super(model,node);
		this.shape = shape;
		if (mass > 0)
			shape.calculateLocalInertia(mass, inertia);
		else
			inertia.set(0, 0, 0);
		info = new btRigidBody.btRigidBodyConstructionInfo(mass, null, shape, inertia);

		rigidBody = new btRigidBody(info);
	}

	public btRigidBody getRigidBody() {
		return rigidBody;
	}

	@Override
	public void dispose() {
		rigidBody.dispose();
		shape.dispose();
		info.dispose();
	}
}