package com.example.foos.oving1.pong.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.foos.oving1.MainActivity;

import sheep.collision.Rectangle;
import sheep.collision.Shape;
import sheep.game.Sprite;
import sheep.input.Touch;
import sheep.input.TouchListener;

/**
 * Created by Sigurd on 31.01.2017.
 */

public class Paddle extends Sprite implements TouchListener, MainActivity.SizeListener {

    private String TAG = "Paddle: ";


    private Paint PAINT;
    private Shape shape;
    private float width = 20;
    private float height = 150;
    private Side side;

    public Paddle(Side side){
        this.side = side;
        PAINT = new Paint();
        PAINT.setColor(Color.WHITE);
        shape = new Rectangle(width, height);
        setShape(shape);
    }

    @Override
    public void draw(Canvas canvas){
        float right = getX() + width;
        float bottom = getY() + height;
        canvas.drawRect(getX(), getY(), right, bottom, PAINT);
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

        if(!isOnCorrectSide()){
            return false;
        }

        setPosition(getX(), motionEvent.getY());

        return false;
    }

    public boolean isOnCorrectSide(){
        return true;
    }

    @Override
    public void onSizeChanged(int width, int screenHeight) {
        float newY = screenHeight/2 - this.height/2;
        float newX = 0;

        if(side == Side.RIGHT){
            newX = width - this.width;
        }

        setPosition(newX, newY);
    }

    public enum Side {
        LEFT, RIGHT
    }
}
