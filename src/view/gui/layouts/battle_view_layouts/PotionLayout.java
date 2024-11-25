package view.gui.layouts.battle_view_layouts;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import models.card.card_structure.Card;
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
    private final List<PotionCard> potions;
    private final BattleView battleView;

    private final Image emptyPotionIcon = new Image(getClass().getResource("/images/view/gui/layouts/battle_view_layouts/potion_layout/EmptyPotion.png").toExternalForm());
    private ImageView emptyPotionIconView = new ImageView(emptyPotionIcon);

    public PotionLayout(List<PotionCard> potions, BattleView battleView) {
        this.potions = potions;
        this.battleView = battleView;

        showPotions();
        setTranslateY(-35);
        setTranslateX(130);
    }

    private void initEmptyPotionIcon() {
        emptyPotionIconView.setFitHeight(40);
        emptyPotionIconView.setFitWidth(40);
    }

    public void refreshPotions() {
        this.getChildren().clear();
        showPotions();
    }

    private void showPotions(){
        List<Node> nodes = new ArrayList<>();

        for (PotionCard potion : potions) {
            nodes.add(images(potion));
        }

        getChildren().addAll(nodes);
    }

    private Node images(PotionCard potion) {
        Image imagePotion = new Image(getClass().getResource(potion.getImagePath()).toExternalForm());
        ImageView imageViewPotion = new ImageView(imagePotion);
        imageViewPotion.setPreserveRatio(true);


        setHoverEffect(imageViewPotion);

        imageViewPotion.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handlePotionClick(potion, potions.indexOf(potion)));
        return imageViewPotion;
    }

    private void setHoverEffect(ImageView imageView) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.YELLOW);
        glow.setHeight(30);
        glow.setWidth(30);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(glow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> imageView.setEffect(null));
    }

    public void handlePotionClick(PotionCard potion, int index) {
        battleView.clickedOnPotion(potion, index);
    }
}
