package com.iScots.game;

/**
 * Created by caseysass on 4/11/15. (credit to https://github.com/libgdx/libgdx-demo-superjumper)
 */

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
    public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NONLOOPING = 1;

    final TextureRegion[] keyFrames;
    final float frameDuration;

    public Animation (float frameDuration, TextureRegion... keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
    }

    public TextureRegion getKeyFrame (float stateTime, int mode) {
        int frameNumber = (int)(stateTime / frameDuration);

        if (mode == ANIMATION_NONLOOPING) {
            frameNumber = Math.min(keyFrames.length - 1, frameNumber);
        } else {
            frameNumber = frameNumber % keyFrames.length;
        }
        return keyFrames[frameNumber];
    }
}