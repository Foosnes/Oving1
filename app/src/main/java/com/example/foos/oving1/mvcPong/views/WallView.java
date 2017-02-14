package com.example.foos.oving1.mvcPong.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.foos.oving1.mvcPong.models.Wall;

/**
 * Created by Sigurd on 09.02.2017.
 */

public class WallView extends ModelView {

    private static String TAG = "WallView";
    Paint paint;

    public WallView(Wall wall){
        super(wall);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    public void draw(Canvas canvas) {
        float left = this.model.getX();
        float top = this.model.getY();
        float right = left + ((Wall)this.model).width;
        float bottom = left + ((Wall)this.model).length;

        canvas.drawRect(left, top, right, bottom, paint);
    }
}
