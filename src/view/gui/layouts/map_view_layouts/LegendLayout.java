package view.gui.layouts.map_view_layouts;

import helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class LegendLayout extends StackPane {
    public LegendLayout() {
        VBox legendItems = new VBox();
        setAlignment(Pos.TOP_CENTER);

        ImageView backgound = image("/images/map/legend.png");

        LegendItemLayout item1 = new LegendItemLayout("Unknown", "/images/map_elements/field_types/EnemyField.png");
        item1.setPadding(new Insets(140, 0, 0, 80));
        LegendItemLayout item2 = new LegendItemLayout("Merchant", "/images/map_elements/field_types/EnemyField.png");
        LegendItemLayout item3 = new LegendItemLayout("Treasure", "/images/map_elements/field_types/EnemyField.png");
        LegendItemLayout item4 = new LegendItemLayout("Rest", "/images/map_elements/field_types/EnemyField.png");
        LegendItemLayout item5 = new LegendItemLayout("Enemy", "/images/map_elements/field_types/EnemyField.png");
        LegendItemLayout item6 = new LegendItemLayout("Elite", "/images/map_elements/field_types/EnemyField.png");

        legendItems.getChildren().addAll(item1, item2, item3, item4, item5, item6);
        getChildren().addAll(backgound, legendItems);

    }

    private ImageView image(String imagePath) {
        Image figureImage = new Image(getClass().getResource(imagePath).toExternalForm());
        javafx.scene.image.ImageView imageViewFigure = new ImageView(figureImage);
        imageViewFigure.setFitWidth(600);
        imageViewFigure.setFitHeight(600);
        imageViewFigure.setPreserveRatio(true);
        return imageViewFigure;
    }


}
