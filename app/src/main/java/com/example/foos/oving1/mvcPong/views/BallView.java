package com.example.foos.oving1.mvcPong.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.foos.oving1.mvcPong.models.Ball;

/**
 * Created by Sigurd on 09.02.2017.
 */

public class BallView extends ModelView {

    private String TAG = "BallView: ";

    private Paint paint;
    private Paint backgroundPaint;

    public BallView(Ball ball){
        super(ball);
        paint = new Paint();
        backgroundPaint = new Paint();
        paint.setColor(Color.WHITE);
        backgroundPaint.setColor(Color.BLACK);
    }

    public void draw(Canvas canvas){
        float center_x = this.model.getX();
        float center_y = this.model.getY();
        canvas.drawCircle(center_x, center_y, ((Ball)this.model).radius, paint);
    }

}
