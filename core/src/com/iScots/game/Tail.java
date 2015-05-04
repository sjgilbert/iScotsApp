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
        stateTime = 0;  //The total time the animation has been going. Used it to figure out which keyFrame it should be at.
    }

    public void update (float deltaTime) {
        stateTime += deltaTime;
    }
}
