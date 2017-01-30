package com.example.foos.oving1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Size;

import java.util.ArrayList;
import java.util.List;

import sheep.game.Game;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.graphics.SpriteView;


public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = new FixedGame(this, null);
        GameState state = new GameState();

        game.pushState(state);

        setContentView(game);
        state.init();

    }

    class FixedGame extends Game {

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

    interface SizeListener {
        void onSizeChanged(int width, int height);
    }

}
