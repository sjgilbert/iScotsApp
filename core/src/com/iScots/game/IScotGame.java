package com.iScots.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

public class IScotGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture happy;
    Texture sad;
    Texture distraught;
    Vector3 touchpoint;
    Rectangle bound1;
    Rectangle bound2;
    Rectangle bound3;
    float hunger;
    float happiness;
    float tired;
    OrthographicCamera touchcam;

	@Override
	public void create () {
		batch = new SpriteBatch();
		happy = new Texture("happypet.png");
        sad = new Texture("sadpet.png");
        distraught = new Texture("distraughtpet.png");
        touchpoint = new Vector3();     //Inspired by files located by Sam Gilbert, at https://github.com/libgdx/libgdx-demo-superjumper
        touchcam = new OrthographicCamera(300, 900);  //This seems to basically create a new image which is overlaid, since pixel size isn't as useful.
        bound1 = new Rectangle(-300, -450, 200, 300);  //Buttons.  Use the dimensions given in "OrthographicCamera."
        bound2 = new Rectangle(-100, -450, 200, 300);
        bound3 = new Rectangle(100, -450, 200, 300);
        hunger = 100;  //Statistics.
        happiness = 100;
        tired = 100;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(sad, 250, 1000, 1000, 500);
		batch.end();

        if (Gdx.input.justTouched()){  //If the screen is touched
            touchcam.unproject(touchpoint.set(Gdx.input.getX(),Gdx.input.getY(), 0));  //Assigns location of touchpoint.
            System.out.println(touchpoint);  //For determining the boundaries of various objects.
            if (bound1.contains(touchpoint.x, touchpoint.y)){
                System.out.println("Bound1 triggered!");
            }
            if (bound2.contains(touchpoint.x, touchpoint.y)){
                System.out.println("Bound2 triggered!");
            }
            if (bound3.contains(touchpoint.x, touchpoint.y)){
                System.out.println("Bound3 triggered!");
            }
        }
	}


}
