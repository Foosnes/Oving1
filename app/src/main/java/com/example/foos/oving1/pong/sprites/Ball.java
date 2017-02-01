package com.example.foos.oving1.pong.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.foos.oving1.MainActivity;
import com.example.foos.oving1.pong.PongState;

import sheep.collision.CollisionListener;
import sheep.collision.Rectangle;
import sheep.game.Sprite;

/**
 * Created by Sigurd on 31.01.2017.
 */

public class Ball extends Sprite implements CollisionListener, MainActivity.SizeListener {

    private static String TAG = "Ball: ";

    public final static float RADIUS = 20;
    public final Paint PAINT;
    private float width;
    private float height;

    public Ball(){
        addCollisionListener(this);
        PAINT = new Paint();
        PAINT.setColor(Color.WHITE);
        Rectangle rect = new Rectangle(2*RADIUS, 2*RADIUS);
        setShape(rect);

        setXSpeed(5);
        setYSpeed(5);
    }

    @Override
    public void draw(Canvas canvas){

        float newX = getX() + getSpeed().getX();
        float newY = getY() + getSpeed().getY();
        setPosition(newX, newY);

        canvas.drawCircle(getX(), getY(), RADIUS, PAINT);
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {

        Log.w(TAG, "Collision detected");

        if( sprite1 instanceof Paddle ) {
            setXSpeed(-getSpeed().getX());
        }

        if( sprite1 instanceof  Wall ) {

            Wall.Position pos = ((Wall) sprite1).pos;

            if( pos == Wall.Position.NORTH || pos == Wall.Position.SOUTH) {
                setYSpeed(-getSpeed().getY());
            }

            if( pos == Wall.Position.WEST){
                PongState.addScore(PongState.Player.EAST);
                resetBall();
            }

            if(pos == Wall.Position.EAST){
                PongState.addScore(PongState.Player.WEST);
                resetBall();
            }

        }

    }

    public void resetBall(){
        setPosition(width/2, height/2);
    }

    @Override
    public void onSizeChanged(int width, int height) {
        this.width = width;
        this.height = height;
        setPosition(width/2, height/2);
    }
}
