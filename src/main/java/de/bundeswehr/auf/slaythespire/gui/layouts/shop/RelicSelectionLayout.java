package de.bundeswehr.auf.slaythespire.gui.layouts.shop;

import de.bundeswehr.auf.slaythespire.gui.ShopView;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author Keil, Vladislav
 */
public class RelicSelectionLayout extends FlowPane {

    private static final Font largeFont = Font.font("Kreon", FontWeight.BOLD, 30);
    private static final Font smallFont = Font.font("Kreon", FontWeight.BOLD, 20);


    private final Image bg = new Image(getClass().getResource("/images/gui/potion/bg.png").toExternalForm());
    private final Relic relic;
    private final ShopView shopView;

    public RelicSelectionLayout(Relic relic, ShopView shopView) {
        this.relic = relic;
        this.shopView = shopView;
        setAlignment(Pos.CENTER);
        if (relic != null) {
            showRelic();
        }
    }

    private void handleRelicClick() {
        shopView.onRelicClick(relic);
    }

    private Node images() {
        Image image = new Image(getClass().getResource(relic.getImagePath()).toExternalForm());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        GuiHelper.setHoverEffect(imageView);

        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleRelicClick());

        return imageView;
    }

    private StackPane plate() {
        ImageView background = new ImageView(bg);

        Label name = new Label(relic.getName());
        name.setTextFill(Color.WHITE);
        name.setFont(largeFont);
        name.setMaxWidth(270);

        Label description = new Label(relic.getDescription());
        description.setTextFill(Color.WHITE);
        description.setFont(smallFont);
        description.setWrapText(true);
        description.setMaxWidth(270);

        VBox layout = new VBox(name, description);
        layout.setPadding(new Insets(85, 0, 0, 40));
        layout.setSpacing(50);

        StackPane root = new StackPane();
        root.getChildren().addAll(background, layout);

        return root;
    }

    private void showRelic() {
        HBox priceBox = new HBox();
        Image img = new Image(getClass().getResource("/images/gui/info/gold.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        Label label = new Label();

        label.setText(String.valueOf(relic.getPrice()));
        label.setStyle("-fx-font-size: 28px;" +
                "-fx-font-family: Kreon;");
        label.setTextFill(Paint.valueOf("White"));
        priceBox.setAlignment(Pos.CENTER);
        priceBox.getChildren().addAll(imgView, label);

        VBox box = new VBox();
        box.getChildren().addAll(images(), priceBox, plate());
        box.setAlignment(Pos.CENTER);

        getChildren().addAll(box);
    }

}
