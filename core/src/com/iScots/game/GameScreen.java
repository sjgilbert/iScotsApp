package com.iScots.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    Vector3 touchPoint;

    boolean petState = false;

    public GameScreen(IScotGame game) {
        this.game = game;

        guiCam = new OrthographicCamera(400,600);
        guiCam.position.set(450 / 2, 500 / 2, 0);
        playBounds = new Rectangle(0, 0, 64, 64);
        eatBounds = new Rectangle(65, 0, 129, 64);
        sleepBounds = new Rectangle(130,0,194,64);
        touchPoint = new Vector3();
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                petState = !petState;
            }

            if (eatBounds.contains(touchPoint.x, touchPoint.y)) {

            }

            if (sleepBounds.contains(touchPoint.x, touchPoint.y)) {

            }

        }
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);

        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.gameScreen, 0, 0);
        game.batch.draw(petState ? Assets.happyPet : Assets.sadPet,100,200);
        game.batch.end();
        ShapeRenderer drawer = new ShapeRenderer();
        drawer.setAutoShapeType(true);
        drawer.begin();
        drawer.rect(0,0,64,64);
        drawer.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

}
