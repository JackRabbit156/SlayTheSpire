package de.bundeswehr.auf.slaythespire.gui.layouts.battle_view_layouts;

import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class HealthBarLayout extends StackPane {
    private static final int MARGIN_LEFT = 100;
    private final Text healthText = new Text();
    private final Text healthTextStroke = new Text();
    private ProgressBar healthBar;


    private String strokeColor = "#3f1711";
    private int strokeWidth = 3;

    private String textColor = "#f2e2e2";

    private final Font font = Font.font("Kreon", FontWeight.BOLD, 16);
    private final FontSmoothingType smoothingType = FontSmoothingType.GRAY;


    public HealthBarLayout(){
        initHealthBar();
        initText();

        getChildren().addAll(healthBar, healthTextStroke, healthText);
    }

    public void setHealthText(int current, int max) {
        healthBar.setProgress((current)/(double)max);
        String text = current + "/" + max;
        healthTextStroke.setText(text);
        healthText.setText(text);
    }

    private void initText(){
        healthTextStroke.setStroke(Paint.valueOf(strokeColor));
        healthTextStroke.setStrokeWidth(strokeWidth);
        healthText.setFill(Paint.valueOf(textColor));
        healthTextStroke.setFont(font);
        healthTextStroke.setFontSmoothingType(smoothingType);
        healthText.setFont(font);
        healthText.setFontSmoothingType(smoothingType);

        setMargin(healthText, new Insets(10, 0, 0, MARGIN_LEFT));
        setMargin(healthTextStroke, new Insets(10, 0, 0, MARGIN_LEFT));
    }

    private void initHealthBar(){
        healthBar = new ProgressBar();
        setMargin(healthBar, new Insets(10, 0, 0, MARGIN_LEFT));
        setMargin(healthTextStroke, new Insets(10, 0, 0, MARGIN_LEFT));
        //healthBar.setPadding(new Insets(0, 0, 0, 200));
        healthBar.setPrefWidth(100); //Breite in Pixel
        healthBar.setPrefHeight(5);//Höhe in Pixel

        healthBar.setProgress(1);
        healthBar.setStyle("-fx-accent: #c10b0b;");
    }

}
