package com.iScots.game;


import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

/**
 * Created by Christopher on 3/23/2015.
 */
public class Pet {
    private int hunger;
    private int happiness;
    private int tiredness;

    private Texture petImage;

    Random rand = new Random();

    public Pet() {
        this.hunger = 100;
        this.happiness = 100;
        this.tiredness = 90;
        this.petImage = Assets.pet5;
    }

    public void update(String action) {
        if (action.equals("sleep")) {
            if (tiredness < 100) { tiredness += 10 * multiplier();}
            if (hunger > 0) { hunger -= 10 * multiplier();}
            if (happiness > 0) { happiness -= 10 * multiplier();}
        } else if (action.equals("feed")) {
            if (tiredness > 0) { tiredness -= 10 * multiplier();}
            if (hunger < 100) { hunger += 10 * multiplier();}
            if (happiness > 0) { happiness -= 10 * multiplier();}
        } else if (action.equals("play")) {
            if (tiredness > 0) { tiredness -= 10 * multiplier();}
            if (hunger > 0) { hunger -= 10 * multiplier();}
            if (happiness < 100) { happiness += 10 * multiplier();}
        }
        else {
            System.out.println("illegal argument");
        }
        if (happiness < 0) {happiness = 0;}
        else if (happiness > 100) {happiness = 100;}
        if (hunger < 0) {hunger = 0;}
        else if (hunger > 100) {hunger = 100;}
        if (tiredness < 0) {tiredness = 0;}
        else if (tiredness > 100) {tiredness = 100;}
        updateState();
    }

        public double multiplier() {
            return rand.nextDouble();
        }

        private void updateState() {
            if (happiness < 100 / 6.0) {setPetImage(Assets.pet0);}
            else if (happiness < 2 * 100 / 6.0) {setPetImage(Assets.pet1);}
            else if (happiness < 3 * 100 / 6.0) {setPetImage(Assets.pet2);}
            else if (happiness < 4 * 100 / 6.0) {setPetImage(Assets.pet3);}
            else if (happiness < 5 * 100 / 6.0) {setPetImage(Assets.pet4);}
            else if (happiness <= 6 * 100 / 6.0) {setPetImage(Assets.pet5);}
        }
//    public void doNothing(){
//        tiredness = tiredness - 10;
//        hunger =  hunger - 10;
//        happiness = happiness - 10;
//    }

    public float getHunger(){
        return hunger;
    }

    public float getHappiness(){
        return happiness;
    }

    public float getTiredness(){
        return tiredness;
    }

    private void setPetImage(Texture texture) {
        petImage = texture;
    }

    public Texture getPetImage(){
        return petImage;
    }
}
