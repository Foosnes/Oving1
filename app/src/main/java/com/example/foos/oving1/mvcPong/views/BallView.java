package com.example.foos.oving1.mvcPong.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.foos.oving1.mvcPong.models.Ball;

/**
 * Created by Sigurd on 09.02.2017.
 */

public class BallView extends ModelView {

    private Paint paint;

    public BallView(Ball ball, Canvas canvas){
        super(ball, canvas);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    public void draw(){
        float center_x = this.model.x;
        float center_y = this.model.y;

        this.canvas.drawCircle(center_x, center_y, ((Ball)this.model).radius, paint);
    }

}
