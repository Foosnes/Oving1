package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Size;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sheep.collision.CollisionLayer;
import sheep.game.Game;
import sheep.game.Layer;
import sheep.game.State;
import sheep.game.World;
import sheep.math.BoundingBox;

/**
 * Created by Sigurd on 30.01.2017.
 */

public class GameState extends State {

    World world;
    GameLayer layer;

    public GameState(){
        world = new World();
    }

    public void init(){
        List<MainActivity.SizeListener> listeners = ((MainActivity.FixedGame) getGame()).listeners;
        layer = new GameLayer(listeners);
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

}
