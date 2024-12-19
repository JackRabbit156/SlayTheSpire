package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.controller.listener.BattleDeckListener;
import de.bundeswehr.auf.slaythespire.gui.events.BattleViewEvents;
import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.gui.layouts.CardSelectionLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.BottomSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.LeftSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.PotionLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.RightSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.top_bar.TopBarLayout;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.AttackPotion;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.potion.structure.SkillPotion;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

import java.util.List;

/**
 * Diese Klasse repräsentiert die Ansicht für den Kampf im Spiel.
 * Sie ist verantwortlich für die Darstellung des Spielers, der Gegner,
 * der Lebenspunkte und der Kartenhand. Sie zeigt die verschiedenen
 * Informationen an und informiert den Spieler über
 * Kampfergebnisse, wie Angriffe, Siege oder Niederlagen.
 *
 * @author F Alexander Warawa
 * @author OF Daniel Willig
 */
public class BattleView extends BorderPane implements View, WithTopBar, BattleDeckListener {

    public enum Mode {
        NORMAL, ATTACK, SKILL, POWER
    }

    private final BattleDeck battleDeck;
    private final BattleViewEvents battleViewEvents;
    private BottomSideLayout bottom;
    private final Pane center = new Pane();
    private final List<Enemy> enemies;
    private LeftSideLayout left;
    /**
     * Ändert den aktuellen Modus der View. Z.B. wenn auf Karte gedrückt, dann Attack-Modus.
     */
    private final SimpleObjectProperty<Mode> mode = new SimpleObjectProperty<>(Mode.NORMAL);
    private final Player player;
    private RightSideLayout right;
    private TopBarLayout top;

    public BattleView(Player player, List<Enemy> enemies, BattleViewEvents battleViewEvents, BattleDeck battleDeck) {
        this.battleViewEvents = battleViewEvents;
        this.player = player;
        this.enemies = enemies;
        this.battleDeck = battleDeck;
        this.battleDeck.setBattleDeckListener(this);

        setBackground(new Background(GuiHelper.backgroundInHD(player.getActImage())));
        setCenter(center);
        initNodes();
    }

