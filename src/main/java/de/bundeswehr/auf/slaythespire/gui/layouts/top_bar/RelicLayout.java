package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;


/**
 * Das Potion layout.
 * bis zu drei kleine Icons rechts der Info; wird nur angezeigt, wenn auch Potions im Inventar sind.
 * Sind anklickbar.
 *
 * @author OF Daniel Willig
 */
public class RelicLayout extends HBox {

    private static final Font largeFont = Font.font("Kreon", FontWeight.BOLD, 30);
    private static final Font smallFont = Font.font("Kreon", FontWeight.BOLD, 20);

    private final Image bg = new Image(getClass().getResource("/images/view/gui/layouts/potion/bg.png").toExternalForm());
    private final Player player;

    public RelicLayout(Player player) {
        this.player = player;

        setAlignment(Pos.CENTER_LEFT);

        showRelic();
    }

    public void refresh() {
        getChildren().clear();
        showRelic();
    }

    private Node images(Relic relic) {
        Image image = new Image(relic.getImagePath(), 64, 64, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        setEventHandler(imageView, relic);
        return imageView;
    }

    private void setEventHandler(ImageView imageView, Relic relic) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setHeight(30);
        glow.setWidth(30);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showPopup(relic, imageView));
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(glow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> imageView.setEffect(null));
    }

    private void showPopup(Relic relic, ImageView imageView) {
        Popup popup = new Popup();
        popup.setAutoHide(true);

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
        root.getChildren().addAll(new ImageView(bg), layout);

        popup.getContent().add(root);
        Bounds bounds = imageView.localToScreen(imageView.getBoundsInLocal());
        popup.show(imageView, bounds.getMinX() + bounds.getWidth() / 2 - popup.getWidth() / 2, bounds.getMaxY());
    }

    private void showRelic() {
        getChildren().add(images(player.getRelic()));
    }

}
