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
import com.gdx.game.model.entities.MapModel;
import com.gdx.game.model.entities.BonusModel;
import com.gdx.game.model.entities.PlainModel;
import com.gdx.game.view.entities.MapView;
import com.gdx.game.view.entities.BallView;
import com.gdx.game.view.entities.BonusView;
import com.gdx.game.view.entities.PlainView;

import java.util.Random;

public class MapController  {

    private static MapController instance = null;


    private MapModel model;
    private MapView view;

    private Array<PlainController> plains;
    private BallController ball;
    private Array<BonusController> bonus;

    private int plainLevels;
    private int plainsPerLevel;
    private int positioningLevel;
    private float lastZUpdated;
    private int[] positions;

    private btCollisionConfiguration collisionConfig;
    private btDispatcher dispatcher;
    private btBroadphaseInterface broadphase;
    private btDynamicsWorld world;
    private btConstraintSolver constraintSolver;

    private float cameraBallDistance;

    private float xLimit;

    private Vector3 gravity;

    private ControllerContactListener contactListener;

    private DebugDrawer debugDrawer;

    private long startTime = TimeUtils.nanoTime() ;

    private float speedIncrease = 0.05f;

    private boolean moving = true;

    private Sound bgMusic;

    private MusicState musicState;

    public enum MusicState {
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
                ball.setJump(false);  //para que so se possa saltar 1 vez de cada vez
            }
        }
    }

    private MapController() {

        model = MapModel.getInstance();
        view = MapView.getInstance();
        contactListener = new ControllerContactListener();

        lastZUpdated = 0;

        positions = new int[]{-12, -8, -4, 0, 4, 8, 12};

        gravity = new Vector3(0, -75f, 0);

        xLimit = 5;

        cameraBallDistance = 15;

        buildWorld();

        buildBall();

        buildPlains();

        buildBonus();

        bgMusic = Gdx.audio.newSound(Gdx.files.internal("test.mp3"));

        bgMusic.stop();

        musicState = MusicState.PAUSE;
    }

    private void addPlains() {

        plainLevels = 15;
        plainsPerLevel = 3;
        positioningLevel = plainLevels;

        plains.clear();
        for (int i = 0; i < plainLevels*plainsPerLevel; ++i) {
            plains.add(new PlainController());
        }

    }

    private void placePlains() {
        boolean spawnPlain = true;

        Random rand = new Random();
        int loc = 0;
        for(PlainController pc : plains) {

            int r = rand.nextInt(positions.length);

            if(spawnPlain)
                spawnPlain = false;
            else
                pc.setPos(positions[r], 0, -loc*(((PlainModel) (pc.getModel())).getDepth()));
                //pc.getView().moveModelInstance(positions[r], 0, -loc*(((PlainModel) (pc.getModel())).getDepth()));
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

            int r = rand.nextInt(positions.length);

            pc.moveToPos(positions[r], 0, -plainLevels*(((PlainModel) (pc.getModel())).getDepth()));
        }
        ++positioningLevel;
    }

    private void addPlainsToWorld()
    {
        for (PlainController pc : plains)
        {
            pc.getBody().proceedToTransform(pc.getView().getModelInstance().transform);
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

        ball.getBody().proceedToTransform(ball.getView().getModelInstance().transform);

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
           bc.getBody().proceedToTransform(bc.getView().getModelInstance().transform);
           bc.getBody().setUserValue(view.getInstances().size);
           bc.getBody().setCollisionFlags( bc.getBody().getCollisionFlags() | btCollisionObject.CollisionFlags.CF_NO_CONTACT_RESPONSE);
           view.addInstance((BonusView) (bc.getView()));
           world.addRigidBody(bc.getBody());
           bc.getBody().setActivationState(Collision.DISABLE_DEACTIVATION);
       }
   }


    public void create() {
        startTime = TimeUtils.nanoTime();
        debugDrawer = new DebugDrawer();
        world.setDebugDrawer(debugDrawer);
        debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
    }

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

        //faz update do score, se a bola tiver caido ele para
        if (TimeUtils.timeSinceNanos(startTime) > 10000 && !ball.isFalling())
        {
            if (model.updateScore(1))
                ball.incLinearVelocity(speedIncrease);
            startTime = TimeUtils.nanoTime();
            view.setScore(model.getScoreCount());
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
        ball.setLinearVelocity();
        ball.moveFront();

        if (model.isImmune() && ball.isFalling())
        {
            replaceBall();
            ball.updateModel();
            ball.setWorldTransform();
            ball.setIsFalling(false);
        }
    }

    public void dispose() {
        bgMusic.dispose();
        view.dispose();

        for(PlainController pc : plains) {
            pc.dispose();
        }

        world.dispose();
        constraintSolver.dispose();
        broadphase.dispose();
        dispatcher.dispose();
        collisionConfig.dispose();

        contactListener.dispose();
    }

    public void jump()
    {
        if(!moving)
            return;
        ball.jump();
    }

    public void moveLeft()
    {
        if(!moving)
            return;
        ball.moveLeft();
    }

    public void moveRight()
    {
        if(!moving)
            return;
        ball.moveRight();
    }

    //nao sei se o model dos planos esta a ser atualizado
    private void replaceBall()
    {
        //mudar isto para chamar o model em vez do body
        float x = plains.get(ball.getCurrentPlainIndex()-1).getBody().getCenterOfMassPosition().x;
        float z = plains.get(ball.getCurrentPlainIndex()-1).getBody().getCenterOfMassPosition().z;
        ball.getView().getModelInstance().transform.setToTranslation(x,1,z);
        ball.setWorldTransform();
        ball.updateModel();
    }

    public void reset() {
        model.reset();
        view.reset();
        ball.reset();
        this.instance = null;
        bgMusic.stop();
    }

    public void setMoving(boolean moving) {
        this.moving = moving;

        if(moving) {
            bgMusic.play();
            musicState = MusicState.PLAY;
        } else {
            bgMusic.stop();
            musicState = MusicState.PAUSE;
        }
    }

    public static MapController getInstance() {
        if(instance == null)
            instance = new MapController();
        return instance;
    }

    public Integer getScore() {
        return view.getScore();
    }

    public void setPlainsInitialColor(Color color)
    {
        for(PlainController pc : plains)
            pc.setInitialColor(color);
    }

    public void setBallInitialColor(Color color)
    {
         ball.setInitialColor(color);
    }

    public void resumeBgMusic() {
        bgMusic.resume();
        musicState = MusicState.PLAY;
    }

    public void pauseBgMusic() {
        bgMusic.pause();
        musicState = MusicState.PAUSE;
    }

    public MusicState getMusicState() {
        return musicState;
    }

    public void setScreenColor(float r, float g, float b)
    {
        view.setScreenColor(r,g,b);
    }

}

