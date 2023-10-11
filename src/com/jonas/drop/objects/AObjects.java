package com.jonas.drop.objects;

import com.jonas.drop.physics.Gravity;
import com.jonas.drop.physics.Time;

import java.text.DecimalFormat;

public abstract class AObjects implements Gravity, Time {
    DecimalFormat df = new DecimalFormat("#.##");
    // Physical attribution
    int volume;
    int weight;
    int mass;

    // Motion
    private double time = 0.0;
    private double initialPositionY;
    private double newPositionY;
    private double velocity;


    public void emulateDrop() {

        double point = getInitialPositionY();
        do {
            setNewPositionY(getInitialPositionY() - ((0.5 * gravitationalAcceleration) * Math.pow(getTime(), 2)));
            setVelocity(gravitationalAcceleration * getTime());

            if (getNewPositionY() < 0) {
                impactSound();
                impactDetails();
                break;
            }

            printMotionInfo(point);
            setTime(getTime() + timeStep);

        } while (getNewPositionY() >= 0);
    }

    void printMotionInfo(double point) {
        if (getNewPositionY() < point && getNewPositionY() > point - 1) {

            System.out.println("Time: " + Math.ceil(getTime() * 100) / 100 + " [s]\n" +
                    "Position: " + Math.ceil(getNewPositionY()) + " [m]\n" +
                    "Velocity: " + Math.ceil(getVelocity() * 100) / 100 + " [m/s]\n");

            point--;
        }
    }
    void impactSound() {
        System.out.println();

        if (initialPositionY <= 3) {
            System.out.println("Boink");
        } else if (initialPositionY > 3 && initialPositionY < 10) {
            System.out.println("BOINK BOINK BOINK....Boink");
        } else {
            System.out.println("...!!!!! BOOOOOOiiiNK !!!!!...");
        }

        System.out.println();
    }
    void impactDetails() {
        newPositionY = 0;
        time = Math.sqrt(initialPositionY / (gravitationalAcceleration * 0.5));
        velocity = gravitationalAcceleration * time;

        System.out.println("Time at impact: " + Math.ceil(time * 100) / 100 + " [s]\n" +
                "Position: " + Math.ceil(newPositionY) + " [m]\n" +
                "Velocity at impact: " + Math.ceil(velocity * 100) / 100 + " [m/s]\n");
    }

    // GET ´n SET
    public void setInitialPositionY(double initialPositionY) {
        this.initialPositionY = initialPositionY;
    }

    private void setNewPositionY(double newPositionY) {
        this.newPositionY = newPositionY;
    }

    private double getTime() {
        return time;
    }

    private void setTime(double time) {
        this.time = time;
    }

    private double getInitialPositionY() {
        return initialPositionY;
    }

    private double getNewPositionY() {
        return newPositionY;
    }

    private double getVelocity() {
        return velocity;
    }

    private void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
