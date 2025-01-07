package de.bundeswehr.auf.slaythespire.gui.components;

import javafx.animation.PathTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.Random;

public class CombatText extends Text {

    private static final Font font = Font.font("Kreon", FontWeight.BOLD, 30);
    private static final Random rnd = new Random();

    public static void applyAnimation(CombatText combatText, Node parent) {
        Popup popup = new Popup();
        popup.setAutoHide(false);
        PathTransition transition = generatePathTransition(popup, combatText, generatePath(parent));
        Bounds bounds = parent.localToScreen(parent.getBoundsInLocal());
        popup.show(parent.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
        transition.play();
    }

    private static Path generatePath(Node parent) {
        final Path path = new Path();
        Bounds bounds = parent.getBoundsInLocal();
        path.getElements().add(new MoveTo(bounds.getMinX() + bounds.getWidth() / 2, bounds.getMinY() + bounds.getHeight() / 2));
        double x = rnd.nextInt((int) (bounds.getMaxX() - bounds.getMinX())) + bounds.getMinX();
        double y = rnd.nextInt(30) - bounds.getMinY();
        path.getElements().add(new LineTo(x, y));
        path.setOpacity(0.0);
        return path;
    }

    private static PathTransition generatePathTransition(Popup popup, Node node, Path path) {
        popup.getContent().addAll(path, node);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(2.0));
        pathTransition.setDelay(Duration.seconds(0.1));
        pathTransition.setPath(path);
        pathTransition.setNode(node);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.setOnFinished(event -> popup.hide());
        return pathTransition;
    }

    public CombatText(Paint paint, int amount) {
        setText(Integer.toString(amount));
        setTextAlignment(TextAlignment.CENTER);
        setFill(paint);
        setFont(font);
    }

}
