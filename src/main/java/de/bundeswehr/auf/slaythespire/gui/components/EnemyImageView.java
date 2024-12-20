package de.bundeswehr.auf.slaythespire.gui.components;

import de.bundeswehr.auf.slaythespire.model.enemy.structure.Boss;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Elite;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyImageView extends ImageView {

    public EnemyImageView(Enemy enemy) {
        super(new Image(enemy.getImagePath()));
        int factor;
        if (enemy instanceof Boss) {
            factor = 20;
        }
        else if (enemy instanceof Elite) {
            factor = 15;
        }
        else {
            factor = 10;
        }
        setFitWidth(Math.sqrt(getImage().getWidth()) * factor);
        setFitHeight(Math.sqrt(getImage().getHeight()) * factor);
        setPreserveRatio(true);

        setStyle("-fx-background-color: #926099;");
    }

}
