package view.gui.layouts.battle_view_layouts;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class IntentLayout extends StackPane {
    private static final int MARGIN_LEFT = 100;


    private Label intentText;
    private ImageView intentIconView;

    public IntentLayout() {
        initIntendIcon();
        initLabel();

        getChildren().addAll(intentIconView, intentText);
    }

    public void setIntentText(String intent) {
        intentText.setText(intent);
    }

    private void initLabel() {
        intentText = new Label("0");
        intentText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        setMargin(blockText, new Insets(7, 0, 0, MARGIN_LEFT));
        intentText.setStyle("-fx-text-fill: red; -fx-accent: black;");

    }

    private void initIntendIcon() {
        String path = "/images/view/gui/layouts/battle_view_layouts/intent_layout/intent.png";
        Image intentIcon = new Image(getClass().getResource(path).toExternalForm());
        intentIconView = new ImageView(intentIcon);

        intentIconView.setFitHeight(50);
        intentIconView.setFitWidth(50);
    }


}
