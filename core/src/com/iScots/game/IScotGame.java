package com.iScots.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The main game class. Has all of the screens.  //TODO:Christopher please comment!
 * Much of the screen architecture, touchpoint handling, drawing, rendering and updating method structure
 * of this game was found in the libgdx demo code at https://github.com/libgdx/libgdx-demo-superjumper
 */
public class IScotGame extends Game {
    private SpriteBatch batch;  //The SpriteBatch used to draw all the graphics for the game.

    public long currentAnimTime; //Time for the animations
    public long lastAnimTime;


    //The three screens for the game.
    //private MainMenuScreen mainMenuScreen;
    private GameScreen gameScreen;
    //private SettingsScreen settingsScreen;

    @Override
    public void create () {
        batch = new SpriteBatch();  //The spritebatch used to draw everything

        Assets.load();  //Loads in the assets

        //Creating the screens
        //mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);
        //settingsScreen = new SettingsScreen(this);

        //Setting the initial screen
        setScreen(gameScreen);

        lastAnimTime = System.currentTimeMillis()/100;    //For the animations
        currentAnimTime = System.currentTimeMillis()/100;

    }

    @Override
    public void render () {
        super.render();
    }

//    public MainMenuScreen getMainMenuScreen() {
//        return mainMenuScreen;
//    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

//    public SettingsScreen getSettingsScreen() {
//        return settingsScreen;
//    }

    public SpriteBatch getBatch() { return batch;}
}