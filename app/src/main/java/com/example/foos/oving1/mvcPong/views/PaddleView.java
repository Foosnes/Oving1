package com.example.foos.oving1.mvcPong.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.foos.oving1.mvcPong.models.Paddle;

import sheep.game.Sprite;

/**
 * Created by Sigurd on 09.02.2017.
 */

public class PaddleView extends ModelView {

    Paint paint;

    public PaddleView(Paddle paddle){
        super(paddle);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    public void draw(Canvas canvas) {
        float left = this.model.getX();
        float top = this.model.getY();
        float right = left + ((Paddle)this.model).width;
        float bottom = top + ((Paddle)this.model).length;

        canvas.drawRect(left, top, right, bottom, paint);
    }
}
