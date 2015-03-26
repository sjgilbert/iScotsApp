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
    Texture bar;
    Texture redBar;
    Vector3 touchPoint;
    Pet gamePet;


    public GameScreen(IScotGame game) {
        this.game = game;
        guiCam = new OrthographicCamera(300,900);
        playBounds = new Rectangle(-150, -450, 100, 100);
        eatBounds = new Rectangle(-50, -450, 100, 100);
        sleepBounds = new Rectangle(50, -450, 100, 100);
        touchPoint = new Vector3();
        bar = new Texture("Rectangle.png");
        redBar = new Texture("Redtangle.png");
        gamePet = new Pet();

    }

    public void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            System.out.print(touchPoint);
            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("play");
                gamePet.update("play");
            }

            if (eatBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("feed");
                gamePet.update("feed");
            }

            if (sleepBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("sleep");
                gamePet.update("sleep");
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
        game.batch.draw(redBar, -150, 300, 300, 50);
        game.batch.draw(redBar, -150, 200, 300, 50);
        game.batch.draw(redBar, -150, 100, 300, 50);
        game.batch.draw(bar, -150, 300, 300*(gamePet.getHappiness()/100), 50);
        game.batch.draw(bar, -150, 200, 300*(gamePet.getHunger()/100), 50);
        game.batch.draw(bar, -150, 100, 300*(gamePet.getTiredness()/100), 50);
        game.batch.end();

    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

}
