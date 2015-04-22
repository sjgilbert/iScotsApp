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
        this.hunger = 1.0f;
        this.happiness = 2.0f;
        this.tiredness = 2.2f;
        this.petImage = Assets.pet4;
    }

    /**
     *
     * @param action The command passed to determine which attribute to increase.
     */
    public void update(String action) {
        if (action.equals("sleep")) {
            if (tiredness < 3) { tiredness += 0.8 * multiplier(tiredness);}
        } else if (action.equals("feed")) {
            if (hunger < 3) { hunger += 0.8 * multiplier(hunger);}
        } else if (action.equals("play")) {
            if (happiness < 3) { happiness += 0.8 * multiplier(happiness);}
        }
        else if (action.equals("decay")) {
            if (tiredness > -3) { tiredness -= 0.1 * multiplier(tiredness);}
            if (hunger > -3) { hunger -= 0.1 * multiplier(hunger);}
            if (happiness > -3) { happiness -= 0.1 * multiplier(happiness);}
        }
        else {
            System.out.println("illegal argument");
        }
        //These lines are to make sure the attributes don't go out of bounds.
        if (happiness < -3) {happiness = -3;}
        else if (happiness > 3) {happiness = 3;}
        if (hunger < -3) {hunger = -3;}
        else if (hunger > 3) {hunger = 3;}
        if (tiredness < -3) {tiredness = -3;}
        else if (tiredness > 3) {tiredness = 3;}

        updateState(); //to set the proper image.
    }

    /**
     * A number used to shift the values of the attributes.  It is the function of the normal distribution.
     * @param x The value of the attribute being altered.
     * @return
     */
        public float multiplier(float x) {
            return (float) (1/Math.sqrt(2*Math.PI)*Math.exp(-Math.pow(-x,2)/(2*1.5)));
        }

    /**
     * Sets the state of the pet.  Currently only sets based on happiness.
     * TODO: implement a better system for changing the pet's image.
     */
        private void updateState() {
            if (happiness < -2) {setPetImage(Assets.pet0);}
            else if (happiness < -1) {setPetImage(Assets.pet1);}
            else if (happiness < 0) {setPetImage(Assets.pet2);}
            else if (happiness < 1) {setPetImage(Assets.pet3);}
            else if (happiness < 2) {setPetImage(Assets.pet4);}
            else if (happiness <= 3) {setPetImage(Assets.pet5);}
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

    public void setHunger(float h){hunger = h; }

    public void setHappiness(float h) {happiness = h; }

    public void setTiredness(float t){tiredness = t; }

    private void setPetImage(Texture texture) {
        petImage = texture;
    }

    public Texture getPetImage(){
        return petImage;
    }
}