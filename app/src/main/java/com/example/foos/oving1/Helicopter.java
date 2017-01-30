package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import sheep.collision.Interval;
import sheep.collision.Rectangle;
import sheep.collision.Shape;
import sheep.game.Game;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

import static com.example.foos.oving1.Helicopter.COLLISION_DIR.BOTTOM;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.LEFT;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.RIGHT;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.TOP;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.NONE;


/**
 * Created by Sigurd on 30.01.2017.
 */

public class Helicopter extends Sprite implements MainActivity.SizeListener{

    private String TAG = "Helicopter: ";

    private boolean screenIsSet = false;
    private float xPos = 400;
    private float yPos = 200;

    private float xChange = 3f;
    private float yChange = 3f;

    private float[] screenPoints = new float[4];
    private Shape shape;
    private Image img;

    public Helicopter () {
        super(new Image(R.drawable.heli1));
        img = new Image(R.drawable.heli1);
        setPosition(xPos,yPos);
        shape = new Rectangle(img.getWidth(), img.getHeight());
        setShape(shape);

    }

    @Override
    public void update(float dt){
        super.update(dt);

        COLLISION_DIR collisionDirection = getCollision();
        reactToCollision(collisionDirection);

        xPos += xChange;
        yPos += yChange;

        setPosition(xPos,yPos);
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
        if(screenPoints.length == 0){
            Log.w(TAG, "ScreenPoints not set");
            return NONE;
        }

        if(getX() - img.getWidth()/2 < screenPoints[0]){
            Log.w(TAG, "LEFT COLLISION");
            return LEFT;
        }

        if(getX() + img.getWidth()/2 > screenPoints[1]){
            Log.w(TAG, String.format("RIGHT %f + %f > %f ", getX(), img.getWidth(), screenPoints[1] ));
            Log.w(TAG, "RIGHT COLLISION");
            return RIGHT;
        }

        if(getY() - img.getHeight()/2 < screenPoints[2]){
            Log.w(TAG, "TOP COLLISION");
            return TOP;
        }

        if(getY() + img.getHeight()/2 > screenPoints[3]){
            Log.w(TAG, "BOTTOM COLLISION");
            return BOTTOM;
        }

        return NONE;
    }

    @Override
    public void onSizeChanged(int width, int height) {
        screenPoints[0] = 0;
        screenPoints[1] = width;
        screenPoints[2] = 0;
        screenPoints[3] = height;

        Log.w(TAG, String.format(" screen: %f %f %f %f ", screenPoints[0], screenPoints[1], screenPoints[2], screenPoints[3]));
    }

    public enum COLLISION_DIR {
        LEFT, RIGHT, TOP, BOTTOM, NONE;
    }

}
