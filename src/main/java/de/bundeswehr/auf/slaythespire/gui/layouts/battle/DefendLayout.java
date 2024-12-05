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
 * Das Defend layout.
 * Das kleine Icon neben der Healthbar welches zeigt wie viel verteidigung das Objekt gerade hat
 *
 * @author OF Daniel Willig
 */
public class DefendLayout extends StackPane {
    private final Text blockTextStroke = new Text();
    private final Text blockText = new Text();
    private ImageView blockShieldView;


    private final String strokeColor = "#13503e";
    private final int strokeWidth = 3;

    private final String textColor = "#fdf9fc";

    private final Font font = Font.font("Kreon", FontWeight.BOLD, 20);
    private final FontSmoothingType smoothingType = FontSmoothingType.GRAY;


    /**
     * Constructor Defend layout.
     */
    public DefendLayout(){
        initBlockShield();
        initText();

        getChildren().addAll(blockShieldView, blockTextStroke, blockText);
    }

    public void setBlockText(int block) {
        blockTextStroke.setText(String.valueOf(block));
        blockText.setText(String.valueOf(block));
    }

    private void initText(){
        blockTextStroke.setStroke(Paint.valueOf(strokeColor));
        blockTextStroke.setStrokeWidth(strokeWidth);
        blockText.setFill(Paint.valueOf(textColor));
        blockTextStroke.setFont(font);
        blockTextStroke.setFontSmoothingType(smoothingType);
        blockText.setFont(font);
        blockText.setFontSmoothingType(smoothingType);
    }

    private void initBlockShield(){
        String path = "/images/view/gui/layouts/battle_view_layouts/defend_layout/Block.png";
        Image blockShield = new Image(getClass().getResource(path).toExternalForm());
        blockShieldView = new ImageView(blockShield);

        blockShieldView.setFitHeight(34);
        blockShieldView.setFitWidth(34);
    }
}
