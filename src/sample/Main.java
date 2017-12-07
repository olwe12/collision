package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static sample.RandomNumberFromTo.nextInt;

public class Main extends Application {

    public final static int WIDTH_FRAME = 1200;
    public final static int HEIGHT_FRAME = 800;
    public final static int NUMBER_OF_HYDROGEN = 30;
    public final static int NUMBER_OF_HELIUM = 30;
    public final static int ALL = NUMBER_OF_HELIUM + NUMBER_OF_HYDROGEN;

    List<AtomicNucleus> listOfParticle = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH_FRAME, HEIGHT_FRAME, Color.DARKGRAY);
        primaryStage.setScene(scene);

        //Add hydrogen to the scene
        for (int i = 0; i < NUMBER_OF_HYDROGEN; i++) {
            AtomicNucleus particle = new HydrogenNucleus(10, 2, Color.ANTIQUEWHITE);
            listOfParticle.add(particle);
            root.getChildren().add(particle);
        }

        for (int i = 0; i < NUMBER_OF_HELIUM; i++) {
            AtomicNucleus particle = new HeliumNucleus(15, 1, Color.AQUA);
            listOfParticle.add(particle);

            root.getChildren().add(particle);
        }

        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(new Duration(15), e -> {

            for (int i = 0; i < ALL  - 1; i++) {
                for (int j = i + 1; j < ALL; j++) {
                    isCollision(listOfParticle.get(i), listOfParticle.get(j));
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public double getParticlCenterX(AtomicNucleus particle) {
        return particle.getLayoutX() + particle.getRADIUS();
    }

    public double getParticlCenterY(AtomicNucleus particle) {
        return particle.getLayoutY() + particle.getRADIUS();
    }

    public synchronized void isCollision(AtomicNucleus particle1, AtomicNucleus particle2) {

        double x = getParticlCenterX(particle1) - getParticlCenterX(particle2);
        double y = getParticlCenterY(particle1) - getParticlCenterY(particle2);
        double z = Math.sqrt(x * x + y * y);

        //crash
        if (z <= (particle1.getRadius() + particle2.getRadius()) + 1) {

            System.out.println("promien 2: " + particle1.getRADIUS());
            System.out.println("promien 1: " + particle2.getRADIUS());
            System.out.println("zderzenie nastapi gdy odleglosc jest mniejsza od: " + (particle1.getRadius() + particle2.getRadius()));
            System.out.println("a odleglosc wynosi: " + z);
            double particleOneOldDx = particle1.getdX();
            double particleTwoOldDy = particle1.getdY();

            double particle1newDx;
            double particle1NewDy;
            double particle2NewDx;
            double particle2NewDy;


            particle1newDx = newVelocityX(particle1, particle2);
            particle1NewDy = newVelocityY(particle1, particle2);

            particle2NewDx = newVelocityX(particle2, particle1);
            particle2NewDy = newVelocityY(particle2, particle1);

            //set new velocity
            particle1.setdX(particle1newDx);

            //New location x, solve connection problem old x + Dx
            particle1.setLayoutX(particle1.getLayoutX() + 1 * particle1newDx);

            particle1.setdY(particle1NewDy);

            particle1.setLayoutY(particle1.getLayoutY() + 1 * particle1NewDy);


            particle2.setdX(particle2NewDx);

            particle2.setLayoutX(particle2.getLayoutX() + 1 * particle2NewDx);
            particle2.setdY(particle2NewDy);

            particle2.setLayoutY(particle2.getLayoutY() + 1 * particle2NewDy);
        }
    }

    //Change for second particle
    public double newVelocityX(AtomicNucleus particle1, AtomicNucleus particle2) {

        return (particle1.getdX() * (particle1.getMass() - particle2.getMass()) + (2 * particle2.getMass() *
                particle2.getdX())) / (particle1.getMass() + particle2.getMass());
    }

    public double newVelocityY(AtomicNucleus particle1, AtomicNucleus particle2) {
        return (particle1.getdY() * (particle1.getMass() - particle2.getMass()) + (2 * particle2.getMass() *
                particle2.getdY())) / (particle1.getMass() + particle2.getMass());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
