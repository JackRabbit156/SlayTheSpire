package de.bundeswehr.auf.slaythespire.gui.components;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DamageText extends CombatText {

    public DamageText(int amount) {
        super(Paint.valueOf("#e18b81"), amount);
    }

}
