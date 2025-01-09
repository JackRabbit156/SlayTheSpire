package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.components.StrokedText;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

public abstract class IconLayout extends StackPane {

    private ImageView imageView;
    private final StrokedText text;

    public IconLayout() {
        initImage(getPath());
        text = new StrokedText(getFontSize(), Paint.valueOf(getTextColor()), Paint.valueOf(getStrokeColor()), getStrokeWidth());
        getChildren().addAll(imageView, new AnchorPane(text));
        anchorTop(100.0);
        anchorRight(50.0);
        anchorBottom(100.0);
        anchorLeft(50.0);
    }

    public IconLayout(String path) {
        initImage(path);
        text = new StrokedText(getFontSize(), Paint.valueOf(getTextColor()), Paint.valueOf(getStrokeColor()), getStrokeWidth());
        getChildren().addAll(imageView, new AnchorPane(text));
        anchorTop(0.0);
        anchorRight(0.0);
        anchorBottom(0.0);
        anchorLeft(0.0);
    }

    protected void anchorBottom(Double value) {
        AnchorPane.setBottomAnchor(text, value);
    }

    protected void anchorLeft(Double value) {
        AnchorPane.setLeftAnchor(text, value);
    }

    protected void anchorRight(Double value) {
        AnchorPane.setRightAnchor(text, value);
    }

    protected void anchorTop(Double value) {
        AnchorPane.setTopAnchor(text, value);
    }

    protected abstract double getFontSize();

    protected abstract String getPath();

    protected abstract double getSize();

    protected abstract String getStrokeColor();

    protected abstract double getStrokeWidth();

    protected abstract String getTextColor();

    protected void setText(String text) {
        this.text.setText(text);
    }

    private void initImage(String path) {
        imageView = new ImageView(new Image(path));
        imageView.setFitHeight(getSize());
        imageView.setFitWidth(getSize());
    }

}
