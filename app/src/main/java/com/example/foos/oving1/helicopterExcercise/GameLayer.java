package com.example.foos.oving1.helicopterExcercise;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import sheep.game.Game;
import sheep.game.Layer;
import sheep.input.TouchListener;
import sheep.math.BoundingBox;

import com.example.foos.oving1.MainActivity;
import com.example.foos.oving1.pong.animatedSprites.Helicopter;
/**
 * Created by Sigurd on 30.01.2017.
 */

public class GameLayer extends Layer {

    private static String TAG = "GameLayer: ";
    private Game game;
    private Paint textPaint;

    Helicopter helicopter;

    public GameLayer(List<MainActivity.SizeListener> sizeListeners, List<TouchListener> touchListeners){
        helicopter = new Helicopter();
        sizeListeners.add(helicopter);
        touchListeners.add(helicopter);
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
    }

    @Override
    public void update(float v) {
        helicopter.update(v);
        //Log.w(TAG, "update called");
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        helicopter.draw(canvas);
        String text = String.format("X: %f , Y: %f", helicopter.getX(), helicopter.getY());
        canvas.drawText(text, 20, 30, textPaint);
    }
}
