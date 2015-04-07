package com.iScots.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sam on 3/18/2015.
 */
public class Assets {
    public static Texture gameScreen;
    public static TextureRegion gameScreenRegion;

    public static Texture mainMenuScreen;
    public static Texture settingsScreen;

    public static Texture pet0;
    public static Texture pet1;
    public static Texture pet2;
    public static Texture pet3;
    public static Texture pet4;
    public static Texture pet5;

    public static Texture ball;
    public static Texture bed;
    public static Texture bone;

    public static Texture loadTexture(String file) { return new Texture(Gdx.files.internal(file));}

    public static void load() {
        gameScreen = loadTexture("background.png");
        gameScreenRegion = new TextureRegion(gameScreen);

        mainMenuScreen = new Texture("mainMenuScreen.png");
        settingsScreen = new Texture("settingsScreen.png");

        pet0 = new Texture("pet0.png");
        pet1 = new Texture("pet1.png");
        pet2 = new Texture("pet2.png");
        pet3 = new Texture("pet3.png");
        pet4 = new Texture("pet4.png");
        pet5 = new Texture("pet5.png");

        ball = new Texture("ball.png");
        bone = new Texture("bone.png");
        bed = new Texture("bed.png");
    }
}

