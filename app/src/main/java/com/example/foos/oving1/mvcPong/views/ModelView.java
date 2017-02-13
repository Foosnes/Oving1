package com.example.foos.oving1.mvcPong.views;

import android.graphics.Canvas;
import android.util.Log;

import com.example.foos.oving1.mvcPong.models.Model;

import sheep.game.Sprite;

/**
 * Created by Sigurd on 13.02.2017.
 */

public abstract class ModelView extends Sprite implements Model.ModelListener {

    private static String TAG = "ModelView";

    protected Model model;
    protected Canvas canvas;

    public ModelView(Model model, Canvas canvas){
        this.model = model;
        this.model.listeners.add(this);
        this.canvas = canvas;
    }

    public abstract void draw();

    @Override
    public void onModelChanged(Model model) {
        Log.w(TAG, "CALLING DRAW");
        draw();
    }
}
