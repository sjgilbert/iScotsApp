package com.iScots.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sam on 3/14/2015.
 */
public class GameScreen extends ScreenAdapter {
    IScotGame game;
    OrthographicCamera guiCam;
    Rectangle playBounds;
    Rectangle eatBounds;
    Rectangle sleepBounds;
    Texture button;
    Vector3 touchPoint;

    boolean petState = false;

    public GameScreen(IScotGame game) {
        this.game = game;

        guiCam = new OrthographicCamera(300,900);
        playBounds = new Rectangle(-150, -450, 100, 100);
        eatBounds = new Rectangle(-50, -450, 100, 100);
        sleepBounds = new Rectangle(150, -450, 100, 100);
        touchPoint = new Vector3();
        button = new Texture("Rectangle.png");

    }

    public void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            System.out.print(touchPoint);
            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                petState = !petState;
                System.out.println("play");
            }

            if (eatBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("eat");
            }

            if (sleepBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("sleep");
            }

        }
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.gameScreen, -150, -450, 300, 900); //Last two must always be the same as camera size!
        game.batch.draw(petState ? Assets.happyPet : Assets.sadPet,-50,-100, 100, 200);
        //game.batch.draw(button, -150, -450, 100, 100);
        game.batch.draw(button, -50, -450, 100, 100);
        //game.batch.draw(button, 150, -450, 100, 100);
        game.batch.end();
//        ShapeRenderer drawer = new ShapeRenderer()
//        drawer.setAutoShapeType(true);
//        drawer.begin();
//        drawer.rect(0,0,64,64);
//        drawer.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

}
