package com.iScots.game;

/**
 * Created by caseysass on 4/25/15.
 */

public class CoolDown extends GameObject{
    public static final float DOG_WIDTH = 1f;
    public static final float DOG_HEIGHT = 1f;

    float stateTime;

    public CoolDown(float x, float y) {
        super(x, y, DOG_WIDTH, DOG_HEIGHT);
        stateTime = 0;
    }

    public void update (float deltaTime) {
        stateTime += deltaTime;
    }
}
