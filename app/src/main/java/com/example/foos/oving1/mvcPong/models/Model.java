package com.example.foos.oving1.mvcPong.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sigurd on 09.02.2017.
 */

public abstract class Model {

    private static String TAG = "Model";

    public List<ModelListener> listeners = new ArrayList<>();
    public float x;
    public float y;

    public void setPos(float x, float y){
        this.x = x;
        this.y = y;

        this.propagateChange();
    }

    /**
     * Tell all listeners about change in model
     */
    public void propagateChange(){
        Log.w(TAG, "Propagating changes");
        for (ModelListener listener: listeners) {
            listener.onModelChanged(this);
        }
    }

    public interface ModelListener {
        void onModelChanged(Model model);
    }
}
