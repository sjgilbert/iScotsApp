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

    public static Texture happyPet;
    public static Texture sadPet;
    public static Texture distraughtPet;

    public static Texture loadTexture(String file) { return new Texture(Gdx.files.internal(file));}

    public static void load() {
        gameScreen = loadTexture("demopic.jpg");
        gameScreenRegion = new TextureRegion(gameScreen);

        happyPet = new Texture("happypet.png");
        sadPet = new Texture("sadpet.png");
        distraughtPet = new Texture("distraughtpet.png");
    }
}

