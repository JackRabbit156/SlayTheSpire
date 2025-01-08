package de.bundeswehr.auf.slaythespire.gui.components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;

public class StrokedText extends StackPane {

    private static final FontSmoothingType smoothingType = FontSmoothingType.GRAY;

    private final Text foreground = new Text();
    private final Text stroke = new Text();

    public StrokedText(double fontSize, Paint textColor, Paint strokeColor, double strokeWidth) {
        Font font = Font.font("Kreon", FontWeight.BOLD, fontSize);
        stroke.setStroke(strokeColor);
        stroke.setStrokeWidth(strokeWidth);
        stroke.setFont(font);
        stroke.setFontSmoothingType(smoothingType);

        foreground.setFill(textColor);
        foreground.setFont(font);
        foreground.setFontSmoothingType(smoothingType);

        getChildren().addAll(stroke, foreground);
    }

    public void setText(String text) {
        foreground.setText(text);
        stroke.setText(text);
    }

}
