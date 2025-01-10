package de.bundeswehr.auf.slaythespire.gui.components.animation;

import de.bundeswehr.auf.slaythespire.gui.components.StrokedText;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CombatText extends StrokedText {

    public CombatText(Paint paint, int amount) {
        this(paint, Integer.toString(amount));
    }

    public CombatText(Paint paint, String text) {
        super(30, paint, Color.BLACK, 3);
        setText(text);
    }

}
