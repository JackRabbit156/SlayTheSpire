package view.gui.layouts.map_view_layouts;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class LegendItemLayout extends HBox {

    public LegendItemLayout(String itemName, String itemPath) {
        Label itemNameLabel = new Label(itemName);
        itemNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        itemNameLabel.setTranslateY(20);

        ImageView itemImageView = image(itemPath);
        setPadding(new Insets(-16, 0, 0, 80));

        getChildren().addAll(itemImageView, itemNameLabel);
    }

    private ImageView image(String imagePath) {
        Image figureImage = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageViewFigure = new ImageView(figureImage);
        imageViewFigure.setFitWidth(70);
        imageViewFigure.setFitHeight(70);
        imageViewFigure.setPreserveRatio(true);
        return imageViewFigure;
    }

}
