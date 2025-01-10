package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.components.StrokedText;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

/**
 * Das Defend layout.
 * Das kleine Icon neben der Healthbar welches zeigt wie viel verteidigung das Objekt gerade hat
 *
 * @author OF Daniel Willig
 */
public class DefendLayout extends StackPane {

    private static final Paint STROKE_COLOR = Paint.valueOf("#13503e");
    private static final Paint TEXT_COLOR = Paint.valueOf("#fdf9fc");

    private ImageView blockShieldView;
    private final StrokedText blockText = new StrokedText(20, TEXT_COLOR, STROKE_COLOR, 3);

    /**
     * Constructor Defend layout.
     */
    public DefendLayout() {
        initBlockShield();

        getChildren().addAll(blockShieldView, blockText);
    }

    public void setBlockText(int block) {
        blockText.setText(String.valueOf(block));
    }

    private void initBlockShield() {
        String path = "/images/gui/battle/Block.png";
        Image blockShield = new Image(getClass().getResource(path).toExternalForm());
        blockShieldView = new ImageView(blockShield);

        blockShieldView.setFitHeight(34);
        blockShieldView.setFitWidth(34);
    }

}
