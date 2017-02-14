package com.example.foos.oving1.mvcPong.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sigurd on 09.02.2017.
 */

public abstract class Model {

    private String TAG = this.getClass().getSimpleName();

    private float oldX;
    private float oldY;
    private float x;
    private float y;

    public void setPos(float x, float y){
        this.oldX = x;
        this.oldY = y;
        this.x = x;
        this.y = y;

    }

    public float getOldX() {
        return oldX;
    }

    public float getOldY() {
        return oldY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
