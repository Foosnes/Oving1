package com.example.foos.oving1;

import android.graphics.Canvas;
import android.util.Log;
import android.view.LayoutInflater;

import sheep.game.Layer;
import sheep.graphics.SpriteView;
import sheep.math.BoundingBox;

/**
 * Created by Sigurd on 30.01.2017.
 */

public class GameLayer extends Layer {

    private static String TAG = "GameLayer: ";

    Helicopter helicopter;

    public GameLayer(){
        helicopter = new Helicopter();
    }

    @Override
    public void update(float v) {
        helicopter.update(v);
        //Log.w(TAG, "update called");
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        helicopter.setBoundingBox(boundingBox);
        helicopter.draw(canvas);
    }
}
