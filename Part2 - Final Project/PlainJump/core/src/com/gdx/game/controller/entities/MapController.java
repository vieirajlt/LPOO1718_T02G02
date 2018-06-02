package com.gdx.game.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.gdx.game.model.GameModel;
import com.gdx.game.model.entities.BonusModel;
import com.gdx.game.model.entities.MapModel;
import com.gdx.game.model.entities.PlainModel;
import com.gdx.game.view.entities.BallView;
import com.gdx.game.view.entities.BonusView;
import com.gdx.game.view.entities.MapView;
import com.gdx.game.view.entities.PlainView;

import java.util.Random;

//TODO descricao da classe + 5 (+/-) todos
public class MapController  {

    /**
     * This used music
     */
    private static final String SOUND_PATH = "sounds/test48000.mp3";

    /**
     * this Singleton Instance
     */
    private static MapController instance = null;
    /**
     * This map model
     */
    private MapModel model;
    /**
     * This map view
     */
    private MapView view;
    /**
     * This MapController plains
     */
    private Array<PlainController> plains;
    /**
     * This MapController ball
     */
    private BallController ball;
    /**
     * This MapController bonus
     */
    private Array<BonusController> bonus;

    //TODO
    private int plainLevels;
    private int plainsPerLevel;
    private int positioningLevel;
    private float lastZUpdated;
    private float[] positionsX;

    public float getMinY() {
        return positionsY[0];
    }

    private float[] positionsY;

    /**
     * This collision configuration
     */
    private btCollisionConfiguration collisionConfig;
    /**
     * This dispatcher
     */
    private btDispatcher dispatcher;
    /**
     * This broadphase
     */
    private btBroadphaseInterface broadphase;
    /**
     * This dynamics world
     */
    private btDynamicsWorld world;
    /**
     * This constraint solver
     */
    private btConstraintSolver constraintSolver;

    /**
     * Distance in the z axis from the camera position to the this ball position
     */
    private float cameraBallDistance;

    /**
     * TODO
     */
    private float xLimit;

    private Vector3 gravity;

    /**
     * This contact listener
     */
    private ControllerContactListener contactListener;

    /**
     * This debug drawer
     */
    private DebugDrawer debugDrawer;

    /**
     * This start time
     */
    private long startTime = TimeUtils.nanoTime() ;

    /**
     * Represents the speed increment to this ball
     */
    private float speedIncrease = 0.05f;

    /**
     * TODO
     */
    private boolean moving = true;

    /**
     * This music
     */
    private Sound bgMusic;

    /**
     * This current music state
     */
    private MusicState musicState;

    /**
     * This music status flag
     */
    private boolean musicOnFlag;

    /**
     * This music possible states
     */
    public enum MusicState {
        STOP,
        PAUSE,
        PLAY;
    }

    class ControllerContactListener extends ContactListener {
        @Override
        public boolean onContactAdded (int userValue0, int partId0, int index0, int userValue1, int partId1, int index1) {
            boolean isBonus = false;
            if (userValue1 != 0)
            {
                for (BonusController bc : bonus) {
                    if (userValue1 == bc.getBody().getUserValue())
                    {
                        bc.setVisible(false);
                        model.setScoreMultiplier(((BonusModel)bc.getModel()).getValue());
                        model.setImmune(((BonusModel)bc.getModel()).isImmune());
                        isBonus = true;
                        ball.setCurrentColor(bc.getInitialColor());
                        break;
                    }
                }

                if (!isBonus) {
                    ball.setCurrentPlainIndex(userValue1);
                }
                ball.setJump(true);

            }
            return true;
        }

        @Override
        public void onContactEnded(int userValue0, int userValue1) {
            if (userValue1 != 0)
            {
                ball.setJump(false);
            }
        }
    }

    private MapController() {

        model = MapModel.getInstance();
        view = MapView.getInstance();
        contactListener = new ControllerContactListener();

        lastZUpdated = 0;

        positionsX = new float[]{-24, -20, -16, -12, -8, -4, 0, 4, 8, 12, 16, 20, 24};

        positionsY = new float[]{-4, 0, 4};

        gravity = new Vector3(0, -75f, 0);

        xLimit = 5;

        cameraBallDistance = 15;

        buildWorld();

        buildBall();

        buildPlains();

        buildBonus();

        bgMusic = Gdx.audio.newSound(Gdx.files.internal(SOUND_PATH));

        bgMusic.stop();

        musicState = MusicState.STOP;

        musicOnFlag = true;
    }

    private void addPlains() {

        plainLevels = 20;
        plainsPerLevel = 5;
        positioningLevel = plainLevels;

        plains.clear();
        for (int i = 0; i < plainLevels*plainsPerLevel; ++i) {
            plains.add(new PlainController());
        }

    }

    private void placePlains() {
        Random rand = new Random();
        int loc = 0;
        for(PlainController pc : plains) {

            int r1 = rand.nextInt(positionsX.length);

            int r2 = rand.nextInt(positionsY.length);

            float plainDepth = (((PlainModel) (pc.getModel())).getDepth());

            if (loc <= 1) {
                r1 = (positionsX.length-1)/2;
                r2 = (positionsY.length-1)/2;
            }
            pc.setPos(positionsX[r1], positionsY[r2], -loc * plainDepth);
            ++loc;
            if(loc%plainLevels == 0) {
                loc = 0;
            }
        }
    }

