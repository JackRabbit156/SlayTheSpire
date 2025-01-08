package de.bundeswehr.auf.slaythespire.gui.layouts.battle;

import de.bundeswehr.auf.slaythespire.gui.components.StrokedText;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;


/**
 * Das Intent layout.
 * Die Icons über jeden Gegner, welches die nächste Aktion hervorsagt
 *
 * @author OF Daniel Willig
 */
public class IntentLayout extends StackPane {

    private static final Paint STROKE_COLOR = Paint.valueOf("#424443");
    private static final Paint TEXT_COLOR = Paint.valueOf("#fdf8fc");

    private final Enemy enemy;
    private final ImageView intentIconView = new ImageView();
    private final StrokedText intentText = new StrokedText(20, TEXT_COLOR, STROKE_COLOR, 3);

    public IntentLayout(Enemy enemy) {
        this.enemy = enemy;
        initIntentIcon();

        getChildren().addAll(intentIconView, intentText);
        setAlignment(Pos.BOTTOM_CENTER);
    }

    public void setIntentIcon(String intent) {
        if (intent != null) {
            intentIconView.setImage(new Image(getClass().getResource(intent).toExternalForm()));
        }
    }

    public void setIntentText(String intent) {
        intentText.setText(intent);
    }

    private void initIntentIcon() {
        intentIconView.setFitHeight(75);
        intentIconView.setFitWidth(75);
    }

}
