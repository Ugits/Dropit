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
    private double positionY;
    private double velocity;


    public void emulateDrop() {

        double point = getInitialPositionY();
        do {
            setPositionY(getInitialPositionY() - ((0.5 * gravitationalAcceleration) * Math.pow(getTime(), 2)));
            setVelocity(gravitationalAcceleration * getTime());

            if (getPositionY() < 0) {
                impactSound();
                impactDetails();
                break;
            }

            if (getPositionY() < point && getPositionY() > point - 1) {

                printMotionInfo();
                point--;
            }
            setTime(getTime() + timeStep);

        } while (getPositionY() >= 0);
    }

    private void printMotionInfo() {
        System.out.println("Time: " + Math.ceil(getTime() * 100) / 100 + " [s]\n" +
                "Position: " + Math.ceil(getPositionY()) + " [m]\n" +
                "Velocity: " + Math.ceil(getVelocity() * 100) / 100 + " [m/s]\n");
    }

    private void impactSound() {
        System.out.println();

        if (getInitialPositionY() <= 3) {
            System.out.println("Boink");
        } else if (getInitialPositionY() > 3 && getInitialPositionY() < 10) {
            System.out.println("BOINK BOINK BOINK....Boink");
        } else {
            System.out.println("...!!!!! BOOOOOOiiiNK !!!!!...");
        }

        System.out.println();
    }

    private void impactDetails() {
        setPositionY(0);
        setTime(Math.sqrt(getInitialPositionY() / (gravitationalAcceleration * 0.5)));
        setVelocity(gravitationalAcceleration * getTime());

        System.out.println("Time at impact: " + Math.ceil(getTime() * 100) / 100 + " [s]\n" +
                "Position: " + Math.ceil(getPositionY()) + " [m]\n" +
                "Velocity at impact: " + Math.ceil(getVelocity() * 100) / 100 + " [m/s]\n");
    }

    // GET ´n SET
    public void setInitialPositionY(double initialPositionY) {
        this.initialPositionY = initialPositionY;
    }

    private void setPositionY(double positionY) {
        this.positionY = positionY;
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

    private double getPositionY() {
        return positionY;
    }

    private double getVelocity() {
        return velocity;
    }

    private void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
