package com.iScots.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Sam on 3/18/2015.
 * The class that loads all of the textures for the Spritebatch to show graphics.
 */
public class Assets {
    public static Texture gameScreen;   //The backgrounds for the various screens
    //public static Texture mainMenuScreen;
    //public static Texture settingsScreen;

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

    //public static Texture playButton; //The action buttons on the launch screen. Created by Annabelle.
    //public static Texture aboutButton;
    public static Texture iScotName;

    //public static Texture settingsButton; //Play page --> setting page
    //public static Texture returnButton; //Settings page --> play page
    public static Texture resetButton; //reset button on settings screen

    public static Texture tail1;
    public static Texture tail2;
    public static Animation tailAnim;
    
    public static Texture blinkEye;
    public static Texture openEye;
    public static Animation blinkAnim;

    public static Texture eat1;
    public static Texture eat2;
    public static Texture eat3;
    public static Texture eat4;
    public static Animation eatAnim;

    public static Texture sleep1;
    public static Texture sleep2;
    public static Texture sleep3;
    public static Texture sleep4;
    public static Animation sleepAnim;

    public static Texture play1;
    public static Texture play2;
    public static Texture play3;
    public static Animation playAnim;

    public static Texture deathMessage;
    public static Texture timerMessage;

    /**
     *Loads in all the assets when the app starts.
     */
    public static void load() {
        gameScreen = new Texture("gameScreen.png");
        //mainMenuScreen = new Texture("launchBackground.jpg");
        //settingsScreen = new Texture("settingsScreen.png");

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
        //iScotName = new Texture("iScotLabel.png");
        //playButton = new Texture("playButton.png");
        //aboutButton = new Texture("aboutButton.png");

        //settingsButton = new Texture("settingsCog.png");
        //returnButton = new Texture("returnButton.png");
        resetButton = new Texture("refreshButton.png");

        tail1 = new Texture("tail1.png");
        tail2 = new Texture("tail2.png");

        tailAnim = new Animation(4f, new TextureRegion(tail1, 370, 470, 80, 140), new TextureRegion(tail2, 370, 470, 80, 140));

        blinkEye = new Texture("blinkEye.png");
        openEye = new Texture("transparent.png");

        blinkAnim = new Animation (6f, new TextureRegion(openEye, 0, 0, 30, 30), new TextureRegion(openEye, 0, 0, 30, 30), new TextureRegion(openEye, 0, 0, 30, 30), new TextureRegion(openEye, 0, 0, 30, 30), new TextureRegion(openEye, 0, 0, 30, 30), new TextureRegion(blinkEye, 0, 0, 30, 30)); //

        eat1 = new Texture("eating1.png");
        eat2 = new Texture("eating2.png");
        eat3 = new Texture("eating3.png");
        eat4 = new Texture("eating4.png");

        eatAnim = new Animation(2.5f, new TextureRegion(eat1), new TextureRegion(eat2), new TextureRegion(eat3), new TextureRegion(eat4));

        sleep1 = new Texture("sleep1.png");
        sleep2 = new Texture("sleep2.png");
        sleep3 = new Texture("sleep3.png");
        sleep4 = new Texture("sleep4.png");

        sleepAnim = new Animation(2.5f, new TextureRegion(sleep1), new TextureRegion(sleep2), new TextureRegion(sleep3), new TextureRegion(sleep4));

        play1 = new Texture("play1.png");
        play2 = new Texture("play2.png");
        play3 = new Texture("play3.png");

        playAnim = new Animation(2.5f, new TextureRegion(play1), new TextureRegion(play2), new TextureRegion(play1), new TextureRegion(play3));

        deathMessage = new Texture("deathmessage.png");
        timerMessage = new Texture("timermessage.png");

    }
}