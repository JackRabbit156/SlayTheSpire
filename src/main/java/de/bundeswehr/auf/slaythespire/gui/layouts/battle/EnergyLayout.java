package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Das Energy layout.
 * Das kleine Icon linkt, welches angibt wie viel energy der spieler noch hat
 *
 * @author OF Daniel Willig
 */
public class EnergyLayout extends StackPane {
    private final Text energyTextStroke = new Text();
    private final Text energyText = new Text();
    private ImageView energyImageView;


    private String strokeColor = "#632411";
    private int strokeWidth = 7;

    private String textColor = "#fffbe6";

    private final Font font = Font.font("Kreon", FontWeight.BOLD, 35);
    private final FontSmoothingType smoothingType = FontSmoothingType.GRAY;


    public EnergyLayout() {
        initEnergyImage();
        initText();

        getChildren().addAll(energyImageView, energyTextStroke, energyText);
    }

    public void setEnergyText(int currentEnergy, int maxEnergy) {
        String text = currentEnergy + "/" + maxEnergy;
        energyTextStroke.setText(text);
        energyText.setText(text);
    }

    private void initText() {
        energyTextStroke.setStroke(Paint.valueOf(strokeColor));
        energyTextStroke.setStrokeWidth(strokeWidth);
        energyText.setFill(Paint.valueOf(textColor));
        energyTextStroke.setFont(font);
        energyTextStroke.setFontSmoothingType(smoothingType);
        energyText.setFont(font);
        energyText.setFontSmoothingType(smoothingType);
    }

    private void initEnergyImage() {
        String path = "/images/view/gui/layouts/battle/energy/Energy.png";
        Image energyIcon = new Image(getClass().getResource(path).toExternalForm());
        energyImageView = new ImageView(energyIcon);

        energyImageView.setFitHeight(120);
        energyImageView.setFitWidth(120);
    }
}
