package com.iScots.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;
import com.sun.org.apache.xpath.internal.operations.Bool;


/**
 * Created by Christopher on 3/23/2015.
 */
public class Pet {
    float hunger;
    float happiness;
    float tired;

    public float getHunger(){
        return hunger;
    }

    public float getHappiness(){
        return happiness;
    }

    public float getTired(){
        return tired;
    }

    public void sleep(){
        tired = tired + 20;
        hunger = hunger - 10;
        happiness = happiness - 10;
    }
    public void feed(){
        tired = tired - 10;
        hunger =  hunger + 20;
        happiness = happiness - 10;
    }
    public void play(){
        tired = tired - 10;
        hunger =  hunger - 10;
        happiness = happiness + 20;
    }

    public void doNothing(){
        tired = tired - 10;
        hunger =  hunger - 10;
        happiness = happiness - 10;
    }

    public boolean checkHappy(){
        return happiness > 50;
    }
}
