package com.example.foos.oving1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Size;

import com.example.foos.oving1.mvcPong.controllers.GameController;
import com.example.foos.oving1.pong.PongState;

import java.util.ArrayList;
import java.util.List;

import sheep.game.Game;


public class MainActivity extends AppCompatActivity {

    FixedGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = new FixedGame(this, null);

        /* This is code for assignment 1 */
        //PongState state = new PongState(game.listeners);

        /* This is code for assignment 2, pattern excercise */
        GameController state = new GameController(game.listeners);

        game.pushState(state);

        setContentView(game);
        //state.init();

    }
    /*  */
    public class FixedGame extends Game {

        public List<SizeListener> listeners;

        public FixedGame(Context context, AttributeSet attrs) {
            super(context, attrs);
            listeners = new ArrayList<>();
        }

        @Override
        public void onSizeChanged(int w, int h, int oldw, int oldh){
            super.onSizeChanged(w,h,oldw,oldh);
            for (SizeListener listener: listeners) {
                listener.onSizeChanged(w,h);
            }
        }

    }

    public interface SizeListener {
        void onSizeChanged(int width, int height);
    }

}
