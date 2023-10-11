package com.jonas.drop;
import com.jonas.drop.objects.Cube;
import com.jonas.drop.physics.Gravity;
import com.jonas.drop.physics.Time;

public class Main implements Gravity, Time {

    public static void main(String[] args) {

        Cube cube = new Cube();

        // setting position to 5 meters
        cube.setInitialPositionY(100);
        cube.emulateDrop();
        // equation to get time
        //cube.emulateDrop();



    }

}


