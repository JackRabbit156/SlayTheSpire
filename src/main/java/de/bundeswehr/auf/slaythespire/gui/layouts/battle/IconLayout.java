package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public abstract class IconLayout extends StackPane {

    private static final FontSmoothingType smoothingType = FontSmoothingType.GRAY;

    private final Font font = Font.font("Kreon", FontWeight.BOLD, getFontSize());
    private ImageView imageView;
    private final Text text = new Text();
    private final Text textStroke = new Text();

    public IconLayout() {
        initImage();
        initText();
        getChildren().addAll(imageView, new AnchorPane(textStroke), new AnchorPane(text));
        anchorTop(100.0);
        anchorRight(50.0);
        anchorBottom(100.0);
        anchorLeft(50.0);
    }

    protected void anchorBottom(Double value) {
        AnchorPane.setBottomAnchor(text, value);
        AnchorPane.setBottomAnchor(textStroke, value);
    }

    protected void anchorLeft(Double value) {
        AnchorPane.setLeftAnchor(text, value);
        AnchorPane.setLeftAnchor(textStroke, value);
    }

    protected void anchorRight(Double value) {
        AnchorPane.setRightAnchor(text, value);
        AnchorPane.setRightAnchor(textStroke, value);
    }

    protected void anchorTop(Double value) {
        AnchorPane.setTopAnchor(text, value);
        AnchorPane.setTopAnchor(textStroke, value);
    }

    protected abstract double getFontSize();

    protected abstract String getPath();

    protected abstract double getSize();

    protected abstract String getStrokeColor();

    protected abstract double getStrokeWidth();

    protected abstract String getTextColor();

    protected void setText(String text) {
        textStroke.setText(text);
        this.text.setText(text);
    }

    private void initImage() {
        Image energyIcon = new Image(getPath());
        imageView = new ImageView(energyIcon);

        imageView.setFitHeight(getSize());
        imageView.setFitWidth(getSize());
    }

    private void initText() {
        textStroke.setStroke(Paint.valueOf(getStrokeColor()));
        textStroke.setStrokeWidth(getStrokeWidth());
        textStroke.setFont(font);
        textStroke.setFontSmoothingType(smoothingType);

        text.setFill(Paint.valueOf(getTextColor()));
        text.setFont(font);
        text.setFontSmoothingType(smoothingType);
    }

}
