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

    public WallView(Wall wall, Canvas canvas){
        super(wall, canvas);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void draw() {
        Log.w(TAG, "Drawing WallView");
        float left = this.model.x;
        float top = this.model.y;
        float right = left + ((Wall)this.model).width;
        float bottom = left + ((Wall)this.model).length;

        this.canvas.drawRect(left, top, right, bottom, paint);
    }
}
