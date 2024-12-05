package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;


/**
 * Das Intent layout.
 * Die Icons über jeden Gegner, welches die nächste Aktion hervorsagt
 *
 * @author OF Daniel Willig
 */
public class IntentLayout extends StackPane {
    private final Text intentText = new Text();
    private final Text intentTextStroke = new Text();

    private ImageView intentIconView = new ImageView();

    private final String strokeColor = "#424443";
    private final int strokeWidth = 3;

    private final String textColor = "#fdf8fc";

    private final Font font = Font.font("Kreon", FontWeight.BOLD, 20);
    private final FontSmoothingType smoothingType = FontSmoothingType.GRAY;

    private Enemy enemy;


    public IntentLayout(Enemy enemy) {
        this.enemy = enemy;
        initIntentIcon();
        initText();

        getChildren().addAll(intentIconView, intentTextStroke, intentText);
        setAlignment(Pos.BOTTOM_CENTER);
    }

    public void setIntentText(String intent) {
        intentText.setText(intent);
        intentTextStroke.setText(intent);
    }

    public void setIntentIcon(String intent) {
        if (intent != null)
            intentIconView.setImage(new Image(getClass().getResource(intent).toExternalForm()));
    }

    private void initText() {
        intentTextStroke.setStroke(Paint.valueOf(strokeColor));
        intentTextStroke.setStrokeWidth(strokeWidth);
        intentText.setFill(Paint.valueOf(textColor));
        intentTextStroke.setFont(font);
        intentTextStroke.setFontSmoothingType(smoothingType);
        intentText.setFont(font);
        intentText.setFontSmoothingType(smoothingType);
    }

    private void initIntentIcon() {
        intentIconView.setFitHeight(75);
        intentIconView.setFitWidth(75);
    }


}
