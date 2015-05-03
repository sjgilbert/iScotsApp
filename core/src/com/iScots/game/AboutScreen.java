//package com.iScots.game;
//
///**
// * Created by Sam on 4/29/2015.
// * The screen for information on how to play the game.
// */
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.ScreenAdapter;
//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector3;
//
//public class AboutScreen extends ScreenAdapter {
//    IScotGame game;
//    OrthographicCamera guiCam;
//    Vector3 touchPoint;     //For touch
//
//    Texture backButton = Assets.returnButton; //For the gameScreen (back) button.
//    Rectangle backBounds = new Rectangle(-150, 420, 30, 30);
//
//    public AboutScreen(IScotGame game) {
//        this.game = game;
//        guiCam = new OrthographicCamera(300, 900);
//        touchPoint = new Vector3();
//    }
//
//    public void update() {
//        if (Gdx.input.justTouched()) {
//            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
//            System.out.print(touchPoint);
//
//            if (backBounds.contains(touchPoint.x, touchPoint.y)) {
//                System.out.println("back");
//                game.setScreen(game.getMainMenuScreen());
//                return;
//            }
//        }
//    }
//
//    /**
//     * Currently only draws the background and a placeholder for the back button.  Also reset button.
//     */
//    public void draw() {
//        GL20 gl = Gdx.gl;
//        gl.glClearColor(1, 1, 1, 1);
//        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        guiCam.update();
//        game.getBatch().setProjectionMatrix(guiCam.combined);
//        game.getBatch().enableBlending();
//        game.getBatch().begin();
//
//        game.getBatch().draw(Assets.settingsScreen, -150, -450, 300, 900); //The background.  Last two must always be the same as camera size!
//
//        game.getBatch().draw(backButton, -150, 420, 30, 30); //The back button
//
//        game.getBatch().end();
//    }
//
//    @Override
//    public void render (float delta) {
//        update();
//        draw();
//    }
//}