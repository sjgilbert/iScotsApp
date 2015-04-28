package com.iScots.game;

/**
 * Created by Christopher on 4/27/2015.
 */
public class Blink extends GameObject{
    public static final float EYE_WIDTH = 1f;
    public static final float EYE_HEIGHT = 1f;

    float stateTime;

    public Blink (float x, float y) {
        super(x, y, EYE_WIDTH, EYE_HEIGHT);
        stateTime = 0;  //This seems to be the amount of time that it's meant to spend in a given state.
    }

    public void update (float deltaTime) {
        stateTime = deltaTime;
    }  //I have no idea why this is +=.

}
