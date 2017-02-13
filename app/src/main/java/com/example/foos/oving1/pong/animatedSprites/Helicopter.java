package com.example.foos.oving1.pong.animatedSprites;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.foos.oving1.R;

import sheep.graphics.Image;
import sheep.graphics.SpriteView;

/**
 * Created by Sigurd on 31.01.2017.
 */

public class Helicopter extends com.example.foos.oving1.helicopterExcercise.Helicopter {

    SpriteView[] keyFrames = new SpriteView[4];
    int keyFrameIndex = 0;
    float timeDelta = 0;

    public Helicopter(){
        keyFrames[0] = createKeyFrame(new Image(R.drawable.heli1));
        keyFrames[1] = createKeyFrame(new Image(R.drawable.heli2));
        keyFrames[2] = createKeyFrame(new Image(R.drawable.heli3));
        keyFrames[3] = createKeyFrame(new Image(R.drawable.heli4));

        setView(keyFrames[keyFrameIndex]);
    }

    @Override
    public void update(float dt){
        timeDelta = timeDelta + dt;
        if(timeDelta < 0.100f){
            return;
        }
        timeDelta = timeDelta % 0.100f;

        super.update(dt);
        keyFrameIndex = (keyFrameIndex + 1) % 4;
        setView(keyFrames[keyFrameIndex]);
    }


    public SpriteView createKeyFrame(final Image image){
        return new SpriteView() {
            @Override
            public void update(float v) {

            }

            @Override
            public void draw(Canvas canvas, Matrix matrix) {
                image.draw(canvas, getX(), getY());
            }
        };
    }

}
