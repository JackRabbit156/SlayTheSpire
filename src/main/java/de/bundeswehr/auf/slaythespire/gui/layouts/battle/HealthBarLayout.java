package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.components.StrokedText;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;


public class HealthBarLayout extends StackPane {

    private static final int MARGIN_LEFT = 100;
    private static final int MARGIN_TOP = 10;
    private static final Paint STROKE_COLOR = Paint.valueOf("#3f1711");
    private static final Paint TEXT_COLOR = Paint.valueOf("#f2e2e2");

    private ProgressBar healthBar;
    private final StrokedText healthText = new StrokedText(16, TEXT_COLOR, STROKE_COLOR, 3);


    public HealthBarLayout() {
        initHealthBar();
        initText();

        getChildren().addAll(healthBar, healthText);
    }

    public void setHealthText(int current, int max) {
        healthBar.setProgress((current) / (double) max);
        healthText.setText(current + "/" + max);
    }

    private void initHealthBar() {
        healthBar = new ProgressBar();
        setMargin(healthBar, new Insets(MARGIN_TOP, 0, 0, MARGIN_LEFT));
        healthBar.setPrefWidth(100);
        healthBar.setPrefHeight(5);

        healthBar.setProgress(1);
        healthBar.setStyle("-fx-accent: #c10b0b;");
    }

    private void initText() {
        setMargin(healthText, new Insets(MARGIN_TOP, 0, 0, MARGIN_LEFT));
    }

}
