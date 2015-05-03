//package com.iScots.game;
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
///**
// * Created by Sam on 4/5/2015.
// * The settings Screen.  Currently a placeholder.  Only has a button in the
// * upper left corner that sets the screen to gameScreen.
// */
//public class SettingsScreen extends ScreenAdapter {
//    IScotGame game;
//    OrthographicCamera guiCam;
//    Vector3 touchPoint;     //For touch
//
//    Texture backButton = Assets.returnButton; //For the gameScreen (back) button.
//    Rectangle backBounds = new Rectangle(-150, 420, 30, 30);
//
//    Texture resetButton = Assets.resetButton;
//    Rectangle resetBounds = new Rectangle(-54, 3.75f, 100,110f);
//
//    public SettingsScreen(IScotGame game) {
//        this.game = game;
//        guiCam = new OrthographicCamera(300, 900);
//        touchPoint = new Vector3();
//    }
//
//    /**
//     * Currently only allows the user to go back to the game and reset.
//     */
//    public void update() {
//        if (Gdx.input.justTouched()) {
//            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
//            System.out.print(touchPoint);
//
//            if (backBounds.contains(touchPoint.x, touchPoint.y)) {
//                System.out.println("back");
//                game.setScreen(game.getGameScreen());
//                return;
//            }
//
//            if (resetBounds.contains(touchPoint.x,touchPoint.y)) { //Resets.
//                System.out.println("reset");
//                FileHandle filehandle = Gdx.files.local(".IScotGame");
//
//                Pet pet = game.getGameScreen().getGamePet();
//                pet.setHappiness(100);
//                pet.setHunger(95);
//                pet.setTiredness(96);
//
//                filehandle.writeString(Double.toString(System.currentTimeMillis()/1000.0) + "\n", false); //"False" means that this overwrites previous local file in that location.
//                filehandle.writeString(Float.toString(pet.getHappiness()) + "\n", true);  //"True" means that this is appended to local file.
//                filehandle.writeString(Float.toString(pet.getHunger()) + "\n", true);
//                filehandle.writeString(Float.toString(pet.getTiredness()) + "\n", true);
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
//        game.getBatch().draw(resetButton, -54, 3.75f, 100,110f); //The reset button.
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