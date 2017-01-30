package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Matrix;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;

/**
 * Created by Sigurd on 30.01.2017.
 */

public class Helicopter extends Sprite {

    private float xPos = 0;
    private float yPos = 0;

    public Helicopter () {
        super(new Image(R.drawable.heli1));
        setPosition(xPos,yPos);
    }

    @Override
    public void update(float dt){
        super.update(dt);

        xPos += 1f;
        yPos += 1f;

        setPosition(xPos, yPos);
    }

}
