package de.bundeswehr.auf.slaythespire.gui.components;

import com.sun.javafx.scene.traversal.Direction;
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

public class InventoryText extends Text {

    private static final Font font = Font.font("Kreon", FontWeight.BOLD, 30);
    private static final Random rnd = new Random();

    public static void applyAnimation(InventoryText inventoryText, Node parent, Direction direction) {
        Popup popup = new Popup();
        popup.setAutoHide(false);
        Path path = generatePath(parent, inventoryText, direction);
        PathTransition transition = generatePathTransition(popup, inventoryText, path);
        Bounds bounds = parent.localToScreen(parent.getBoundsInLocal());
        popup.show(parent.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
        transition.play();
    }

    private static Path generatePath(Node parent, Node node, Direction direction) {
        final Path path = new Path();
        Bounds bounds = parent.getBoundsInLocal();
        double x = rnd.nextInt((int) (bounds.getMaxX() - bounds.getMinX())) + bounds.getMinX();
        double y = rnd.nextInt(30) + bounds.getMaxY() + 70;
        double startX;
        double startY;
        double endX;
        double endY;
        if (direction == Direction.DOWN) {
            startX = bounds.getMinX() + bounds.getWidth() / 2;
            startY = bounds.getMinY() + bounds.getHeight() / 2;
            endX = x;
            endY = y;
        }
        else {
            startX = x;
            startY = y;
            endX = bounds.getMinX() + bounds.getWidth() / 2;
            endY = bounds.getMinY() + bounds.getHeight() / 2;
        }
        node.setTranslateX(startX);
        node.setTranslateY(startY);
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));
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

    public InventoryText(Paint paint, int amount) {
        setText(Integer.toString(amount));
        setTextAlignment(TextAlignment.CENTER);
        setFill(paint);
        setFont(font);
    }

}
