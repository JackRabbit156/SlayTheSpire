package de.bundeswehr.auf.slaythespire.helper;

import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DebugPane extends StackPane {

    public DebugPane(Node children) {
        this(Color.MAGENTA, children);
    }

    public DebugPane(Paint paint, Node children) {
        super(children);
        setBorder(new Border(new BorderStroke(paint, BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

}