    @Override
    public void chooseCard(List<Card> cards, CardEventListener cardEventListener) {
        disableBattleView();
        CardSelectionLayout cardSelectionLayout = new CardSelectionLayout(cards, cardEventListener, this);
        cardSelectionLayout.setPadding(new Insets(50, 50, 15, 50));

        HBox centerCard = new HBox();
        centerCard.setAlignment(Pos.CENTER);
        centerCard.setTranslateX(-180);
        centerCard.getChildren().add(cardSelectionLayout);

        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(centerCard);
        cardSelectionLayout.cardClickedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                popup.hide();
            }
        });
        popup.setOnHiding(event -> {
            if (!cardSelectionLayout.isCardClicked() && cardEventListener instanceof Card) {
                ((Card) cardEventListener).cancel();
            }
            enableBattleView();
        });

        Bounds bounds = center.localToScreen(center.getBoundsInLocal());
        popup.show(center.getScene().getWindow(), bounds.getMinX() - centerCard.getBoundsInLocal().getWidth() / 2, bounds.getMinY());
    }

    public void clickedOnCard(Card card, int index) {
        if (card == null) {
            mode.set(Mode.NORMAL);
            enableBattleView();
            return;
        }
        if (mode.get() != Mode.NORMAL) {
            mode.set(Mode.NORMAL);
            enableBattleView();
        }
        if (card instanceof AttackCard) {
            mode.set(Mode.ATTACK);
            selectEnemyViewForCard();
        }
        else if (card instanceof SkillCard) {
            mode.set(Mode.SKILL);
            selectPlayerViewForCard();
        }
        else if (card instanceof PowerCard) {
            mode.set(Mode.POWER);
            selectPlayerViewForCard();
        }
        battleViewEvents.onCardClick(card, index);
    }

    public void clickedOnEndTurn() {
        battleViewEvents.onEndTurnClick();
        updateInformation();
    }

    public void clickedOnEnemy(Enemy enemy) {
        mode.set(Mode.NORMAL);
        enableBattleView();
        battleViewEvents.onEnemyClick(enemy);
        updateInformation();
    }

    public void clickedOnPlayer() {
        mode.set(Mode.NORMAL);
        enableBattleView();
        battleViewEvents.onPlayerClick();
        updateInformation();
    }

    public void clickedOnPotion(Potion potion, int index) {
        if (potion == null) {
            mode.set(Mode.NORMAL);
            enableBattleView();
            return;
        }
        if (mode.get() != Mode.NORMAL) {
            mode.set(Mode.NORMAL);
            enableBattleView();
        }
        if (potion instanceof AttackPotion) {
            mode.set(Mode.ATTACK);
            selectEnemyViewForPotion();
        }
        else if (potion instanceof SkillPotion) {
            mode.set(Mode.SKILL);
            selectPlayerViewForPotion();
        }
        battleViewEvents.onCardClick(potion, index);
    }

    public void disableBattleView() {
        top.setDisableMiddleBar(true);
        left.setDisable(true);
        right.setDisable(true);
        bottom.setDisableEndTurn(true);
        bottom.setDisable(true);
    }

    @Override
    public void discard() {
        left.discard();
        right.discard();
        top.discard();
    }

    public void enableBattleView() {
        top.setDisableMiddleBar(false);
        left.setDisable(false);
        right.setDisable(false);
        bottom.setDisableEndTurn(false);
        bottom.setDisable(false);
    }

    public Mode getMode() {
        return mode.get();
    }

    public void setMode(Mode mode) {
        this.mode.set(mode);
    }

    public SimpleObjectProperty<Mode> modeProperty() {
        return mode;
    }

    @Override
    public void onCardClick(Card card) {
        updateInformation();
        enableBattleView();
    }

    /**
     * gets called after battledeck fills the hand
     */
    @Override
    public void onCardFill() {
        updateBottom();
    }

    @Override
    public void onFullScreen() {
        battleViewEvents.onFullScreenClick();
    }

    public void selectEnemyViewForCard() {
        top.setDisableMiddleBar(true);
        left.setDisable(true);
        bottom.setDisableEndTurn(true);
    }

    public void selectEnemyViewForPotion() {
        left.setDisable(true);
        bottom.setDisable(true);
    }

    public void selectPlayerViewForCard() {
        top.setDisableMiddleBar(true);
        right.setDisable(true);
        bottom.setDisableEndTurn(true);
    }

    public void selectPlayerViewForPotion() {
        right.setDisable(true);
        bottom.setDisable(true);
    }

    /**
     * Zeigt ein Popup-Fenster mit einer Nachricht an.
     *
     * @param text Der Text im Popup-Fenster.
     */
    public void showDialog(String text) {
        Image popupImage = new Image(getClass().getResource("/images/popup/popupBg.png").toExternalForm());
        ImageView imageView = new ImageView(popupImage);
        imageView.setScaleX(0.5);
        imageView.setScaleY(0.5);

        StackPane stackPopup = new StackPane();
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setWrapText(true);
        label.setMaxWidth(400);
        label.setStyle("-fx-font-size: 36;" +
                "-fx-font-family: Kreon;");
        label.setTextFill(Color.WHITE);
        stackPopup.getChildren().addAll(imageView, label);

        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(stackPopup);
        Bounds bounds = center.localToScreen(center.getBoundsInLocal());
        popup.show(center.getScene().getWindow(), bounds.getMinX(), bounds.getMinY());
    }

    public void updateBottom() {
        bottom.update();
    }

    public void updateInformation() {
        right.update();
        bottom.update();
        top.update();
        left.update();
    }

    /**
     * Bottom side for the Hand cards
     */
    private void initBottom() {
        bottom = new BottomSideLayout(this, player, battleDeck);
        setBottom(bottom);
    }

    /**
     * Left side for the player
     */
    private void initLeft() {
        left = new LeftSideLayout(this, player);
        setLeft(left);
    }

    private void initNodes() {
        initTop();
        initLeft();
        initRight();
        initBottom();
        updateInformation();
    }

    /**
     * Right side for the enemies
     */
    private void initRight() {
        right = new RightSideLayout(this, enemies);
        setRight(right);
    }

    /**
     * Top side for the Player Information
     */
    private void initTop() {
        top = new TopBarLayout(this, player, new PotionLayout(player.getPotions(), this));
        setTop(top);
    }

}