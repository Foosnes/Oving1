package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Matrix;

import sheep.game.Sprite;
import sheep.graphics.SpriteView;

/**
 * Created by Sigurd on 30.01.2017.
 */

public class Helicopter extends SpriteView {

    Sprite sprite;

    public Helicopter () {
        sprite = new Sprite(R.drawable.heli1);
    }

    @Override
    public void update(float v) {

    }

    @Override
    public void draw(Canvas canvas, Matrix matrix) {

    }
}
