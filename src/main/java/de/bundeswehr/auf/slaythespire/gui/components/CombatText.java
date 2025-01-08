package de.bundeswehr.auf.slaythespire.gui.components;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class CombatText extends StrokedText {

    public CombatText(Paint paint, int amount) {
        super(30, paint, Color.BLACK, 3);
        setText(Integer.toString(amount));
    }

}
