package view.gui.layouts.battle_view_layouts;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import models.potion.potion_structure.PotionCard;
import view.gui.BattleView;

import java.util.ArrayList;
import java.util.List;


/**
 * Das Potion layout.
 * bis zu drei kleine Icons rechts der Info; wird nur angezeigt, wenn auch Potions im Inventar sind.
 * Sind anklickbar.
 *
 * @author OF Daniel Willig
 */
public class PotionLayout extends HBox {

    private static final Font largeFont = Font.font("Kreon", FontWeight.BOLD, 44);
    private static final Font smallFont = Font.font("Kreon", FontWeight.BOLD, 20);

    private final BattleView battleView;
    private final Image bg = new Image(getClass().getResource("/images/view/gui/layouts/battle_view_layouts/potion_layout/bg.png").toExternalForm());
    private final Image emptyPotionIcon = new Image(getClass().getResource("/images/view/gui/layouts/battle_view_layouts/potion_layout/EmptyPotion.png").toExternalForm());
    private final List<PotionCard> potions;
    private final ObjectProperty<PotionCard> selected = new SimpleObjectProperty<>();

    public PotionLayout(List<PotionCard> potions, BattleView battleView) {
        this.potions = potions;
        this.battleView = battleView;

        showPotions();
        setTranslateY(-35);
        setTranslateX(130);
    }

    public void handlePotionClick(PotionCard potion, int index) {
        selected.set(potion);
        battleView.clickedOnPotion(potion, index);
    }

    public void refreshPotions() {
        this.getChildren().clear();
        showPotions();
        selected.set(null);
    }

    private Node images(PotionCard potion) {
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

    private void setEventHandler(ImageView imageView, PotionCard potion) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setHeight(30);
        glow.setWidth(30);
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            showPopup(potion, imageView);
            handlePotionClick(potion, potions.indexOf(potion));
        });
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(glow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            if (selected.get() != potion) {
                imageView.setEffect(null);
            }
        });
        selected.addListener((observable, oldValue, newValue) -> {
            if (oldValue == potion && newValue != potion) {
                imageView.setEffect(null);
            }
        });


    }

    private void showPopup(PotionCard potion, ImageView imageView) {
        Popup popup = new Popup();
        popup.setAutoHide(true);

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
        layout.setPadding(new Insets(110, 0, 0, 70));
        layout.setSpacing(50);

        StackPane root = new StackPane();
        root.getChildren().addAll(new ImageView(bg), layout);

        popup.getContent().add(root);
        Bounds bounds = imageView.localToScreen(imageView.getBoundsInLocal());
        popup.show(imageView, bounds.getMinX() + bounds.getWidth() / 2 - popup.getWidth() / 2, bounds.getMaxY());
        popup.setOnHiding(event -> {
            battleView.updateInformation();
            battleView.clickedOnPotion(null, -1);
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
