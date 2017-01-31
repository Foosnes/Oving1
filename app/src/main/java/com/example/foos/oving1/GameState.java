package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.util.Size;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sheep.collision.CollisionLayer;
import sheep.game.Game;
import sheep.game.Layer;
import sheep.game.State;
import sheep.game.World;
import sheep.input.TouchListener;

/**
 * Created by Sigurd on 30.01.2017.
 */

public class GameState extends State implements TouchListener{

    World world;
    GameLayer layer;
    List<TouchListener> touchListeners;

    public static String TAG = "GameState: ";

    public GameState(){
        world = new World();
        touchListeners = new ArrayList<>();
    }

    public void init(){
        List<MainActivity.SizeListener> listeners = ((MainActivity.FixedGame) getGame()).listeners;
        layer = new GameLayer(listeners, touchListeners);
        world.addLayer(layer);
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        world.draw(canvas);
    }

    @Override
    public void update(float dt){
        world.update(dt);
    }

    public boolean onTouchMove(MotionEvent event){
        boolean consumedAtLeastOnce = false;
        for (TouchListener listener : touchListeners) {
            boolean currentlyConsumed = listener.onTouchMove(event);
            if(currentlyConsumed){
                consumedAtLeastOnce = true;
            }
        }
        return consumedAtLeastOnce;
    }
}
