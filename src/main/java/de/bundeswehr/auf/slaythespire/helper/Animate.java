package de.bundeswehr.auf.slaythespire.helper;

import com.sun.javafx.scene.traversal.Direction;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.Random;

public final class Animate {
    private static final Random rnd = new Random();

    /**
     * Animation path is ABOVE the target.
     *
     * @param node       the moving node
     * @param target     the reference for the path
     * @param direction  {@link Direction#DOWN} is from a random point above to the center of {@code target}, every other direction is the opposite
     * @param onFinished will be executed after animation is finished and the animation popup is hidden
     */
    public static void pathAnimationAboveTarget(Node node, Node target, Direction direction, EventHandler<ActionEvent> onFinished) {
        pathAnimation(node, target, createPathAboveTarget(target, node, direction), onFinished);
    }

    /**
     * Animation path is BELOW the target.
     *
     * @param node       the moving node
     * @param target     the reference for the path
     * @param direction  {@link Direction#DOWN} is from the center of {@code target} to a random point below, every other direction is the opposite
     * @param onFinished will be executed after animation is finished and the animation popup is hidden
     */
    public static void pathAnimationBelowTarget(Node node, Node target, Direction direction, EventHandler<ActionEvent> onFinished) {
        pathAnimation(node, target, createPathBelowTarget(target, node, direction), onFinished);
    }

    private static Path createPathAboveTarget(Node target, Node node, Direction direction) {
        Bounds targetBounds = target.getBoundsInLocal();
        double x = rnd.nextInt((int) (targetBounds.getMaxX() - targetBounds.getMinX())) + targetBounds.getMinX();
        double y = rnd.nextInt(30) - targetBounds.getMinY();
        return createPathBelowTarget(node, x, y, direction == Direction.UP ? Direction.DOWN : Direction.UP, targetBounds);
    }

    private static Path createPathBelowTarget(Node target, Node node, Direction direction) {
        Bounds targetBounds = target.getBoundsInLocal();
        double x = rnd.nextInt((int) (targetBounds.getMaxX() - targetBounds.getMinX())) + targetBounds.getMinX();
        double y = rnd.nextInt(30) + targetBounds.getMaxY() + 70;
        return createPathBelowTarget(node, x, y, direction, targetBounds);
    }

    private static Path createPathBelowTarget(Node node, double x, double y, Direction direction, Bounds targetBounds) {
        double centerX = targetBounds.getMinX() + targetBounds.getWidth() / 2;
        double centerY = targetBounds.getMinY() + targetBounds.getHeight() / 2;
        double startX;
        double startY;
        double endX;
        double endY;
        if (direction == Direction.DOWN) {
            startX = centerX;
            startY = centerY;
            endX = x;
            endY = y;
        }
        else {
            startX = x;
            startY = y;
            endX = centerX;
            endY = centerY;
        }
        node.setTranslateX(startX);
        node.setTranslateY(startY);
        final Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));
        path.setOpacity(0.0);
        return path;
    }

    private static PathTransition generatePathTransition(Popup popup, Node node, Path path, EventHandler<ActionEvent> onFinished) {
        popup.getContent().addAll(path, node);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(2.0));
        pathTransition.setPath(path);
        pathTransition.setNode(node);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.setOnFinished(event -> {
            popup.hide();
            onFinished.handle(event);
        });
        return pathTransition;
    }

    private static void pathAnimation(Node node, Node target, Path path, EventHandler<ActionEvent> onFinished) {
        Popup popup = new Popup();
        popup.setAutoHide(false);
        PathTransition transition = generatePathTransition(popup, node, path, onFinished);
        Bounds bounds = target.localToScreen(target.getBoundsInLocal());
        popup.show(target.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
        transition.play();
    }

    private Animate() {
    }

}
