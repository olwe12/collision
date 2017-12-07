package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static java.lang.Math.random;
import static sample.Main.*;
import static sample.RandomNumberFromTo.nextInt;

public class AtomicNucleus extends Circle{

    private final int MASS;
    private final int RADIUS;
    private double dX;
    private double dY;
    private double xStart;
    private double yStart;

    public AtomicNucleus(int RADIUS, int MASS, Color color) {

        this.MASS = MASS;
        this.RADIUS = RADIUS;
        setRadius(RADIUS);
        setFill(color);

        xStart = (nextInt(1, 9) * WIDTH_FRAME * 0.1);
        yStart = (nextInt(1, 9) * HEIGHT_FRAME * 0.1);

        relocate(xStart, yStart);

        if(random() < 0.5) {
            dX = -random() * 5;
            dY = -random() * 5;
        }
        else {
            dX = random() * 5;
            dY = random() * 5;
        }

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    Timeline timeline = new Timeline(new KeyFrame(new Duration(20), e -> {

        move();
        Thread.yield();

    }));

    public synchronized void move() {
        if (this.getLayoutX() + RADIUS >= WIDTH_FRAME) {
            dX = -dX;
            setLayoutX(getLayoutX() + dX);
        }
        if (getLayoutX() - RADIUS <= 0) {
            dX = -dX;
            setLayoutX(getLayoutX() + dX);
        }
        if (getLayoutY() + RADIUS >= HEIGHT_FRAME) {
            dY = -dY;
            setLayoutY(getLayoutY() + dY);
        }
        if (getLayoutY() - RADIUS <= 0) {
            dY = -dY;
            setLayoutY(getLayoutY() + dY);
        }
        setLayoutX(getLayoutX() + dX);
        setLayoutY(getLayoutY() + dY);
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public double getdX() {
        return dX;
    }

    public double getdY() {return dY; }

    public int getRADIUS() {return RADIUS;}

    public double getMass() {return MASS; }
}
