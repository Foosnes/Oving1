package com.example.foos.oving1.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.foos.oving1.MainActivity;
import com.example.foos.oving1.pong.sprites.Ball;
import com.example.foos.oving1.pong.sprites.Paddle;
import com.example.foos.oving1.pong.sprites.Wall;

import java.util.ArrayList;
import java.util.List;

import sheep.collision.CollisionLayer;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.input.TouchListener;

/**
 * Created by Sigurd on 31.01.2017.
 */

public class PongState extends State implements TouchListener, MainActivity.SizeListener{

    private static int westScore;
    private static int eastScore;

    private Paint textPaint;

    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Wall northWall;
    private Wall southhWall;
    private Wall westWall;
    private Wall easthWall;

    private CollisionLayer collisionLayer;

    List<Sprite> sprites;

    public PongState(List<MainActivity.SizeListener> listeners){
        sprites = new ArrayList<>();
        collisionLayer = new CollisionLayer();
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);

        paddle1 = new Paddle(Paddle.Side.LEFT);
        paddle2 = new Paddle(Paddle.Side.RIGHT);
        ball = new Ball();
        northWall = new Wall(Wall.Position.NORTH);
        southhWall = new Wall(Wall.Position.SOUTH);
        westWall = new Wall(Wall.Position.WEST);
        easthWall = new Wall(Wall.Position.EAST);

        sprites.add(paddle1);
        sprites.add(paddle2);
        sprites.add(ball);
        sprites.add(northWall);
        sprites.add(southhWall);
        sprites.add(westWall);
        sprites.add(easthWall);

        addTouchListener(paddle1);
        addTouchListener(paddle2);

        for (Sprite sprite: sprites) {
            collisionLayer.addSprite(sprite);
        }
        listeners.add(this);
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        for (Sprite sprite: sprites) {
            sprite.draw(canvas);
        }
        drawScores(canvas);
    }

    @Override
    public void update(float dt){
        collisionLayer.update(dt);
    }


    @Override
    public void onSizeChanged(int width, int height) {
        for (Sprite sprite: sprites) {
            if(sprite instanceof MainActivity.SizeListener){
                ((MainActivity.SizeListener)sprite).onSizeChanged(width, height);
            }
        }
    }

    public void drawScores(Canvas canvas){
        canvas.drawText(""+westScore, 50, 60, textPaint);
        canvas.drawText(""+eastScore, 600, 60, textPaint);
    }

    public static void addScore(Player player){
        if(player == Player.WEST){
            westScore += 1;
        }
        else{
            eastScore += 1;
        }

        if(westScore > 21 || eastScore > 21){
            westScore = 0;
            eastScore = 0;
        }
    }

    public enum Player {
        EAST, WEST
    }
}
