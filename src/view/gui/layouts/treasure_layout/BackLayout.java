package view.gui.layouts.treasure_layout;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import view.gui.TreasureView;

public class BackLayout extends HBox {
    private TreasureView view;

    public BackLayout(TreasureView view) {
        this.view = view;
        this.setMouseTransparent(false);
        init();
    }

    private void init() {
        initBackButton();
    }

    private void initBackButton() {
        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL-small.png").toExternalForm());
        ImageView imgView = new ImageView(btnImage);

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;  -fx-font-family: Kreon;");

        getChildren().add(GuiHelper.addButtonStackPane(imgView,label, 12,8));

        imgView.setOnMouseClicked(event -> this.onBackClick());
        label.setOnMouseClicked(event -> this.onBackClick());

        setAlignment(Pos.CENTER);
//        setTranslateY(150);
    }

    private void onBackClick() {
        this.view.onBackClick();
    };
}
