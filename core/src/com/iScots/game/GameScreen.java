package com.iScots.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sam on 3/14/2015.
 * This is the screen where the game actually runs.  It handles the time, displays the pet
 * lets the user perform actions, etc.  From this screen the user is capable of calling the
 * settingsScreen in the upper right corner.
 */
public class GameScreen extends ScreenAdapter {
    IScotGame game;
    OrthographicCamera guiCam;
    Vector3 touchPoint;         //Used for touch handling.
    Pet gamePet;    //The pet for the game.

    //The textures used to draw the status bars.
    Texture greenBar;
    Texture redBar;
    Texture blackBar;

    //Temporary placeholder for the settings button.
    Texture settingsButton;

    //These define the bounds of the touchboxes for the actions.
    Rectangle playBounds;
    Rectangle eatBounds;
    Rectangle sleepBounds;
    Rectangle settingsBounds;

    Texture happinessLabel;
    Texture hungerLabel;
    Texture fatigueLabel;

    /**
     * Initializes the screen.
     * @param game The game that the Screen belongs to.  Necessary for referencing the SpriteBatch.
     */
    public GameScreen(IScotGame game) {
        this.game = game;

        guiCam = new OrthographicCamera(300,900);   //TODO: Christopher please comment!
        touchPoint = new Vector3(); //Supplies the input for the update method.

        gamePet = new Pet();    //Creates and stores a pet for the game.

        greenBar = Assets.greenBar;     //These are instantiated for the status bars in the draw method.
        redBar = Assets.redBar;
        blackBar = Assets.blackBar;

        settingsButton = Assets.redBar;     //Upper left

        playBounds = new Rectangle(-150, -450, 75, 150);    //lower left
        eatBounds = new Rectangle(-38, -450, 75, 150);      //lower middle
        sleepBounds = new Rectangle(75, -450, 75, 150);     //lower right
        settingsBounds = new Rectangle(120, 420, 30, 30);       //upper right

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
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("play"); //These println are here for testing.
                gamePet.update("play");
                return;
            }
            else if (eatBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("feed");
                gamePet.update("feed");
                return;
            }
            else if (sleepBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("sleep");
                gamePet.update("sleep");
                return;
            }
            else if (settingsBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("settings");
                game.setScreen(game.getSettingsScreen());       //calls the game's set screen method to set the screen to the settingsScreen.
                return;
            }
            else {
                System.out.println(touchPoint);     //For testing.
            }
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

        //The buttons for actions.  Drawn from left to right.
        game.getBatch().draw(Assets.ball, -150, -450, 75, 150);
        game.getBatch().draw(Assets.bone, -38, -450, 75, 150);
        game.getBatch().draw(Assets.bed, 75, -450, 75, 150);

        //The left half of the status bars.
        game.getBatch().draw(redBar, -120, 350, 120, 50);
        game.getBatch().draw(redBar, -120, 250, 120, 50);
        game.getBatch().draw(redBar, -120, 150, 120, 50);

        //The right half of the status bars.
        game.getBatch().draw(greenBar, 0, 350, 120, 50);
        game.getBatch().draw(greenBar, 0, 250, 120, 50);
        game.getBatch().draw(greenBar, 0, 150, 120, 50);

        //The sliding black indicator on the status bars.  Updates based on the attributes.
        game.getBatch().draw(blackBar, 40*gamePet.getHappiness(), 350, 1, 50);
        game.getBatch().draw(blackBar, 40*gamePet.getHunger(), 250, 1, 50);
        game.getBatch().draw(blackBar, 40*gamePet.getTiredness(), 150, 1, 50);

        //The labels for the status bar
        game.getBatch().draw(happinessLabel, -120, 350, 60, 50);
        game.getBatch().draw(hungerLabel, -122, 245, 60, 60);
        game.getBatch().draw(fatigueLabel, -120, 150, 60, 50);
        
        //The settings button in the upper right corner.
        game.getBatch().draw(settingsButton, 120, 420, 30, 30);

        //The pet in the center of the screen.
        game.getBatch().draw(gamePet.getPetImage(), -100, -400, 200, 800);

        game.getBatch().end();

    }

    /**
     * //TODO: Christopher please comment!
     * @param delta
     */
    @Override
    public void render (float delta) {
        game.currentTime = System.currentTimeMillis()/1000;
        if (game.currentTime - game.lastTime > 1){  //Provide time in seconds.
            game.lastTime = game.currentTime;
            gamePet.decrease();
        }
        update();
        draw();
    }
}
