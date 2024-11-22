package view.gui.layouts.treasure_layout;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import view.gui.TreasureView;

public class BackLayout extends VBox {
    private TreasureView view;
    private BorderPane layout;

    public BackLayout(TreasureView view) {
        this.layout = new BorderPane();
        this.view = view;
        init();
    }

    private void init() {
        initBackButton();
    }

    private void initBackButton() {
        Image btnImage = new Image(getClass().getResource("/images/buttons/buttonL.png").toExternalForm());
        ImageView imgView = new ImageView(btnImage);
        HBox bottomHBox = new HBox();

        Label label = new Label("Back");
        label.setTextFill(Paint.valueOf("White"));
        label.setStyle("-fx-font-size: 24;");

        bottomHBox.getChildren().add(GuiHelper.addButtonStackPane(imgView, label, 0.7));

        imgView.setOnMouseClicked(event -> this.onBackClick());
        label.setOnMouseClicked(event -> this.onBackClick());

        bottomHBox.setAlignment(Pos.TOP_LEFT);
        bottomHBox.setTranslateY(150);
        layout.setBottom(bottomHBox);
    }

    private void onBackClick() {
        this.view.onBackClick();
    };
}
