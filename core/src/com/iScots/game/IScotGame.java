package com.iScots.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IScotGame extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        Assets.load();
		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
