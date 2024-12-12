package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.controller.listener.BattleDeckListener;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.BottomSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.LeftSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.RightSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.TopSideLayout;
import de.bundeswehr.auf.slaythespire.gui.events.BattleViewEvents;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
public class BattleView extends BorderPane implements View, BattleDeckListener {

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
    private TopSideLayout top;

    public BattleView(Player player, List<Enemy> enemies, BattleViewEvents battleViewEvents, BattleDeck battleDeck) {
        this.battleViewEvents = battleViewEvents;
        this.player = player;
        this.enemies = enemies;
        this.battleDeck = battleDeck;
        this.battleDeck.setBattleDeckListener(this);

        setBackground(new Background(GuiHelper.background("/images/act1.png")));
        setCenter(center);
        initNodes();
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
        if (card.getCardType() == CardType.ATTACK) {
            mode.set(Mode.ATTACK);
            selectEnemyViewForCard();
        }
        else if (card.getCardType() == CardType.SKILL) {
            mode.set(Mode.SKILL);
            selectPlayerViewForCard();
        }
        else if (card.getCardType() == CardType.POWER) {
            mode.set(Mode.POWER);
            selectPlayerViewForCard();
        }
        battleViewEvents.onCardClick(card, index);
        // TODO ist das für irgendetwas nötig?
//        updateInformation();
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

    public void clickedOnFullscreen() {
        battleViewEvents.onFullscreenClick();
    }

    public void clickedOnPlayer() {
        mode.set(Mode.NORMAL);
        enableBattleView();
        battleViewEvents.onPlayerClick();
        updateInformation();
    }

    public void clickedOnPotion(PotionCard potion, int index) {
        if (potion == null) {
            mode.set(Mode.NORMAL);
            enableBattleView();
            return;
        }
        if (mode.get() != Mode.NORMAL) {
            mode.set(Mode.NORMAL);
            enableBattleView();
        }
        if (potion.getCardType() == CardType.ATTACK) {
            mode.set(Mode.ATTACK);
            selectEnemyViewForPotion();
        }
        else if (potion.getCardType() == CardType.SKILL) {
            mode.set(Mode.SKILL);
            selectPlayerViewForPotion();
        }
        battleViewEvents.onCardClick(potion, index);
        // TODO notwendig?
//        updateInformation();
    }

    public void enableBattleView() {
        top.setDisablePotions(false);
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
    public void discard() {
        left.discard();
        right.discard();
    }

    /**
     * gets called after battledeck fills the hand
     */
    @Override
    public void onCardFill() {
        updateBottom();
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

    public void selectEnemyViewForCard() {
        top.setDisablePotions(true);
        left.setDisable(true);
        bottom.setDisableEndTurn(true);
    }

    public void selectEnemyViewForPotion() {
        left.setDisable(true);
        bottom.setDisable(true);
    }

    public void selectPlayerViewForCard() {
        top.setDisablePotions(true);
        right.setDisable(true);
        bottom.setDisableEndTurn(true);
    }

    public void selectPlayerViewForPotion() {
        right.setDisable(true);
        bottom.setDisable(true);
    }

    public void updateBottom() {
        bottom.update();
    }

    public void updateInformation() {
        right.update();
        // TODO doppelt nötig?
//        leftVBox.updatePlayer();
        bottom.update();
        top.update();
        left.update();
    }

    /**
     * Bottom side for the Hand cards
     */
    private void initBottom() {
        bottom = new BottomSideLayout(this, player, battleDeck.getHand());
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

        left.setTranslateY(-50);
        right.setTranslateY(-50);
        bottom.setTranslateY(-50);

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
        top = new TopSideLayout(this, player);
        setTop(top);
    }

}