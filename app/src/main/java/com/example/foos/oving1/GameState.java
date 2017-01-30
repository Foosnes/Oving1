package com.example.foos.oving1;

import android.graphics.Canvas;

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
        layer = new GameLayer();
        world.addLayer(layer);
    }

    @Override
    public void draw(Canvas canvas){
        world.draw(canvas);
    }

    @Override
    public void update(float dt){
        world.update(dt);
    }

}
