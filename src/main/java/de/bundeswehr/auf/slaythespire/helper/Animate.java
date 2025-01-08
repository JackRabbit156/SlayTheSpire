package de.bundeswehr.auf.slaythespire.helper;

import com.sun.javafx.scene.traversal.Direction;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.Random;

public final class Animate {
    private static final Random rnd = new Random();

    public static void circlePathAnimation(Node node, Node source, Node target, EventHandler<ActionEvent> onFinished) {
        Bounds sourceBounds = source.localToScene(source.getBoundsInLocal());
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth() / 2;
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight() / 2;
        Bounds targetBounds = target.localToScene(target.getBoundsInLocal());
        double endX = targetBounds.getMinX() + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY() + targetBounds.getHeight() / 2;
        Path path = createCircledPath(node, startX, startY, endX, endY);
        pathAnimation(node, target, path, 4.0, onFinished);
    }

    /**
     * Animation path is ABOVE the target.
     *
     * @param node       the moving node
     * @param target     the reference for the path
     * @param direction  {@link Direction#DOWN} is from a random point above to the center of {@code target}, every other direction is the opposite
     * @param onFinished will be executed after animation is finished and the animation popup is hidden
     */
    public static void pathAnimationAboveTarget(Node node, Node target, Direction direction, EventHandler<ActionEvent> onFinished) {
        Path path = createPathAboveTarget(target, node, direction);
        pathAnimation(node, target, path, onFinished);
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
        Path path = createPathBelowTarget(target, node, direction);
        pathAnimation(node, target, path, onFinished);
    }

    private static Path createPathAboveTarget(Node target, Node node, Direction direction) {
        Bounds targetBounds = target.getBoundsInLocal();
        double x = rnd.nextInt((int) (targetBounds.getMaxX() - targetBounds.getMinX())) + targetBounds.getMinX();
        double y = rnd.nextInt(30) - targetBounds.getMinY();
        Direction reverse = direction == Direction.UP ? Direction.DOWN : Direction.UP;
        return createPathBelowTarget(node, x, y, reverse, targetBounds);
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
        return createPath(node, startX, startY, endX, endY);
    }

    private static Path createPath(Node node, double startX, double startY, double endX, double endY) {
        node.setTranslateX(startX);
        node.setTranslateY(startY);
        final Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));
        path.setOpacity(0.0);
        return path;
    }

    private static Path createCircledPath(Node node, double startX, double startY, double endX, double endY) {
        node.setTranslateX(startX);
        node.setTranslateY(startY);
        final Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        double radiusX = (startX - endX) / 2;
        double radiusY = rnd.nextInt(5) * 30 + 50;
        path.getElements().add(new ArcTo(radiusX, radiusY, 0, endX, endY, true, false));
        path.setOpacity(0.0);
        return path;
    }

    private static Animation generatePathTransition(Popup popup, Node node, Path path, double duration, EventHandler<ActionEvent> onFinished) {
        popup.getContent().addAll(path, node);
        PathTransition pathTransition = new PathTransition(Duration.seconds(duration), path, node);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.setOnFinished(event -> {
            popup.hide();
            if (onFinished != null) {
                onFinished.handle(event);
            }
        });
        return pathTransition;
    }

    private static void pathAnimation(Node node, Node target, Path path, EventHandler<ActionEvent> onFinished) {
        pathAnimation(node, target, path, 2.0, onFinished);
    }

    private static void pathAnimation(Node node, Node target, Path path, double duration, EventHandler<ActionEvent> onFinished) {
        Popup popup = new Popup();
        popup.setAutoHide(false);
        ParallelTransition transition = new ParallelTransition(generatePathTransition(popup, node, path, duration, onFinished),
                generateFadeOutTransition(node, duration));
        Bounds bounds = target.localToScreen(target.getBoundsInLocal());
        popup.show(target.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
        transition.play();
    }

    private static Animation generateFadeOutTransition(Node node, double duration) {
        Timeline fadeOut = new Timeline(
            new KeyFrame(Duration.ZERO,
                new KeyValue(node.opacityProperty(), 1.0)
            ),
            new KeyFrame(Duration.seconds(duration - duration / 4.0),
                new KeyValue(node.opacityProperty(), 1.0)
            ),
            new KeyFrame(Duration.seconds(duration),
                new KeyValue(node.opacityProperty(), 0.0)
            )
        );
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);
        return fadeOut;
    }

    private Animate() {
    }

}
