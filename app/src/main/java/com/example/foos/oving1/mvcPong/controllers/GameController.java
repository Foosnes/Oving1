package com.example.foos.oving1.mvcPong.controllers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.foos.oving1.MainActivity;
import com.example.foos.oving1.mvcPong.models.Ball;
import com.example.foos.oving1.mvcPong.models.Paddle;
import com.example.foos.oving1.mvcPong.models.Wall;
import com.example.foos.oving1.mvcPong.views.BallView;
import com.example.foos.oving1.mvcPong.views.PaddleView;
import com.example.foos.oving1.mvcPong.views.WallView;

import java.util.List;

import sheep.game.State;
import sheep.input.TouchListener;

/**
 * Created by Sigurd on 09.02.2017.
 */

public class GameController extends State implements TouchListener, MainActivity.SizeListener {

    private static String TAG = "GameController";

    private boolean viewsInitiated = false;
    private int screenWidth;
    private int screenHeight;
    private float speed_x = 5;
    private float speed_y = 5;
    /* Models */
    private Ball ball = new Ball();
    private Paddle leftPaddle = new Paddle();
    private Paddle rightPaddle = new Paddle();
    private Wall westWall = new Wall(Wall.Dir.West);
    private Wall eastWall = new Wall(Wall.Dir.East);
    private Wall northWall = new Wall(Wall.Dir.North);
    private Wall southWall = new Wall(Wall.Dir.South);
    /* ModelViews */
    private BallView ballView;
    private PaddleView leftPaddleView;
    private PaddleView rightPaddleView;
    private WallView westWallView;
    private WallView eastWallView;
    private WallView northWallView;
    private WallView southWallView;

    public GameController(List<MainActivity.SizeListener> listeners){
        listeners.add(this);
    }

    public void initiateViews(Canvas canvas){
        ballView = new BallView(ball, canvas);
        leftPaddleView = new PaddleView(leftPaddle, canvas);
        rightPaddleView = new PaddleView(rightPaddle, canvas);
        westWallView = new WallView(westWall, canvas);
        eastWallView = new WallView(eastWall, canvas);
        northWallView = new WallView(northWall, canvas);
        southWallView = new WallView(southWall, canvas);
    }

    @Override
    public void update(float dt){
        /* Update stuff */

        /* Set next position */
        float next_x = ball.x + speed_x;
        float next_y = ball.y + speed_y;
        ball.setPos(next_x, next_y);

        /* Check for collisions */
        if(hasXCollision()){
            speed_x = -speed_x;
        }
        if(hasYCollision()){
            speed_y = -speed_y;
        }

        // TODO: Clear models previous positions
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        if(!viewsInitiated){
            initiateViews(canvas);
            setScreenPositions();
            viewsInitiated = true;
        }
    }

    public void setScreenPositions(){
        if(screenWidth == 0 || screenHeight == 0){
            throw new ArrayIndexOutOfBoundsException("This will not work");
        }
        /* Set all object in right places of the screen */
        ball.setPos(screenWidth/2, screenHeight/2);
        leftPaddle.setPos(0, screenHeight/2);
        rightPaddle.setPos(screenWidth - leftPaddle.width, screenHeight/2);
        Log.w(TAG, "Drawing walls");
        westWall.setPos(0,0);
        eastWall.setPos(screenWidth - eastWall.width, 0);
        northWall.setPos(0,0);
        southWall.setPos(0, screenHeight - southWall.length);
    }

    @Override
    public void onSizeChanged(int width, int height) {
        Log.w(TAG, "Screensize is now set");
        this.screenWidth = width;
        this.screenHeight = height;
    }

    public boolean hasXCollision(){
        if( ball.x > eastWall.x || ball.x < westWall.x + westWall.width ){
            return true;
        }
        return false;
    }

    public boolean hasYCollision(){
        if( ball.y > southWall.y || ball.y < northWall.y + northWall.length ){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchMove(MotionEvent event){
        // TODO: Use event to move paddles
        return false;
    }

}