    private void placePlainsLevel() {

        Random rand = new Random();

        int lvl = positioningLevel%(plains.size/plainsPerLevel);
        for(int it = lvl; it < plains.size; it += plainLevels) {
            PlainController pc = plains.get(it);

            int r1 = rand.nextInt(positionsX.length);

            int r2 = rand.nextInt(positionsY.length);

            float plainDepth = (((PlainModel) (pc.getModel())).getDepth());

            pc.moveToPos(positionsX[r1], positionsY[r2], -plainLevels*plainDepth);
        }
        ++positioningLevel;
    }

    private void addPlainsToWorld()
    {
        for (PlainController pc : plains)
        {
            pc.getBody().proceedToTransform(pc.getView().getBodyInstance().transform);
            pc.getBody().setUserValue(view.getInstances().size);
            pc.getBody().setCollisionFlags( pc.getBody().getCollisionFlags() | btCollisionObject.CollisionFlags.CF_KINEMATIC_OBJECT);
            view.addInstance((PlainView) (pc.getView()));
            world.addRigidBody(pc.getBody());
            pc.getBody().setActivationState(Collision.DISABLE_DEACTIVATION);
        }
    }

    private void buildWorld()
    {
        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        world = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfig);

        world.setGravity(gravity);
    }


    private void buildPlains() {
        plains = new Array<PlainController>();

        addPlains();

        placePlains();

        addPlainsToWorld();
    }

    private void buildBall() {

        ball = BallController.getInstance();

        ball.getBody().proceedToTransform(ball.getView().getBodyInstance().transform);

        view.addInstance((BallView) (ball.getView()));

        ball.getBody().setUserValue(0);

        ball.getBody().setCollisionFlags(ball.getBody().getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);

        world.addRigidBody(ball.getBody());
    }

    private void buildBonus()
    {
        bonus = new Array<BonusController>();

        addBonus();

        placeBonus();

        addBonusToWorld();
    }


    private void addBonus()
    {
        BonusController b1 = new BonusController(BonusModel.BonusType.DOUBLE);
        BonusController b2 = new BonusController(BonusModel.BonusType.TRIPLE);
        BonusController b3 = new BonusController(BonusModel.BonusType.QUADRUPLE);
        BonusController b4 = new BonusController(BonusModel.BonusType.IMMUNITY);
        bonus.add(b1);
        bonus.add(b2);
        bonus.add(b3);
        bonus.add(b4);
    }


    private void placeBonus(){
        for (BonusController bc : bonus)
           bc.placeBonus(ball.getModel().getPosZ());
    }


   private void addBonusToWorld()
   {
       for (BonusController bc : bonus)
       {
           bc.getBody().proceedToTransform(bc.getView().getBodyInstance().transform);
           bc.getBody().setUserValue(view.getInstances().size);
           bc.getBody().setCollisionFlags( bc.getBody().getCollisionFlags() | btCollisionObject.CollisionFlags.CF_NO_CONTACT_RESPONSE);
           view.addInstance((BonusView) (bc.getView()));
           world.addRigidBody(bc.getBody());
           bc.getBody().setActivationState(Collision.DISABLE_DEACTIVATION);
       }
   }

    /**
     * Creates this MapController
     */
    public void create() {
        startTime = TimeUtils.nanoTime();
        debugDrawer = new DebugDrawer();
        world.setDebugDrawer(debugDrawer);
        debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
    }

    /**
     * Renders this MapController
     * @param camera perspective camera
     */
    public void render(PerspectiveCamera camera) {

        update(camera);
        view.render(camera, moving);

        //a cena do debug
    /* debugDrawer.begin(camera);
        world.debugDrawWorld();
        debugDrawer.end();*/

    }

    private void update(PerspectiveCamera camera) {
        if(!ball.isFalling()) {
            handleInputs();
        } else {
            view.setGameOverView();
        }

        if(!moving)
            return;

        final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());

        world.stepSimulation(delta, 5, 1f / 60f);

        updateBonus(camera);

        if (TimeUtils.timeSinceNanos(startTime) > 10000 && !ball.isFalling())
        {
            if (model.updateScore(1))
                ball.incLinearVelocity(speedIncrease);
            startTime = TimeUtils.nanoTime();
        }

        final float delay = 0.1f;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (model.incCounter(delay))
                    ball.setCurrentColor(ball.getInitialColor());
            }
        }, delay);

        updateBall();

        updatePlains(camera);

        camera.position.x = ball.getModel().getPosX();
        if(ball.getModel().getPosY() >= 0) {
            camera.position.y = ball.getModel().getPosY()+cameraBallDistance/3;
        } else {
            camera.position.y = cameraBallDistance/3;
        }
        camera.position.z = ball.getModel().getPosZ()+cameraBallDistance;
        camera.update();
    }

    private void handleInputs() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            jump();
        }
        if (Gdx.input.getGyroscopeX() > 0.2) {
            moveRight();
        }
        if (Gdx.input.getGyroscopeX() < -0.2) {
            moveLeft();
        }
        if (Gdx.input.isTouched()) {
            jump();
        }
    }

    private void updatePlains(PerspectiveCamera camera) {

        float plainDepth = (((PlainModel) (plains.get(0).getModel())).getDepth());

        if(camera.position.z <= -plainDepth + lastZUpdated) {
            lastZUpdated = camera.position.z;
            placePlainsLevel();
        }
    }

    private void updateBonus(PerspectiveCamera camera)
    {
        for (BonusController bc : bonus)
        {
            if (bc.isVisible() == false || bc.getBody().getCenterOfMassPosition().z > camera.position.z)
                bc.replaceBonus(ball.getModel().getPosZ());
            bc.getWorldTransform();
        }
    }

    private void updateBall()
    {
        ball.setFrontalLinearVelocity();
        ball.moveFront();

        if (model.isImmune() && ball.isFalling())
        {
            replaceBall();
            ball.updateModel();
            ball.setWorldTransform();
            ball.setIsFalling(false);
        }
    }

     /**
     * Disposes of this MapController.
     */
    public void dispose() {
        bgMusic.dispose();
        view.dispose();

        for(PlainController pc : plains) {
            pc.dispose();
        }

        ball.dispose();

        world.dispose();
        constraintSolver.dispose();
        broadphase.dispose();
        dispatcher.dispose();
        collisionConfig.dispose();

        contactListener.dispose();
    }

    /**
     * Resizes this MapController view.
     * @param width this stage new width
     * @param height this stage new height
     */
    public void resize(int width, int height) {
        view.resize(width,height);
    }

    /**
     * Makes this ball jump.
     */
    public void jump()
    {
        if(!moving)
            return;
        ball.jump();
    }

    /**
     * Makes this ball move to the left.
     */
    public void moveLeft()
    {
        if(!moving)
            return;
        ball.moveLeft();
    }

    /**
     * Makes this ball move to the right.
     */
    public void moveRight()
    {
        if(!moving)
            return;
        ball.moveRight();
    }


    private void replaceBall()
    {
        float x = plains.get(ball.getCurrentPlainIndex()-1).getBody().getCenterOfMassPosition().x;
        float z = plains.get(ball.getCurrentPlainIndex()-1).getBody().getCenterOfMassPosition().z;
        ball.getView().getBodyInstance().transform.setToTranslation(x,1,z);
        ball.setWorldTransform();
        ball.updateModel();
    }

    /**
     * Restarts this MapController
     */
    public void reset() {
        model.reset();
        view.reset();
        ball.reset();
        this.instance = null;
        bgMusic.stop();
    }

    /**
     * TODO
     * @param moving
     */
    public void setMoving(boolean moving) {
        this.moving = moving;

        if(moving && musicOnFlag) {
            bgMusic.play();
            musicState = MusicState.PLAY;
        } else {
            bgMusic.stop();
            musicState = MusicState.STOP;
        }
    }

    /**
     * Gets MapController Singleton instance.
     * @return this MapController
     */
    public static MapController getInstance() {
        if(instance == null)
            instance = new MapController();
        return instance;
    }

    /**
     * Retrieves the value of this MapController model score count.
     * @return this MapController model score count
     */
    public Integer getScore() {
        return model.getScoreCount();
    }

    /**
     * Sets this plains initial color.
     * @param color this model color new value
     */
    public void setPlainsInitialColor(Color color)
    {
        for(PlainController pc : plains)
            pc.setInitialColor(color);
    }

    /**
     * Sets this ball initial color.
     * @param color this model color new value
     */
    public void setBallInitialColor(Color color)
    {
         ball.setInitialColor(color);
    }

    /**
     * TODO
     */
    public void resumeBgMusic() {
        bgMusic.resume();
        musicState = MusicState.PLAY;
    }

    /**
     * Plays this MapController bgMusic
     */
    public void startBgMusic() {
        bgMusic.play();
        musicState = MusicState.PLAY;
    }

    /**
     * Pauses this MapController bgMusic.
     */
    public void pauseBgMusic() {
        bgMusic.pause();
        musicState = MusicState.PAUSE;
    }

    /**
     * Retrieves the value of this MapController musicState.
     * @return this MapControlle musicState
     */
    public MusicState getMusicState() {
        return musicState;
    }

    /**
     * Sets this MapController view's background rgb values to the ones from the preferred color
     * @param color to set this view's background to
     */
    public void setScreenColor(Color color)
    {
        view.setScreenColor(color);
    }

    /**
     * Set the value of this MapController musicOnFlag.
     * Set and save GameModel musicOnFlag value.
     * @param musicOnFlag the new value of musicOnFlag
     */
    public void setMusicOnFlag(boolean musicOnFlag) {
        this.musicOnFlag = musicOnFlag;
        GameModel.getInstance().setMusicOnFlag(musicOnFlag);
    }

}

