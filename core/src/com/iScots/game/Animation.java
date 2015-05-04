package com.iScots.game;

/**
 * Created by caseysass on 4/11/15. (credit to https://github.com/libgdx/libgdx-demo-superjumper)
 */

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
    public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NONLOOPING = 1;

    final TextureRegion[] keyFrames;
    private float frameDuration;

    public Animation (float frameDuration, TextureRegion... keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
    }

    public TextureRegion getKeyFrame (float stateTime, int mode) {  //Gives the appropriate frame to display based on the time the animation has been running, the time that should be spent in each frame, and the total number of frames
        int frameNumber = (int)(stateTime / frameDuration);

        if (mode == ANIMATION_NONLOOPING) {
            frameNumber = Math.min(keyFrames.length - 1, frameNumber);
        } else {
            frameNumber = frameNumber % keyFrames.length;
        }
        return keyFrames[frameNumber];
    }

    public void setDuration(float frameDuration) {  //Sets the duration the animation spends on each frame
        this.frameDuration = frameDuration;
    }

}