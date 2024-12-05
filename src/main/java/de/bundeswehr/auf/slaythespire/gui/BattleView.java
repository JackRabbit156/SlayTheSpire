package de.bundeswehr.auf.slaythespire.gui;

import de.bundeswehr.auf.slaythespire.controller.listener.BattleDeckListener;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import de.bundeswehr.auf.slaythespire.models.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardType;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.BottomSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.LeftSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.RightSideLayout;
import de.bundeswehr.auf.slaythespire.gui.layouts.battle.TopSideLayout;
import de.bundeswehr.auf.slaythespire.gui.events.BattleViewEvents;

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
public class BattleView extends BorderPane implements BattleDeckListener {

    public enum Mode {
        NORMAL, ATTACK, SKILL, POWER
    }

    private final BattleDeck battleDeck;
    private final BattleViewEvents battleViewEvents;
    private BottomSideLayout bottom;
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

    /**
     * gets called after battledeck fills the hand
     */
    @Override
    public void onCardFill() {
        updateBottom();
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