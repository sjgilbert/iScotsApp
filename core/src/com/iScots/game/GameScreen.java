package com.iScots.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Sam on 3/14/2015.
 * This is the screen where the game actually runs.  It handles the time, displays the pet
 * lets the user perform actions, etc.  From this screen the user is capable of calling the
 * settingsScreen in the upper right corner.
 */
public class GameScreen extends ScreenAdapter {
    private IScotGame game;
    private OrthographicCamera guiCam;
    private Vector3 touchPoint;         //Used for touch handling.
    private Pet gamePet;    //The pet for the game.

    //The textures used to draw the status bars.
    private Texture greenBar;
    private Texture redBar;
    private Texture blackBar;

    //Temporary placeholder for the settings button.
    private Texture settingsButton;

    //These define the bounds of the touchboxes for the actions.
    private Rectangle playBounds;
    private Rectangle eatBounds;
    private Rectangle sleepBounds;
    private Rectangle settingsBounds;

    private Texture happinessLabel;
    private Texture hungerLabel;
    private Texture fatigueLabel;

    //The variables needed for cooldowns
    private static final double PLAY_TIME = 1;
    private static final double EAT_TIME = 2;
    private static final double SLEEP_TIME = 3;

    private long timePlayed;
    private long timeAte;
    private long timeSlept;

    private boolean playOnCooldown = false;
    private boolean eatOnCooldown = false;
    private boolean sleepOnCooldown = false;

    private boolean playButton =  false;  //These booleans indicate if the button is currently being pressed.
    private boolean eatButton = false;
    private boolean sleepButton = false;

    private double lastButtonTime; //The last time a button (any stat button) was pressed.

    //TODO: Christopher please comment!
    private long currentTime; //Value taken directly from
    private long lastTime;  //Last time the stats were updated.

    /**
     * Initializes the screen.
     *
     * @param game The game that the Screen belongs to.  Necessary for referencing the SpriteBatch.
     */
    public GameScreen(IScotGame game) {
        this.game = game;

        guiCam = new OrthographicCamera(300, 900);   //TODO: Christopher please comment!
        touchPoint = new Vector3(); //Supplies the input for the update method.

        gamePet = new Pet();    //Creates and stores a pet for the game.

        greenBar = Assets.greenBar;     //These are instantiated for the status bars in the draw method.
        redBar = Assets.redBar;
        blackBar = Assets.blackBar;

        settingsButton = Assets.settingsButton;     //Upper right

        playBounds = new Rectangle(-150, -450, 75, 150);    //lower left
        eatBounds = new Rectangle(-38, -450, 75, 150);      //lower middle
        sleepBounds = new Rectangle(75, -450, 75, 150);     //lower right
        settingsBounds = new Rectangle(120, 420, 30, 30);       //upper right
        currentTime = System.currentTimeMillis() / 1000;
        lastButtonTime = System.currentTimeMillis() / 1000;
        try {        //Pulls the last time from the local file if it is there.
            FileHandle filehandle = Gdx.files.local(".IScotGame");
            String[] strings = filehandle.readString().split("\n");  //"strings" contains four objects.  They are used below:
            lastTime = Long.valueOf(strings[0]);  //Sets previous time.
            gamePet.setHappiness(Float.parseFloat(strings[1])); //Sets previous happiness.
            gamePet.setHunger(Float.parseFloat(strings[2])); //Sets previous tiredness.
            gamePet.setTiredness(Float.parseFloat(strings[3]));
            System.out.println(strings[1]);
        } catch (Throwable e) {
        }
        happinessLabel = new Texture("happiness.png");
        hungerLabel = new Texture("hunger.png");
        fatigueLabel = new Texture("fatigue.png");
    }

