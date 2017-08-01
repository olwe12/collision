package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static java.lang.Math.random;
import static sample.Main.*;
import static sample.RandomNumberFromTo.nextInt;

public class HydrogenNucleus extends AtomicNucleus{

    private int MASS = 1;
    private final int RADIUS = 10;
    private Circle particle;
    private double dX;
    private double dY;
    private double xStart;
    private double yStart;


    public HydrogenNucleus() {

        particle = new Circle(RADIUS);
        //Random color
        //particle.setFill(Color.color(random(), random(), random()));
        particle.setFill(Color.CORNSILK);
        xStart = (nextInt(1, 9) * WIDTH * 0.1);
        yStart = (nextInt(1, 9) * HEIGHT * 0.1);

        particle.relocate(xStart, yStart);

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
        //move(particle, RADIUS, Main.WIDTH, Main.HEIGHT, dX, dY);
        move();

    }));

    @Override
    public void move() {
        if (particle.getLayoutX() + RADIUS >= WIDTH) {
            dX = -dX;  System.out.println(dX);
            particle.setLayoutX(particle.getLayoutX() + dX);
        }
        if (particle.getLayoutX() - RADIUS <= 0) {
            dX = -dX;
            particle.setLayoutX(particle.getLayoutX() + dX);
        }
        if (particle.getLayoutY() + RADIUS >= HEIGHT) {
            dY = -dY;
            particle.setLayoutY(particle.getLayoutY() + dY);
        }
        if (particle.getLayoutY() - RADIUS <= 0) {
            dY = -dY;
            particle.setLayoutY(particle.getLayoutY() + dY);
        }
        particle.setLayoutX(particle.getLayoutX() + dX);
        particle.setLayoutY(particle.getLayoutY() + dY);
    }


    @Override
    public void setdX(double dX) {
        this.dX = dX;
    }
    @Override
    public void setdY(double dY) {
        this.dY = dY;
    }
    @Override
    public double getdX() {
        return dX;
    }
    @Override
    public double getdY() {return dY; }

    @Override
    public int getRadius() {return RADIUS; }
    @Override
    public double getMass() {return MASS; }
    @Override
    public Circle getNucleusImage() {
        return particle;
    }

}
