package de.bundeswehr.auf.slaythespire.gui.layouts.shop;

import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;
import de.bundeswehr.auf.slaythespire.gui.ShopView;

/**
 * @author Keil, Vladislav
 */
public class PotionSelectionLayout extends FlowPane {

    private static final Font largeFont = Font.font("Kreon", FontWeight.BOLD, 30);
    private static final Font smallFont = Font.font("Kreon", FontWeight.BOLD, 20);


    private final Image bg = new Image(getClass().getResource("/images/view/gui/layouts/battle_view_layouts/potion_layout/bg.png").toExternalForm());
    private final PotionCard potion;
    private final ShopView shopView;

    public PotionSelectionLayout(PotionCard potion, ShopView shopView) {
        this.potion = potion;
        this.shopView = shopView;
        // Center of the bottom
        setAlignment(Pos.CENTER);
        if (potion != null) {
            showPotion();
        }
    }

    private void handlePotionClick() {
        // Verarbeite hier den Klick auf die Karte, z.B. öffne Details oder führe eine Aktion aus
        this.shopView.onPotionClick(potion);
    }

    private Node images() {
        Image image = new Image(getClass().getResource(potion.getImagePath()).toExternalForm());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(100); // Breite in Pixel
        imageView.setFitHeight(100); // Höhe in Pixel
        imageView.setPreserveRatio(true);
        GuiHelper.setHoverEffect(imageView);

        // Klick-Event hinzufügen
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            handlePotionClick(); // Hier eine Methode aufrufen, die das Klick-Event verarbeitet
        });

        return imageView;
    }

    private StackPane plate() {
        ImageView background = new ImageView(bg);


        Label name = new Label(potion.getName());
        name.setTextFill(Color.WHITE);
        name.setFont(largeFont);
        name.setMaxWidth(270);

        Label description = new Label(potion.getDescription());
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

    private void showPotion() {
        HBox priceBox = new HBox();
        Image img = new Image(getClass().getResource("/images/gold.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        Label label = new Label();

        label.setText(String.valueOf(potion.getPrice()));
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
