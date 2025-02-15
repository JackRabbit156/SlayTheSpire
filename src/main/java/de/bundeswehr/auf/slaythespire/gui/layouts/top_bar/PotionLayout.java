package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Das Potion layout.
 * bis zu drei kleine Icons rechts der Info; wird nur angezeigt, wenn auch Potions im Inventar sind.
 * Sind anklickbar.
 *
 * @author OF Daniel Willig
 */
public class PotionLayout extends MiddleBar {

    private static final Font largeFont = Font.font("Kreon", FontWeight.BOLD, 30);
    private static final Font smallFont = Font.font("Kreon", FontWeight.BOLD, 20);

    private final Image bg = new Image(getClass().getResource("/images/gui/potion/bg.png").toExternalForm());
    private final Image emptyPotionIcon = new Image(getClass().getResource("/images/gui/potion/EmptyPotion.png").toExternalForm());
    private final List<Potion> potions;
    private final Set<Popup> popups = new HashSet<>();

    public PotionLayout(List<Potion> potions) {
        this.potions = potions;

        setAlignment(Pos.CENTER_LEFT);

        showPotions();
    }

    @Override
    public void refresh() {
        popups.forEach(PopupWindow::hide);
        getChildren().clear();
        showPotions();
    }

    private Node images(Potion potion) {
        Image imagePotion = new Image(getClass().getResource(potion.getImagePath()).toExternalForm());
        ImageView imageViewPotion = new ImageView(imagePotion);
        imageViewPotion.setPreserveRatio(true);
        setEventHandler(imageViewPotion, potion);
        return imageViewPotion;
    }

    private ImageView initEmptyPotionIcon() {
        ImageView emptyPotionIconView = new ImageView(emptyPotionIcon);
        emptyPotionIconView.setFitHeight(40);
        emptyPotionIconView.setFitWidth(40);
        return emptyPotionIconView;
    }

    private void setEventHandler(ImageView imageView, Potion potion) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setHeight(30);
        glow.setWidth(30);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> showPopup(potion, imageView));
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(glow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> imageView.setEffect(null));
    }

    private void showPopup(Potion potion, ImageView imageView) {
        Popup popup = new Popup();
        popup.setAutoHide(true);

        popups.add(popup);

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
        root.getChildren().addAll(new ImageView(bg), layout);

        popup.getContent().add(root);
        Bounds bounds = imageView.localToScreen(imageView.getBoundsInLocal());
        popup.show(imageView, bounds.getMinX() + bounds.getWidth() / 2 - popup.getWidth() / 2, bounds.getMaxY());
        popup.setOnHiding(event -> {
            popups.remove(popup);
        });
    }

    private void showPotions() {
        List<Node> nodes = new ArrayList<>();
        int i = 0;
        for (; i < potions.size(); i++) {
            nodes.add(images(potions.get(i)));
        }
        for (; i < 3; i++) {
            nodes.add(initEmptyPotionIcon());
        }
        getChildren().addAll(nodes);
    }

}
