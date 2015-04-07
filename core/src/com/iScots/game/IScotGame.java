package com.iScots.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IScotGame extends Game {
	public SpriteBatch batch;
	public long startTime;
    public long currentTime;
    public long lastTime;

    private MainMenuScreen mainMenuScreen;

    private GameScreen gameScreen;
    private SettingsScreen settingsScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
        Assets.load();
        mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);
        settingsScreen = new SettingsScreen(this);
		setScreen(mainMenuScreen);
        startTime = System.currentTimeMillis()/1000;
        lastTime = System.currentTimeMillis()/1000;
        currentTime = System.currentTimeMillis()/1000;
	}

	@Override
	public void render () {
        super.render();
	}

    public MainMenuScreen getMainMenuScreen() {
        return mainMenuScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public SettingsScreen getSettingsScreen() {
        return settingsScreen;
    }

    public void setGameScreen(GameScreen screen) {
        gameScreen = screen;
    }
}

