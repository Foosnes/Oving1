package com.example.foos.oving1.mvcPong.controllers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private float frameTime = 0.025f;
    private float timeAccumulator = 0;
    private int leftPlayerScore = 0;
    private int rightPlaterScore = 0;
    private Paint textPaint = new Paint();

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
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);
    }


    public void initiateViews(){
        ballView = new BallView(ball);
        leftPaddleView = new PaddleView(leftPaddle);
        rightPaddleView = new PaddleView(rightPaddle);
        westWallView = new WallView(westWall);
        eastWallView = new WallView(eastWall);
        northWallView = new WallView(northWall);
        southWallView = new WallView(southWall);
    }


    @Override
    public void update(float dt){
        /* Only update if it is time for a new frame */
        timeAccumulator += dt;
        if(timeAccumulator > frameTime){
            timeAccumulator = timeAccumulator % frameTime;
        }else {
            return;
        }

        /* Set next position */
        float next_x = ball.getX() + speed_x;
        float next_y = ball.getY() + speed_y;
        ball.setPos(next_x, next_y);

        /* Check for collisions */
        if(hasXCollision()){
            speed_x = -speed_x;
        }
        if(hasYCollision()){
            speed_y = -speed_y;
        }

        BallState ballState = checkBallState();
        switch (ballState){
            case LeftWallHit:
                givePointTo(Player.Right);
                ball.setPos(screenWidth/2, screenHeight/2);
                return;
            case RightWallHit:
                givePointTo(Player.Left);
                ball.setPos(screenWidth/2, screenHeight/2);
                return;
            default:
                return;
        }

    }


    @Override
    public void draw(Canvas canvas){
        if(!viewsInitiated){
            initiateViews();
            setScreenPositions();
            westWallView.draw(canvas);
            eastWallView.draw(canvas);
            northWallView.draw(canvas);
            southWallView.draw(canvas);
            viewsInitiated = true;
        }
        canvas.drawColor(Color.BLACK);
        ballView.draw(canvas);
        leftPaddleView.draw(canvas);
        rightPaddleView.draw(canvas);
        canvas.drawText(""+leftPlayerScore, screenWidth/2 - screenWidth/4, 50,textPaint);
        canvas.drawText(""+rightPlaterScore, screenWidth/2 + screenWidth/4, 50,textPaint);
    }


    public void setScreenPositions(){
        if(screenWidth == 0 || screenHeight == 0){
            throw new ArrayIndexOutOfBoundsException("This will not work, screensize must be set before positions");
        }
        /* Set all object in right places of the screen */
        ball.setPos(screenWidth/2, screenHeight/2);
        leftPaddle.setPos(0, screenHeight/2);
        rightPaddle.setPos(screenWidth - leftPaddle.width, screenHeight/2);
        westWall.setPos(0,0);
        eastWall.setPos(screenWidth - eastWall.width, 0);
        northWall.setPos(0,0);
        southWall.setPos(0, screenHeight - southWall.length);
    }


    @Override
    public void onSizeChanged(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }


    public boolean collides(Ball ball, Paddle paddle ){

        /* Check if ball is over or under paddle */
        if( ball.getY() - ball.radius < paddle.getY() || ball.getY() + ball.radius > paddle.getY() + paddle.length ){
            return false;
        }

        float paddleLeftX = paddle.getX();
        float paddleRightX = paddle.getX() + paddle.width;
        if( paddleLeftX < ball.getX() + ball.radius && ball.getX() - ball.radius < paddleRightX  ){
            return true;
        }

        return false;
    }


    public boolean hasXCollision(){
        if(collides(ball, leftPaddle) || collides(ball, rightPaddle)){
            return true;
        };

        return false;
    }


    public boolean hasYCollision(){
        if( ball.getY() > southWall.getY() || ball.getY() < northWall.getY() + northWall.length ){
            return true;
        }
        return false;
    }


    public BallState checkBallState(){
        if(ball.getX() < 5){
            return BallState.LeftWallHit;
        }

        if(ball.getX() > screenWidth - 5){
            return BallState.RightWallHit;
        }

        return BallState.NoWallHit;
    }


    @Override
    public boolean onTouchMove(MotionEvent event){
        float x = event.getX();
        if(x < screenWidth/2){
            leftPaddle.setPos(leftPaddle.getX(), event.getY());
        }else{
            rightPaddle.setPos(rightPaddle.getX(), event.getY());
        }

        return true;
    }


    public void givePointTo(Player player){
        if(player == Player.Left){
            leftPlayerScore++;
        }
        else{
            rightPlaterScore++;
        }

    }


    enum BallState {
        LeftWallHit, RightWallHit, NoWallHit
    }


    enum Player {
        Left, Right
    }
}
