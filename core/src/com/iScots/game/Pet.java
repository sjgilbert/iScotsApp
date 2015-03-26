package com.iScots.game;


/**
 * Created by Christopher on 3/23/2015.
 */
public class Pet {
    private float hunger;
    private float happiness;
    private float tiredness;

    public Pet() {
        this.hunger = 100;
        this.happiness = 100;
        this.tiredness = 90;
    }

    public void update(String action) {
        if (action == "sleep") {
            tiredness += 20;
            hunger -= 10;
            happiness -= 10;
        } else if (action == "feed") {
            tiredness -= 10;
            hunger += 20;
            happiness -= 10;
        } else if (action == "play") {
            tiredness -= 10;
            hunger -= 10;
            happiness += 20;
        }
        else {
            System.out.println("illegal argument");
        }
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
}
