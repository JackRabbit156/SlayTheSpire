package de.bundeswehr.auf.slaythespire.gui.layouts.map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Die Klasse 'LegendLayout' stellt die Legende für die Spielkarte dar,
 * die verschiedene Elemente (wie Gegner, Schätze usw.) visuell
 * repräsentiert und dem Spieler hilft, die Karte besser zu verstehen.
 *
 * <p>
 * Die Legende enthält mehrere Legendenpunkte, die durch Bilder
 * und Beschriftungen dargestellt werden. Jedes Element der Legende
 * wird in einem vertikalen Layout (VBox) angeordnet und
 * in einen StackPane gelegt, um die Hintergrundgrafik anzuzeigen.
 * </p>
 *
 * @author Warawa Alexander
 */
public class LegendLayout extends StackPane {
    public LegendLayout() {
        VBox legendItems = new VBox();
        setAlignment(Pos.TOP_CENTER);

        ImageView background = image("/images/map/legend.png");

        LegendItemLayout item1 = new LegendItemLayout("Unknown", "/images/view/gui/layouts/map/legend/UnknownLegend.png");
        item1.setPadding(new Insets(140, 0, 0, 80));
        LegendItemLayout item2 = new LegendItemLayout("Merchant", "/images/view/gui/layouts/map/legend/ShopLegend.png");
        LegendItemLayout item3 = new LegendItemLayout("Treasure", "/images/view/gui/layouts/map/legend/TreasureLegend.png");
        LegendItemLayout item4 = new LegendItemLayout("Rest", "/images/view/gui/layouts/map/legend/RestLegend.png");
        LegendItemLayout item5 = new LegendItemLayout("Enemy", "/images/view/gui/layouts/map/legend/EnemyLegend.png");
        LegendItemLayout item6 = new LegendItemLayout("Elite", "/images/view/gui/layouts/map/legend/EliteLegend.png");

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
