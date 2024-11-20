package view.gui.layouts.battle_view_layouts;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class IntentLayout extends StackPane {
    private Text intentText = new Text();
    private Text intentTextStroke = new Text();
    private ImageView intentIconView;


    private String strokeColor = "#424443";
    private int strokeWidth = 3;

    private String textColor = "#fdf8fc";

    private final Font font = Font.font("Kreon", FontWeight.BOLD, 20);
    private final FontSmoothingType smoothingType = FontSmoothingType.GRAY;


    public IntentLayout() {
        initIntendIcon();
        initText();

        getChildren().addAll(intentIconView, intentTextStroke, intentText);
        setAlignment(Pos.BOTTOM_CENTER);
    }

    public void setIntentText(String intent) {
        intentText.setText(intent);
        intentTextStroke.setText(intent);
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

    private void initIntendIcon() {
        String path = "/images/view/gui/layouts/battle_view_layouts/intent_layout/intent.png";
        Image intentIcon = new Image(getClass().getResource(path).toExternalForm());
        intentIconView = new ImageView(intentIcon);

        intentIconView.setFitHeight(50);
        intentIconView.setFitWidth(50);
    }


}
