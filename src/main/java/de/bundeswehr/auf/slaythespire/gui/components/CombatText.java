package de.bundeswehr.auf.slaythespire.gui.components;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CombatText extends Text {

    private static final Font font = Font.font("Kreon", FontWeight.BOLD, 30);

    public CombatText(Paint paint, int amount) {
        setText(Integer.toString(amount));
        setTextAlignment(TextAlignment.CENTER);
        setFill(paint);
        setFont(font);
    }

}
