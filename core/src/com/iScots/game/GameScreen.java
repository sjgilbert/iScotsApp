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
 */
public class GameScreen extends ScreenAdapter {
    IScotGame game;
    OrthographicCamera guiCam;
    Rectangle playBounds;
    Rectangle eatBounds;
    Rectangle sleepBounds;
    Rectangle settingsBounds;
    Texture bar;
    Texture redBar;
    Vector3 touchPoint;
    Pet gamePet;
    Texture hungerLabel;

    Texture blackBar;

    public GameScreen(IScotGame game) {
        this.game = game;
        guiCam = new OrthographicCamera(300,900);
        playBounds = new Rectangle(-150, -450, 75, 150);
        eatBounds = new Rectangle(-38, -450, 75, 150);
        sleepBounds = new Rectangle(75, -450, 75, 150);
        settingsBounds = new Rectangle(120, 420, 30, 30);
        touchPoint = new Vector3();
        bar = new Texture("Rectangle.png");
        redBar = new Texture("Redtangle.png");
        gamePet = new Pet();
        blackBar = new Texture("blackSquare.png");
        hungerLabel = new Texture("hunger.png");
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            System.out.print(touchPoint);
            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("play");
                gamePet.update("play");
                return;
            }

            if (eatBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("feed");
                gamePet.update("feed");
                return;
            }

            if (sleepBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("sleep");
                gamePet.update("sleep");
                return;
            }

            if (settingsBounds.contains(touchPoint.x, touchPoint.y)) {
                System.out.println("settings");
                game.setScreen(game.getSettingsScreen());
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
        game.batch.draw(Assets.gameScreen, -150, -450, 300, 900); //Last two must always be the same as camera size!
        game.batch.draw(Assets.ball, -150, -450, 75, 150);
        game.batch.draw(Assets.bone, -38, -450, 75, 150);
        game.batch.draw(Assets.bed, 75, -450, 75, 150);



        game.batch.draw(redBar, -120, 350, 120, 50);
        game.batch.draw(redBar, -120, 250, 120, 50);
        game.batch.draw(redBar, -120, 150, 120, 50);
        game.batch.draw(bar, 0, 350, 120, 50);
        game.batch.draw(bar, 0, 250, 120, 50);
        game.batch.draw(bar, 0, 150, 120, 50);


        //The labels for the status bars.
        game.batch.draw(hungerLabel, -120, 250, 30, 20);

        game.batch.draw(blackBar, 40*gamePet.getHappiness(), 350, 1, 50);
        game.batch.draw(blackBar, 40*gamePet.getHunger(), 250, 1, 50);
        game.batch.draw(blackBar, 40*gamePet.getTiredness(), 150, 1, 50);

        game.batch.draw(redBar, 120, 420, 30, 30);

        game.batch.draw(gamePet.getPetImage(), -100, -400, 200, 800);
        game.batch.end();

    }

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
