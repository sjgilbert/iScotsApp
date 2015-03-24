package com.iScots.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IScotGame extends Game {
	public SpriteBatch batch;
	public long startTime;
    public long currentTime;


	@Override
	public void create () {
		batch = new SpriteBatch();
        Assets.load();
		setScreen(new GameScreen(this));
        startTime = System.currentTimeMillis()/1000;
        currentTime = System.currentTimeMillis()/1000;
	}

	@Override
	public void render () {
		super.render();
	}
}
