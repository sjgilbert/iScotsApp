package com.iScots.game;


import com.badlogic.gdx.graphics.Texture;

import java.util.Calendar;

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

    private static final long SIX = 21600;
    private static final long SEVEN = 25200;
    private static final long EIGHT = 28800;
    private static final long NINE = 32400;
    private static final long TEN = 36000;
    private static final long ELEVEN = 39600;
    private static final long NOON = 43200;
    private static final long ONE_PM = 46800;
    private static final long TWO_PM = 50400;
    private static final long FIVE_PM = 61200;
    private static final long EIGHT_PM = 72000;
    private static final long NINE_PM = 75600;
    private static final long TEN_PM = 79200;
    private static final long ELEVEN_PM = 82800;
    /**
     * Currently initializing to testing values.
     */
    public Pet() {
        this.happiness = 100f;
        this.hunger = 100f;
        this.tiredness = 100f;
        this.petImage = Assets.pet5;
    }

    //for the offset time idea found at http://stackoverflow.com/questions/6476065/how-to-get-the-number-of-milliseconds-elapsed-so-far-today
    public long timeOfDay() {
        Calendar cal = Calendar.getInstance();
        long offset = cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET);
        return ((System.currentTimeMillis()+offset)%86400000)/1000;  //returns the current time of day in seconds
    }

    public float amp(float att) {
        if(att<100/5) {return 2.1f;}
        else if(att<2*100/5) {return 1.8f;}
        else if(att<2.5*100/5) {return 1.2f;}
        else return 1;
    }
    //The decay is based on the formula for compound interest.
    public void decay() {
        if (tiredness > 0.5) { tiredness = tiredness*0.99995f;}
        if (hunger > 0.5) { hunger = hunger*0.99994f;}
        if (happiness > 0.5) { happiness = happiness*0.99992f;}
        updateState();
    }

    public void sleep() {   //TOO EARLY TO SLEEP
        if(timeOfDay() < NOON && timeOfDay() >SEVEN){
            tiredness += amp(tiredness)*10;
            happiness -= 5;
        }                       //IDEAL BEDTIME
        else if(timeOfDay() < ELEVEN_PM && timeOfDay() > NINE_PM){
            tiredness += amp(tiredness)*25;
            happiness += amp(happiness)*10;
        }                   //NAPTIME
        else if(timeOfDay() > NOON && timeOfDay() > FIVE_PM) {
            tiredness += amp(tiredness)*20;
            happiness += amp(happiness)*5;
        }
        else {
            tiredness += amp(tiredness)*15;
        }
        if (tiredness > 100) {
            tiredness = 100;
        } else if (tiredness < .5) {
            onDeath();
        }
        updateState();
    }

    public void feed() {//LUNCH                                             BREAKFAST                       DINNER
        if(timeOfDay()> ELEVEN && timeOfDay() < ONE_PM || timeOfDay() > SIX && timeOfDay() < NINE || timeOfDay() > FIVE_PM && timeOfDay() < EIGHT_PM){
            hunger += amp(hunger)*15;
            happiness += amp(happiness)*5;
        }           //BAD TIMES TO EAT
        else if(timeOfDay() < SIX || timeOfDay() > TEN_PM) {
            hunger += amp(hunger)*5;
            happiness -= 5;
            tiredness -= 8;
        }
        else {
            hunger += amp(hunger)*10;
            happiness += amp(happiness)*3;
            tiredness += amp(tiredness)*4;
        }
        if (hunger > 100) {
            hunger = 100;
        } else if (hunger < .5) {
            onDeath();
        }
        updateState();
    }

    public void play() {    //IDEAL PLAYTIME
        if(timeOfDay() > SIX && timeOfDay() > NINE_PM) {
            happiness += amp(happiness)*5;
            tiredness -= 3;
            hunger -= 1;
        }
        else {
            happiness += amp(happiness)*4;
            tiredness -= 4;
            hunger -= 1;
        }
        if (happiness > 100) {
            happiness = 100;
        } else if (happiness < .5) {
            onDeath();
        }
        updateState();
    }

    /**
     * Method for checking the state of the pet.  Checks all attributes to see if any
     * are equal to 0.  Also triggers death sequence.
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

        public void updateTailSpeed() {
            if (happiness < 100/6 || hunger < 100/6 || tiredness < 100/6) {Assets.tailAnim.setDuration(7);}
            else if (happiness < 2*100/6 || hunger < 2*100/6 || tiredness < 2*100/6) {Assets.tailAnim.setDuration(6);}
            else if (happiness < 3*100/6 || hunger < 3*100/6 || tiredness < 3*100/6) {Assets.tailAnim.setDuration(5);}
            else if (happiness < 4*100/6 && (hunger+tiredness) > 2.0*200/6) {Assets.tailAnim.setDuration(4);}
            else if (happiness < 5*100/6 && (hunger+tiredness) > 2.5*200/6) {Assets.tailAnim.setDuration(3);}
            else if (happiness <= 6*100/6 && (hunger+tiredness) > 3*200/6) {Assets.tailAnim.setDuration(2);}
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