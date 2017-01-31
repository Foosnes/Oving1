package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import sheep.collision.Interval;
import sheep.collision.Rectangle;
import sheep.collision.Shape;
import sheep.game.Game;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;
import sheep.input.TouchListener;
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

public class Helicopter extends Sprite implements MainActivity.SizeListener, TouchListener{

    private String TAG = "Helicopter: ";

    private float xPos = 400;
    private float yPos = 200;

    private float xChange = 3f;
    private float yChange = 3f;

    private float[] screenSquare = new float[4];
    private Shape shape;
    private Image img;
    private BoundingBox heliBox;

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

        COLLISION_DIR collisionDirection = getCollision(screenSquare);
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

    public COLLISION_DIR getCollision(float[] collisionSquare){
        if(collisionSquare.length == 0){
            Log.w(TAG, "CollisionSqaure not set");
            return NONE;
        }

        if(getX() - img.getWidth()/2 < collisionSquare[0]){
            Log.w(TAG, "LEFT COLLISION");
            return LEFT;
        }

        if(getX() + img.getWidth()/2 > collisionSquare[1]){
            Log.w(TAG, "RIGHT COLLISION");
            return RIGHT;
        }

        if(getY() - img.getHeight()/2 < collisionSquare[2]){
            Log.w(TAG, "TOP COLLISION");
            return TOP;
        }

        if(getY() + img.getHeight()/2 > collisionSquare[3]){
            Log.w(TAG, "BOTTOM COLLISION");
            return BOTTOM;
        }

        return NONE;
    }

    @Override
    public void onSizeChanged(int width, int height) {
        screenSquare[0] = 0;
        screenSquare[1] = width;
        screenSquare[2] = 0;
        screenSquare[3] = height;

        Log.w(TAG, String.format(" screen: %f %f %f %f ", screenSquare[0], screenSquare[1], screenSquare[2], screenSquare[3]));
    }

    @Override
    public boolean onTouchDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onTouchUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent motionEvent) {

        BoundingBox box = new BoundingBox(motionEvent.getX(), motionEvent.getX(), motionEvent.getY(), motionEvent.getY());

        float[] heliBox = getBoundingBox().getPoints();
        Log.w(TAG, String.format(" screen: %f %f %f %f ", heliBox[0], heliBox[1], heliBox[2], heliBox[3]));


        if(!getBoundingBox().contains(box)){
            Log.w(TAG, "Outisde");
            return false;
        }


        Log.w(TAG, "TOUCHED");

        setPosition(motionEvent.getX(), motionEvent.getY());

        return true;
    }

    public enum COLLISION_DIR {
        LEFT, RIGHT, TOP, BOTTOM, NONE;
    }

}
