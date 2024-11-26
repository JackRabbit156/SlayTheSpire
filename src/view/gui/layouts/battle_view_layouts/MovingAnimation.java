package view.gui.layouts.battle_view_layouts;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class MovingAnimation {
    private static final Duration ANIMATION_DURATION = Duration.millis(1100); // Animation für Hin- und Herbewegung
    private Timeline timeline;

    public MovingAnimation(Node nodeToAnimate) {
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(nodeToAnimate.scaleXProperty(), 1.0),
                        new KeyValue(nodeToAnimate.scaleYProperty(), 1.0)
                ),
                new KeyFrame(ANIMATION_DURATION.divide(2), // Vergrößerung bis zur Mitte der Dauer
                        new KeyValue(nodeToAnimate.scaleXProperty(), 1.03),
                        new KeyValue(nodeToAnimate.scaleYProperty(), 1.03)
                ),
                new KeyFrame(ANIMATION_DURATION, // Zurück auf Originalgröße
                        new KeyValue(nodeToAnimate.scaleXProperty(), 1.0),
                        new KeyValue(nodeToAnimate.scaleYProperty(), 1.0)
                )
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }
}
