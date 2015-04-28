package com.iScots.game;

/**
 * Created by caseysass on 4/11/15.
 */

public class Tail extends GameObject{
    public static final float TAIL_WIDTH = 1f;
    public static final float TAIL_HEIGHT = 1f;

    float stateTime;

    public Tail (float x, float y) {
        super(x, y, TAIL_WIDTH, TAIL_HEIGHT);
        stateTime = 0;  //This seems to be the amount of time that it's meant to spend in a given state.
    }

    public void update (float deltaTime) {
        stateTime += deltaTime;
    }  //I have no idea why this is +=.
}
