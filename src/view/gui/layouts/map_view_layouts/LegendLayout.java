package view.gui.layouts.map_view_layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class LegendLayout extends StackPane {
    public LegendLayout() {
        VBox legendItems = new VBox();
        setAlignment(Pos.TOP_CENTER);

        ImageView background = image("/images/map/legend.png");

        LegendItemLayout item1 = new LegendItemLayout("Unknown", "/images/view/gui/layouts/map_view_layouts/legend_layout/UnknownLegend.png");
        item1.setPadding(new Insets(140, 0, 0, 80));
        LegendItemLayout item2 = new LegendItemLayout("Merchant", "/images/view/gui/layouts/map_view_layouts/legend_layout/ShopLegend.png");
        LegendItemLayout item3 = new LegendItemLayout("Treasure", "/images/view/gui/layouts/map_view_layouts/legend_layout/TreasureLegend.png");
        LegendItemLayout item4 = new LegendItemLayout("Rest", "/images/view/gui/layouts/map_view_layouts/legend_layout/RestLegend.png");
        LegendItemLayout item5 = new LegendItemLayout("Enemy", "/images/view/gui/layouts/map_view_layouts/legend_layout/EnemyLegend.png");
        LegendItemLayout item6 = new LegendItemLayout("Elite", "/images/view/gui/layouts/map_view_layouts/legend_layout/EliteLegend.png");

        legendItems.getChildren().addAll(item1, item2, item3, item4, item5, item6);
        getChildren().addAll(background, legendItems);

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
