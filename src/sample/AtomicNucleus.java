package sample;

import javafx.scene.shape.Circle;

import static sample.Main.HEIGHT;
import static sample.Main.WIDTH;

public abstract class AtomicNucleus {

    private int MASS = 1;
    private final int RADIUS = 10;
    private Circle particle = new Circle();
    private double dX = 1;
    private double dY = 1;
    private double xStart;
    private double yStart;

    /*public void move(Circle particle, int RADIUS, int WIDTH, int HEIGHT, double dX, double dY) {
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
    */

    public abstract void move();
    public abstract int getRadius();
    public abstract Circle getNucleusImage();
    public abstract void setdX(double dX);
    public abstract void setdY(double dY);
    public abstract double getdX();
    public abstract double getdY();
    public abstract double getMass();


}
