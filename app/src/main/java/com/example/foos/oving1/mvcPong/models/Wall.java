package com.example.foos.oving1.mvcPong.models;

/**
 * Created by Sigurd on 09.02.2017.
 */

public class Wall extends Model {

    private Dir direction;
    public float width;
    public float length;

    public Wall(Dir direction){
        this.direction = direction;

        if(direction == Dir.West || direction == Dir.East){
            width = 5;
            length = 200;
        }
        else{
            width = 200;
            length = 5;
        }
    }

    public enum Dir {
        West, East, North, South
    }

}
