package de.bundeswehr.auf.slaythespire.gui.components;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MovingAnimation {

    private static final Random rnd = new Random();

    private final Timeline timeline;

    public MovingAnimation(Node nodeToAnimate) {
        Duration duration = Duration.millis(rnd.nextInt(700) + 1800); // Animation für Hin- und Herbewegung
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(nodeToAnimate.scaleXProperty(), 1.0),
                        new KeyValue(nodeToAnimate.scaleYProperty(), 1.0)
                ),
                new KeyFrame(duration.divide(2), // Vergrößerung bis zur Mitte der Dauer
                        new KeyValue(nodeToAnimate.scaleXProperty(), 1.05),
                        new KeyValue(nodeToAnimate.scaleYProperty(), 1.05)
                ),
                new KeyFrame(duration, // Zurück auf Originalgröße
                        new KeyValue(nodeToAnimate.scaleXProperty(), 1.0),
                        new KeyValue(nodeToAnimate.scaleYProperty(), 1.0)
                )
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        try {
            TimeUnit.MILLISECONDS.sleep(rnd.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

}
