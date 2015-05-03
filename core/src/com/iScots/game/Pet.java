package com.iScots.game;


import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

/**
 * Created by Christopher on 3/23/2015.
 * The pet for the game.  Controls all of the game logic and keeps track of the attributes.
 */
public class Pet {
    //The three attributes
    private float happiness;
    private float hunger;
    private float tiredness;

    //The image of the pet currently being displayed
    private Texture petImage;

    /**
     * Currently initializing to testing values.
     */
    public Pet() {
        this.happiness = 100f;
        this.hunger = 100f;
        this.tiredness = 100f;
        this.petImage = Assets.pet5;
    }

    //The decay is based on the formula for compound interest.
    public void decay() {
        if (tiredness > 0.5) { tiredness = tiredness*0.99995f;}
        if (hunger > 0.5) { hunger = hunger*0.99993f;}
        if (happiness > 0.5) { happiness = happiness*0.99992f;}
        updateState();
    }

    public void sleep() {
        tiredness += 25;
        if (tiredness > 100) {
            tiredness = 100;
        } else if (tiredness < .5) {
            onDeath();
        }
        updateState();
    }

    public void feed() {
        hunger += 25;
        if (hunger > 100) {
            hunger = 100;
        } else if (hunger < .5) {
            onDeath();
        }
        updateState();
    }

    public void play() {
        happiness += 25;
        if (happiness > 100) {
            happiness = 100;
        } else if (happiness < .5) {
            onDeath();
        }
        updateState();
    }

    /**
     * Method for checking the state of the pet.  Checks all attributes to see if any
     * are equal to 0.
     * @return true if pet is alive, false otherwise
     */
    public boolean isAlive() {
        if (happiness < 0.5 || hunger < 0.5 || tiredness < 0.5) {
            onDeath();
        }
        return happiness!=0 && hunger!=0 && tiredness!=0;
    }

    /**
     * Sets all attributes to 0 when the pet dies (any attribute drops below 0.5)
     */
    private void onDeath() {
        happiness = 0;
        hunger = 0;
        tiredness = 0;
    }

    /**
     * Sets the state of the pet.  Currently only sets based on happiness.
     * TODO: implement a better system for changing the pet's image.
     */
        private void updateState() {
            if (happiness < 100/6 || hunger < 100/6 || tiredness < 100/6) {setPetImage(Assets.pet0);}
            else if (happiness < 2*100/6 || hunger < 2*100/6 || tiredness < 2*100/6) {setPetImage(Assets.pet1);}
            else if (happiness < 3*100/6 || hunger < 3*100/6 || tiredness < 3*100/6) {setPetImage(Assets.pet2);}
            else if (happiness < 4*100/6 && (hunger+tiredness) > 2.0*200/6) {setPetImage(Assets.pet3);}
            else if (happiness < 5*100/6 && (hunger+tiredness) > 2.5*200/6) {setPetImage(Assets.pet4);}
            else if (happiness <= 6*100/6 && (hunger+tiredness) > 3*200/6) {setPetImage(Assets.pet5);}
        }

    public float getHunger(){
        return hunger;
    }

    public float getHappiness(){
        return happiness;
    }

    public float getTiredness(){
        return tiredness;
    }

    public void setHunger(float h){
        hunger = h;
        updateState();
    }

    public void setHappiness(float h) {happiness = h;
        updateState();
    }

    public void setTiredness(float t){tiredness = t;
        updateState();
    }

    private void setPetImage(Texture texture) {
        petImage = texture;
    }

    public Texture getPetImage(){
        return petImage;
    }
}