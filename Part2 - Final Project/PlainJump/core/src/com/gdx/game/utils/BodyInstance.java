package com.gdx.game.utils;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.utils.Disposable;

/**
 * This class extends ModelInstance to allow the creation of objects that can be added to a {@link btDynamicsWorld}
 * allows the use of the physics motor
 */
public class BodyInstance extends ModelInstance implements Disposable {

	/**
	 * This BodyInstance rigidBody.
	 * allows the use of physics in the game
	 */
	private final btRigidBody rigidBody;

	/**
	 * Contains all the information necessary to create this BodyInstance rigidBody
	 */
	private btRigidBody.btRigidBodyConstructionInfo info;

	/**
	 * Represents this BodyInstance rigidBody inertia
	 */
	private static Vector3 inertia = new Vector3();

	/**
	 * Represents this BodyInstance rigidBody shape
	 */
	public final btCollisionShape shape;

	/**
	 * Creates a new BodyInstance with the preferred values.
	 * @param model necessary to create a ModelInstance
	 * @param node necessary to create a ModelInstance
	 * @param shape the new value of shape
	 * @param mass this BodyInstance rigidBody mass value
	 */
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

	/**
	 * Retrieve the value of this BodyInstance rigidBody.
	 * @return this BoduInstance rigidBody
	 */
	public btRigidBody getRigidBody() {
		return rigidBody;
	}

	/**
	 * Disposes of this BodyInstance.
	 */
	@Override
	public void dispose() {
		rigidBody.dispose();
		shape.dispose();
		info.dispose();
	}
}