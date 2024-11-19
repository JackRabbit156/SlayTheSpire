package view.gui.layouts.battle_view_layouts;

import helper.PathAssistent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DefendLayout extends StackPane {
    private static final int MARGIN_LEFT = 100;


    private Label blockText;
    private ImageView blockShieldView;

    public DefendLayout(){
        initBlockShield();
        initLabel();

        getChildren().addAll(blockShieldView, blockText);
    }

    public void setBlockText(int block) {
        blockText.setText(String.valueOf(block));
    }

    private void initLabel(){
        blockText = new Label("0");
        blockText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        setMargin(blockText, new Insets(7, 0, 0, MARGIN_LEFT));
        blockText.setStyle("-fx-text-fill: white; -fx-accent: blue;");
    }

    private void initBlockShield(){
        String path = "/images/view/gui/layouts/battle_view_layouts/defend_layout/Block.png";
        Image blockShield = new Image(getClass().getResource(path).toExternalForm());
        blockShieldView = new ImageView(blockShield);

        blockShieldView.setFitHeight(50);
        blockShieldView.setFitWidth(50);
    }
}
