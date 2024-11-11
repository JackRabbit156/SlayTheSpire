package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class HealthBarLayout extends StackPane {
    private static final int MARGIN_LEFT = 100;

    private ProgressBar healthBar;
    private Label healthText;

    public HealthBarLayout(int maxHealth){
        initHealthBar();
        initlabel();

        getChildren().addAll(healthBar, healthText);
    }

    public void setHealthText(int current, int max) {
        healthBar.setProgress((current)/(double)max);
        healthText.setText(current + " / " + max);
    }

    private void initlabel(){
        healthText = new Label("80/80");
        healthText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        setMargin(healthText, new Insets(7, 0, 0, MARGIN_LEFT));
    }

    private void initHealthBar(){
        healthBar = new ProgressBar();
        setMargin(healthBar, new Insets(10, 0, 0, MARGIN_LEFT));
        //healthBar.setPadding(new Insets(0, 0, 0, 200));
        healthBar.setPrefWidth(100); //Breite in Pixel
        healthBar.setPrefHeight(14);//Höhe in Pixel

        healthBar.setProgress(1);
        healthBar.setStyle("-fx-accent: red;");
    }

}
