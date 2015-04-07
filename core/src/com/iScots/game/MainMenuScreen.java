package com.iScots.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Sam on 4/5/2015.
 */
public class MainMenuScreen extends ScreenAdapter{
    IScotGame game;
    OrthographicCamera guiCam;
    Vector3 touchPoint;

    Rectangle startBounds;
    Rectangle aboutBounds;

    public MainMenuScreen(IScotGame game) {
        this.game = game;
        guiCam = new OrthographicCamera(300, 900);
        touchPoint = new Vector3();
        startBounds = new Rectangle(-54,3.75f,100,58.15f);
        aboutBounds = new Rectangle(2,2,2,2);
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            System.out.print(touchPoint);

            if (startBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("start");
                GameScreen screen = new GameScreen(game);
                game.setGameScreen(screen);
                game.setScreen(screen);
                return;
            }

            if (aboutBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("about");
                return;
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
        game.batch.draw(Assets.mainMenuScreen, -150, -450, 300, 900); //Last two must always be the same as camera size!
        game.batch.end();

    }
    @Override
    public void render (float delta) {
        update();
        draw();
    }
}