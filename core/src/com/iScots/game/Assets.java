package com.iScots.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sam on 3/18/2015.
 * The class that loads all of the textures for the Spritebatch to show graphics.
 */
public class Assets {
    public static Texture gameScreen;   //The backgrounds for the various screens
    public static Texture mainMenuScreen;
    public static Texture settingsScreen;

    public static Texture pet0;     //The various states of the pet's happiness with 5 the best and 0 the worst.  Pets drawn by Casey Sass.
    public static Texture pet1;
    public static Texture pet2;
    public static Texture pet3;
    public static Texture pet4;
    public static Texture pet5;

    public static Texture greenBar;     //These are for the status bars
    public static Texture redBar;
    public static Texture blackBar;

    public static Texture ball;     //The action buttons.  Drawn by Annabelle
    public static Texture bone;
    public static Texture bed;

    public static Texture playButton; //The action buttons on the launch screen. Created by Annabelle.
    public static Texture aboutButton;
    public static Texture iScotName;

    public static Texture settingsButton; //Play page --> setting page
    public static Texture returnButton; //Settings page --> play page
    public static Texture resetButton; //reset button on settings screen

    public static Texture tail1;
    public static Texture tail2;
    public static Animation tailAnim;
    
    public static Texture blinkEye;
    public static Texture openEye;
    public static Animation blinkAnim;


    /**
     *Loads in all the assets when the app starts.
     */
    public static void load() {
        gameScreen = new Texture("gameScreen.png");
        mainMenuScreen = new Texture("launchBackground.jpg");
        settingsScreen = new Texture("settingsScreen.png");

        //images of the iScot pet
        pet0 = new Texture("pet0.png");
        pet1 = new Texture("pet1.png");
        pet2 = new Texture("pet2.png");
        pet3 = new Texture("pet3.png");
        pet4 = new Texture("pet4.png");
        pet5 = new Texture("pet5.png");


        //images for the status bars.
        greenBar = new Texture("greenRect.png");
        redBar = new Texture("redRect.png");
        blackBar = new Texture("blackRect.png");

        //action button images.
        ball = new Texture("ball.png");
        bone = new Texture("bone.png");
        bed = new Texture("bed.png");

        //images for the launch page.
        iScotName = new Texture("iScotLabel.png");
        playButton = new Texture("playButton.png");
        aboutButton = new Texture("aboutButton.png");

        settingsButton = new Texture("settingsCog.png");
        returnButton = new Texture("returnButton.png");
        resetButton = new Texture("refreshButton.png");

        tail1 = new Texture("tail1.png");
        tail2 = new Texture("tail2.png");

        tailAnim = new Animation(0.2f, new TextureRegion(tail1, 370, 470, 80, 140), new TextureRegion(tail2, 370, 470, 80, 140));

        blinkEye = new Texture("blinkEye.png");
        openEye = new Texture("transparent.png");

        blinkAnim = new Animation (0.2f, new TextureRegion(openEye, 0, 0, 30, 30), new TextureRegion(blinkEye, 0, 0, 30, 30));


    }
}