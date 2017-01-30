package com.example.foos.oving1;

import android.graphics.Canvas;
import android.graphics.Matrix;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;
import sheep.math.BoundingBox;

import static com.example.foos.oving1.Helicopter.COLLISION_DIR.BOTTOM;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.LEFT;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.RIGHT;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.TOP;
import static com.example.foos.oving1.Helicopter.COLLISION_DIR.NONE;


/**
 * Created by Sigurd on 30.01.2017.
 */

public class Helicopter extends Sprite {

    private COLLISION_DIR[] COLLISSIONS = {LEFT, RIGHT, TOP, BOTTOM, NONE};

    private float xPos = 0;
    private float yPos = 0;

    private float xChange = 3;
    private float yChange = 3;

    private float[] heliPoints;
    private float[] screenPoints;
    private BoundingBox screenBox;

    public Helicopter () {
        super(new Image(R.drawable.heli1));
        setPosition(xPos,yPos);
        heliPoints = getBoundingBox().getPoints();
    }

    @Override
    public void update(float dt){
        super.update(dt);

        COLLISION_DIR collisionDirection = getCollision();
        reactToCollision(collisionDirection);

        xPos += 3f;
        yPos += 3f;

        setPosition(xPos, yPos);
    }

    public void setBoundingBox(BoundingBox box){
        this.screenBox = box;
        screenPoints = box.getPoints();
    }

    public void reactToCollision(COLLISION_DIR collision){
        switch (collision){
            case TOP:
                yChange = -yChange;
                return;
            case BOTTOM:
                yChange = -yChange;
                return;
            case LEFT:
                xChange = -xChange;
                return;
            case RIGHT:
                xChange = -xChange;
                return;
            default:
                return;
        }
    }

    public COLLISION_DIR getCollision(){
        if(heliPoints[0] < screenPoints[0]){
            return COLLISION_DIR.LEFT;
        }

        if(heliPoints[1] > screenPoints[1]){
            return COLLISION_DIR.RIGHT;
        }

        if(heliPoints[2] < screenPoints[2]){
            return COLLISION_DIR.TOP;
        }

        if(heliPoints[3] > screenPoints[3]){
            return COLLISION_DIR.LEFT;
        }

        return COLLISION_DIR.NONE;
    }

    public enum COLLISION_DIR {
        LEFT, RIGHT, TOP, BOTTOM, NONE;
    }

}
