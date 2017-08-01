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

public class Main extends Application {

    public static final int RADIUS = 15;
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    public final static int NUMBEROFHYDROGEN = 30;
    List<AtomicNucleus> listOfParticle = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.DARKGRAY);
        primaryStage.setScene(scene);

        //Add hydrogen to the scene
        for (int i = 0; i < NUMBEROFHYDROGEN; i++) {
            AtomicNucleus particle = new HydrogenNucleus();
            listOfParticle.add(particle);
            root.getChildren().add(particle.getNucleusImage());
        }

        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(new Duration(10), e -> {

            for (int i = 0; i < NUMBEROFHYDROGEN - 1; i++) {
                for (int j = i + 1; j < NUMBEROFHYDROGEN; j++) {
                    isCollision(listOfParticle.get(i), listOfParticle.get(j));
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public double getParticlCenterX(AtomicNucleus particle) {
        return particle.getNucleusImage().getLayoutX() + RADIUS;
    }

    public double getParticlCenterY(AtomicNucleus particle) {
        return particle.getNucleusImage().getLayoutY() + RADIUS;
    }

    public void isCollision(AtomicNucleus particle1, AtomicNucleus particle2) {
        double x = getParticlCenterX(particle1) - getParticlCenterX(particle2);
        double y = getParticlCenterY(particle1) - getParticlCenterY(particle2);
        double z = Math.sqrt(x * x + y * y);

        //crash
        if (z <= (particle1.getRadius() + particle2.getRadius())) {

            double particle2Vx = particle2.getdX();
            double particle2Vy = particle2.getdY();
            double oldParticle1Vx = particle1.getdX();
            double oldParticle1Vy = particle1.getdY();
            //////////////////////////////////////////////////////////////
            double particle1newDx = newVelocityX(particle1, particle2);
            double particle1NewDy = newVelocityY(particle1, particle2);

            double particle2NewDx = newVelocityX(particle2, particle1);
            double particle2NewDy = newVelocityY(particle2, particle1);

            //set new velocity
            particle1.setdX(particle1newDx);
            //New location x, solve connection problem old x + Dx
            particle1.getNucleusImage().setLayoutX(particle1.getNucleusImage().getLayoutX() + particle1newDx);


            particle1.setdY(particle1NewDy);
            particle1.getNucleusImage().setLayoutY(particle1.getNucleusImage().getLayoutY() + particle1NewDy);


            particle2.setdX(particle2NewDx);
            particle2.getNucleusImage().setLayoutX(particle2.getNucleusImage().getLayoutX() + particle2NewDx);
            particle2.setdY(particle2NewDy);
            particle2.getNucleusImage().setLayoutY(particle2.getNucleusImage().getLayoutY() + particle2NewDy);
        }
    }

    //Change for second particle
    public double newVelocityX(AtomicNucleus particle1, AtomicNucleus particle2) {

        return particle1.getdX() * (particle1.getMass() - particle2.getMass()) + (2 * particle2.getMass() *
                particle2.getdX()) / (particle1.getMass() + particle2.getMass());
    }

    public double newVelocityY(AtomicNucleus particle1, AtomicNucleus particle2) {
        return particle1.getdY() * (particle1.getMass() - particle2.getMass()) + (2 * particle2.getMass() *
                particle2.getdY()) / (particle1.getMass() + particle2.getMass());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
