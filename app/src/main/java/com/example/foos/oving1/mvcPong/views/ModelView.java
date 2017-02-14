package com.example.foos.oving1.mvcPong.views;

import android.graphics.Canvas;
import android.util.Log;

import com.example.foos.oving1.mvcPong.models.Model;

import sheep.game.Sprite;

/**
 * Created by Sigurd on 13.02.2017.
 */

public class ModelView extends Sprite {

    private String TAG = this.getClass().getSimpleName();

    protected Model model;

    public ModelView(Model model){
        this.model = model;
    }

}
