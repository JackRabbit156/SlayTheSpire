package view.gui;

import controller.listener.BattleDeckListener;
import helper.GuiHelper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import models.battle.BattleDeck;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardType;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import view.gui.layouts.battle_view_layouts.*;
import javafx.scene.control.Button;
import view.gui.layouts.layout_events.BatteViewEvents;

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
        NORMAL, ATTACK
    }
    public SimpleObjectProperty<Mode> modeProperty() {
        return mode;
    }
    public Mode getMode() {
        return mode.get();
    }
    public void setMode(Mode mode) {
        this.mode.set(mode);
    }

    private LeftSideLayout leftVBox;
    private RightSideLayout rightVBox;

    private BottomSideLayout bottomHBox;

    private HBox topHBox; // Die obere HBox;
    private Label information;
    private Button openGameMenuButton;

    private Player player;
    private List<Enemy> enemies;

    private BattleDeck battleDeck;

    private BatteViewEvents batteViewEvents;

    // Ändert den aktuellen Modus des Views. Z.B. wenn auf Karte gedrückt, dann Attack-Modus.
    private SimpleObjectProperty<Mode> mode = new SimpleObjectProperty<>(Mode.NORMAL);

    public BattleView(Player player, List<Enemy> enemies, BatteViewEvents batteViewEvents, BattleDeck battleDeck) {
        this.batteViewEvents = batteViewEvents;
        this.player = player;
        this.enemies = enemies;
        this.battleDeck = battleDeck;
        this.battleDeck.setBattleDeckListener(this);

        setBackground(new Background(GuiHelper.background("/images/act1.png")));
        initNodes();
    }

    private void initNodes() {
        initTop();
        initLeft();
        initRight();
        initBottom();
    }

    /**
     * Top side for the Player Information
     */
    private void initTop() {
        topHBox = new HBox(50);
        topHBox.setStyle("-fx-background-color: rgba(34, 34, 34, 0.8);");
        topHBox.setPrefHeight(50);

        openGameMenuButton = new Button();
        openGameMenuButton.setText("Game Menu");

        information = new Label("Some Information about the player");

        topHBox.getChildren().addAll(openGameMenuButton, information);
        this.setTop(topHBox);
    }

    /**
     * Right side for the enemies
     */
    private void initRight(){
        rightVBox = new RightSideLayout(this, enemies);

        leftVBox.setAlignment(Pos.BOTTOM_LEFT);
        this.setRight(rightVBox);
    }

    /**
     * Left side for the player
     */
    private void initLeft(){
        leftVBox = new LeftSideLayout(player);

        this.setLeft(leftVBox);
    }

    /**
     * Bottom side for the Hand cards
     */
    private void initBottom(){
        bottomHBox = new BottomSideLayout(this, player, battleDeck.getHand());
        setBottom(bottomHBox);
    }

    public void clickedOnEndTurn(){
        batteViewEvents.onEndTurnClick();
        updateInformation();
    }

    public void clickedOnCard(Card card, int index){
        if(card.getCardType() == CardType.ATTACK){
            mode.set(Mode.ATTACK);
            selectEnemyView();
        }

        batteViewEvents.onCardClick(card, index);

        updateInformation();
    }

    public void clickedOnEnemy(Enemy enemy){
        mode.set(Mode.NORMAL);
        enableBatteView();

        batteViewEvents.onEnemyClick(enemy);

        updateInformation();
    }

    /**
     * gets called after battledeck fills the hand
     */
    @Override
    public void onCardFill() {
        updateBottom();
    }

    public void updateInformation(){
        rightVBox.refreshRightSide();
        bottomHBox.updateBottom();
        leftVBox.updatePlayer();
    }

    public void selectEnemyView(){
        topHBox.setDisable(true);
        leftVBox.setDisable(true);
        bottomHBox.setDisable(true);
    }

    public void enableBatteView(){
        topHBox.setDisable(false);
        leftVBox.setDisable(false);
        bottomHBox.setDisable(false);
    }

    public void updatePlayer(){
        leftVBox.updatePlayer();
    }

    public void updateBottom() {
        bottomHBox.updateBottom();
    }
}