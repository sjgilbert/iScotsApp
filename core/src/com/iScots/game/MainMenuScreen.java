package com.iScots.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sun.prism.Texture;

/**
 * Created by Sam on 4/5/2015.
 * The main menu screen for when the app is first opened.  Currently only
 * has a working play button that is a one way link to the gameScreen.
 */
public class MainMenuScreen extends ScreenAdapter{
    IScotGame game;
    OrthographicCamera guiCam;
    Vector3 touchPoint;     //For handling touch.

    Rectangle startBounds;
    //TODO: Implement: Rectangle aboutBounds;


    public MainMenuScreen(IScotGame game) {
        this.game = game;
        guiCam = new OrthographicCamera(300, 900);
        touchPoint = new Vector3();

        startBounds = new Rectangle(-54,3.75f,100,110f); //size of the play button image.
        //TODO: aboutBounds = new Rectangle(2,2,2,2);
    }

    /**
     * Currently only allows the user to set the gameScreen.
     */
    public void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (startBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("start");
                game.setScreen(game.getGameScreen());
                return;
            }
            else {
                System.out.println(touchPoint);
            }
//TODO: Implement            if (aboutBounds.contains(touchPoint.x, touchPoint.y)) {
//                System.out.println("about");
//                return;
//            }
        }
    }

    /**
     * Draws the background currently.
     */
    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.getBatch().setProjectionMatrix(guiCam.combined);
        game.getBatch().enableBlending();
        game.getBatch().begin();

        game.getBatch().draw(Assets.mainMenuScreen, -150, -450, 300, 900); //Draws the background.  Last two must always be the same as camera size!
        game.getBatch().draw(Assets.iScotName, -54, 103.75f, 100, 200f); //Draws the intro title.
        game.getBatch().draw(Assets.playButton, -54,3.75f,100,110f); //Draws the play button. Matches the size of the bounds listed above.
        game.getBatch().draw(Assets.aboutButton, -54, -106.25f, 100, 110f);
        game.getBatch().end();

    }
    @Override
    public void render (float delta) {
        update();
        draw();
    }
}