    /**
     * The update method used for handling all user input.  The first conditional triggers
     * only when there is a touch, then determines whether an action has been selected.
     * If one has, the update method is called for the pet, with the appropriate action
     * passed as a parameter.
     */
    public void update() {
        if (Gdx.input.justTouched()) {
            playButton = false;   //Setting them false here means that they get updated the next time the screen is touched, which lets players see what they're doing..
            eatButton = false;
            sleepButton = false;
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                playButton = true; //Note that the button is pressed.
                lastButtonTime = System.currentTimeMillis() / 1000;  //Reset last button time to now.
                if (!playOnCooldown) {
                    System.out.println("play"); //These println are here for testing.
                    gamePet.update("play");
                    playOnCooldown = !playOnCooldown;   //puts the action on cooldown
                    timePlayed = currentTime;   //marks the start time for the cooldown
                    return;
                } else {
                    if (currentTime - timePlayed < PLAY_TIME) {  //for when the cooldown isn't over
                        System.out.println("play on cooldown");
                    } else {
                        playOnCooldown = !playOnCooldown;   //takes the action off cooldown
                    }
                }
            } else if (eatBounds.contains(touchPoint.x, touchPoint.y)) {
                eatButton = true;
                lastButtonTime = System.currentTimeMillis() / 1000;
                if (!eatOnCooldown) {
                    System.out.println("feed");
                    gamePet.update("feed");
                    eatOnCooldown = !eatOnCooldown;
                    timeAte = currentTime;
                    return;
                } else {
                    if (currentTime - timeAte < EAT_TIME) {
                        System.out.println("eat on cooldown");
                    } else {
                        eatOnCooldown = !eatOnCooldown;
                    }
                }
            } else if (sleepBounds.contains(touchPoint.x, touchPoint.y)) {
                sleepButton = true;
                lastButtonTime = System.currentTimeMillis() / 1000;
                if (!sleepOnCooldown) {
                    System.out.println("sleep");
                    gamePet.update("sleep");
                    sleepOnCooldown = !sleepOnCooldown;
                    timeSlept = currentTime;
                    return;
                } else {
                    if (currentTime - timeSlept < SLEEP_TIME) {
                        System.out.println("sleep on cooldown");
                    } else {
                        sleepOnCooldown = !sleepOnCooldown;
                    }
                }
            } else if (settingsBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("settings");
                game.setScreen(game.getSettingsScreen());       //calls the game's set screen method to set the screen to the settingsScreen.
                return;
            } else {
                System.out.println(touchPoint);     //For testing.
            }
        }
        if (currentTime - lastButtonTime > 5){  //Makes the button shrink back down after 5 seconds.
            playButton = false;
            eatButton = false;
            sleepButton = false;
        }
    }

    /**
     * The method used to display the graphics on the screen.  Unsure what the first 4 lines are doing,
     * but the rest is displaying the images.
     */
    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.getBatch().setProjectionMatrix(guiCam.combined);
        game.getBatch().enableBlending();
        game.getBatch().begin();

        //The backGround
        game.getBatch().draw(Assets.gameScreen, -150, -450, 300, 900); //Last two must always be the same as camera size for gameScreen.  (So coordinates align.)

        //The left half of the status bars.
        game.getBatch().draw(redBar, -120, 350, 120, 50);
        game.getBatch().draw(redBar, -120, 250, 120, 50);
        game.getBatch().draw(redBar, -120, 150, 120, 50);

        //The right half of the status bars.
        game.getBatch().draw(greenBar, 0, 350, 120, 50);
        game.getBatch().draw(greenBar, 0, 250, 120, 50);
        game.getBatch().draw(greenBar, 0, 150, 120, 50);

        //The sliding black indicator on the status bars.  Updates based on the attributes.
        game.getBatch().draw(blackBar, 2.4f * gamePet.getHappiness() - 120, 350, 1, 50);
        game.getBatch().draw(blackBar, 2.4f * gamePet.getHunger() - 120, 250, 1, 50);
        game.getBatch().draw(blackBar, 2.4f * gamePet.getTiredness() - 120, 150, 1, 50);

        //The labels for the status bar
        game.getBatch().draw(happinessLabel, -120, 350, 25, 50);
        game.getBatch().draw(hungerLabel, -120, 250, 25, 50);
        game.getBatch().draw(fatigueLabel, -120, 150, 25, 50);

        //The settings button in the upper right corner.
        game.getBatch().draw(settingsButton, 120, 420, 30, 30);

        //The pet in the center of the screen.
        game.getBatch().draw(gamePet.getPetImage(), -100, -400, 200, 800);

        //This if is to trigger the message that pops up on death.  Currently the else is the tail animation.
        //TODO: We need to work out what happens on death. The tail should stop moving, but this way it removes the tail on death
        if(!gamePet.isAlive()) {
            game.getBatch().draw(blackBar, 0, 0, 100, 100);
        }
        //TODO: Please comment!
        else {
            Tail tail = new Tail(100, 100);
            tail.update((game.currentTailTime - game.lastTailTime) * 5 * gamePet.getHappiness()/100); //I'm not sure of the difference between game.currentTime and currentTime, but only game.currentTime works
            TextureRegion keyFrame = Assets.tailAnim.getKeyFrame(tail.stateTime, Animation.ANIMATION_LOOPING);
            game.getBatch().draw(keyFrame, 18, -115, 22, 105);  //Position of the tail
        }

        Blink blink = new Blink(100, 100);
        blink.update(game.currentTailTime - game.lastTailTime + 12);
        TextureRegion keyFrame1 = Assets.blinkAnim.getKeyFrame(blink.stateTime, Animation.ANIMATION_LOOPING);
        game.getBatch().draw(keyFrame1, -25, 20, 10, 30);
        game.getBatch().draw(keyFrame1, -8, 25, 10, 30);


        //The buttons for actions.  Drawn from left to right.
        if (!playButton) {
            game.getBatch().draw(Assets.ball, -150, -450, 75, 150);
        }
        else if (playButton){  //Draw it larger.
            game.getBatch().draw(Assets.ball, -150, -450, 100, 300);
        }
        if (!eatButton) {
            game.getBatch().draw(Assets.bone, -38, -450, 75, 150);
        }
        else if (eatButton){
            game.getBatch().draw(Assets.bone, -50, -450, 100, 300);
        }
        if (!sleepButton){
            game.getBatch().draw(Assets.bed, 75, -450, 75, 150);
        }
        else if (sleepButton){
            game.getBatch().draw(Assets.bed, 50, -450, 100, 300);
        }


        game.getBatch().end();

    }

    public Pet getGamePet() {
        return gamePet;
    }

    /**
     * //TODO: Christopher please comment!
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        game.currentTailTime = System.currentTimeMillis()/1000;   //need game.currentTime and game.lastTime for tail animation
        if (game.currentTailTime - game.lastTailTime > 1 && gamePet.isAlive()){  //Provide time in seconds.
            game.lastTailTime = game.currentTailTime;
        }
        currentTime = System.currentTimeMillis()/1000;   //Provide time in seconds.
        if(currentTime - lastTime > 1) {
//            while(currentTime - lastTime > 1) { //The loop that decays based on how much time has passed
//                gamePet.update("decay");
//                lastTime += 1;
//            }
            lastTime = currentTime;
            //Writes current time to external file to be pulled on next restart.  Can be updated to include other stats.
            FileHandle filehandle = Gdx.files.local(".IScotGame");
            filehandle.writeString(Double.toString(lastTime) + "\n", false); //"False" means that this overwrites previous local file in that location.
            filehandle.writeString(Float.toString(gamePet.getHappiness()) + "\n", true);  //"True" means that this is appended to local file.
            filehandle.writeString(Float.toString(gamePet.getHunger()) + "\n", true);
            filehandle.writeString(Float.toString(gamePet.getTiredness()) + "\n", true);
//            for(int i=0; i<3600; i++) {       //This loop is to simulate an hour every second for testing.
                gamePet.update("decay");
            }
//        }
        update();
        draw();
    }
}
