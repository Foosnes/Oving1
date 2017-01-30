package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;
import sheep.math.BoundingBox;

import static com.example.foos.oving1.Helicopter.COLLISION_DIR.BOTTOM;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.LEFT;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.RIGHT;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.TOP;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.NONE;


/**
 * Created by Sigurd on 30.01.2017.
 */

public class Helicopter extends Sprite {

    private String TAG = "Helicopter: ";

    private float xPos = 200;
    private float yPos = 200;

    private float xChange = 3f;
    private float yChange = 3f;

    private float[] heliPoints = {};
    private float[] screenPoints = {};
    private BoundingBox screenBox;

    public Helicopter () {
        super(new Image(R.drawable.heli1));
        setPosition(xPos,yPos);
        heliPoints = getBoundingBox().getPoints();
    }

    @Override
    public void update(float dt){
        super.update(dt);

        COLLISION_DIR collisionDirection = getCollision();
        reactToCollision(collisionDirection);

        xPos += xChange;
        yPos += yChange;

        setPosition(xPos, yPos);
    }

    public void setBoundingBox(BoundingBox box){
        this.screenBox = box;
        screenPoints = box.getPoints();
        Log.w(TAG, String.format("ScreenBox %f %f %f %f ",screenPoints[0],screenPoints[1],screenPoints[2],screenPoints[3]));
        Log.w(TAG, "ScreenBox is set");
    }

    public void reactToCollision(COLLISION_DIR collision){
        switch (collision){
            case TOP:
                yChange = -yChange;
                return;
            case BOTTOM:
                yChange = -yChange;
                return;
            case LEFT:
                xChange = -xChange;
                return;
            case RIGHT:
                xChange = -xChange;
                return;
            default:
                return;
        }
    }

    public COLLISION_DIR getCollision(){
        if(heliPoints.length == 0){
            //Log.w(TAG, "Helipoints not set");
            return NONE;
        }
        if(screenPoints.length == 0){
            Log.w(TAG, "ScreenPoints not set");
            return NONE;
        }

        if(heliPoints[0] < screenPoints[0]){
            return LEFT;
        }

        if(heliPoints[1] > screenPoints[1]){
            return RIGHT;
        }

        if(heliPoints[2] < screenPoints[2]){
            return TOP;
        }

        if(heliPoints[3] > screenPoints[3]){
            return BOTTOM;
        }

        return NONE;
    }

    public enum COLLISION_DIR {
        LEFT, RIGHT, TOP, BOTTOM, NONE;
    }

}
