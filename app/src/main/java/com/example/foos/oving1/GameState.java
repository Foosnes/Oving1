package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Color;

import sheep.collision.CollisionLayer;
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
    CollisionLayer collisionLayer;

    public GameState(){
        world = new World();
        layer = new GameLayer();
        world.addLayer(layer);


        getGame().getWidth();
        getGame().getHeight();
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
