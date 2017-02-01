package com.example.foos.oving1.pong.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.TimeZoneFormat;
import android.widget.HorizontalScrollView;

import com.example.foos.oving1.MainActivity;

import sheep.collision.Rectangle;
import sheep.collision.Shape;
import sheep.game.Sprite;

/**
 * Created by Sigurd on 31.01.2017.
 */

public class Wall extends Sprite implements MainActivity.SizeListener {

    public final Position pos;
    private Paint PAINT;
    private boolean shapeIsSet;
    private Shape shape;
    private float width;
    private float height;

    public Wall(Position pos){
        this.pos = pos;
        PAINT = new Paint();

        if (pos == Position.WEST || pos == Position.EAST){
            PAINT.setColor(Color.RED);
        }
        else {
            PAINT.setColor(Color.WHITE);
        }
    }

    public void draw(Canvas canvas){
        float right = getX() + width;
        float bottom = getY() + height;

        canvas.drawRect(getX(), getY(), right, bottom, PAINT);
    }

    @Override
    public void onSizeChanged(int width, int height) {

        if(pos == Position.NORTH){
            this.width = width;
            this.height = 10;
            setPosition(0, 0);
        }
        else if(pos == Position.SOUTH){
            this.width = width;
            this.height = 10;
            setPosition(0, height - this.height);
        }
        else if(pos == Position.WEST){
            this.width = 10;
            this.height = height;
            setPosition(0, 0);
        }
        else if(pos == Position.EAST){
            this.width = 10;
            this.height = height;
            setPosition(width - this.width, 0);
        }

        setShape( new Rectangle(this.width, this.height) );
    }

    public enum Position {
        NORTH, SOUTH, WEST, EAST
    }

}
