package com.iScots.game;


import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

/**
 * Created by Christopher on 3/23/2015.
 */
public class Pet {
    private float hunger;
    private float happiness;
    private float tiredness;

    private Texture petImage;

    Random rand = new Random();

    public Pet() {
        this.hunger = 1.0f;
        this.happiness = 2.8f;
        this.tiredness = 2.5f;
        this.petImage = Assets.pet5;
    }

    public void update(String action) {
        if (action.equals("sleep")) {
            if (tiredness < 3) { tiredness += 0.2*multiplier(tiredness);}
            if (hunger > -3) { hunger -= 0.2*multiplier(hunger);}
            if (happiness > -3) { happiness -= 0.2*multiplier(happiness);}
        } else if (action.equals("feed")) {
            if (tiredness > -3) { tiredness -= 0.2 * multiplier(tiredness);}
            if (hunger < 3) { hunger += 0.2 * multiplier(hunger);}
            if (happiness > -3) { happiness -= 0.2 * multiplier(happiness);}
        } else if (action.equals("play")) {
            if (tiredness > -3) { tiredness -= 0.2 * multiplier(tiredness);}
            if (hunger > -3) { hunger -= 0.2 * multiplier(hunger);}
            if (happiness < 3) { happiness += 0.2 * multiplier(happiness);}
        }
        else {
            System.out.println("illegal argument");
        }
        if (happiness < -3) {happiness = -3;}
        else if (happiness > 3) {happiness = 3;}
        if (hunger < -3) {hunger = -3;}
        else if (hunger > 3) {hunger = 3;}
        if (tiredness < -3) {tiredness = -3;}
        else if (tiredness > 3) {tiredness = 3;}
        updateState();
    }

        public float multiplier(float x) {
            return (float) (1/Math.sqrt(2*Math.PI)*Math.exp(-Math.pow(-x,2)/(2*1.5)));
        }

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

    public void decrease(){
        if (tiredness > -3) { tiredness -= 0.2 * multiplier(tiredness);}
        if (hunger > -3) { hunger -= 0.2*multiplier(hunger);}
        if (happiness > -3) { happiness -= 0.2*multiplier(happiness);}
    }

    private void setPetImage(Texture texture) {
        petImage = texture;
    }

    public Texture getPetImage(){
        return petImage;
    }
}
