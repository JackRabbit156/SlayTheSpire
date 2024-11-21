package view.gui;

import controller.listener.BattleDeckListener;
import helper.GuiHelper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import models.battle.BattleDeck;
import models.card.card_structure.Card;
import models.card.card_structure.CardType;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
import view.gui.layouts.battle_view_layouts.*;
import javafx.scene.control.Button;
import view.gui.layouts.layout_events.BattleViewEvents;

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
        NORMAL, ATTACK, SKILL
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


    private TopSideLayout topVBox;


    private Label information;
    private Button openGameMenuButton;

    private Player player;
    private List<Enemy> enemies;

    private BattleDeck battleDeck;

    private BattleViewEvents battleViewEvents;

    // Ändert den aktuellen Modus des Views. Z.B. wenn auf Karte gedrückt, dann Attack-Modus.
    private SimpleObjectProperty<Mode> mode = new SimpleObjectProperty<>(Mode.NORMAL);

    public BattleView(Player player, List<Enemy> enemies, BattleViewEvents battleViewEvents, BattleDeck battleDeck) {
        this.battleViewEvents = battleViewEvents;
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

        leftVBox.setTranslateY(-50);
        rightVBox.setTranslateY(-50);
        bottomHBox.setTranslateY(-50);

        updateInformation();
    }

    /**
     * Top side for the Player Information
     */
    private void initTop() {
        topVBox = new TopSideLayout(this, player);
        this.setTop(topVBox);
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
        leftVBox = new LeftSideLayout(this, player);

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
        battleViewEvents.onEndTurnClick();
        updateInformation();
    }

    public void clickedOnCard(Card card, int index){
        if(card.getCardType() == CardType.ATTACK){
            mode.set(Mode.ATTACK);
            selectEnemyView();
        }
        else if (card.getCardType() == CardType.SKILL) {
            mode.set(Mode.SKILL);
            selectPlayerView();
        }

        battleViewEvents.onCardClick(card, index);

        updateInformation();
    }

    public void clickedOnPotion(PotionCard potion, int index){
        if(potion.getCardType() == CardType.ATTACK){
            mode.set(Mode.ATTACK);
            selectEnemyView();
        }
        else if (potion.getCardType() == CardType.SKILL) {
            mode.set(Mode.SKILL);
            selectPlayerView();
        }

        battleViewEvents.onCardClick(potion, index);

        updateInformation();
    }

    public void clickedOnEnemy(Enemy enemy){
        mode.set(Mode.NORMAL);
        enableBattleView();

        battleViewEvents.onEnemyClick(enemy);

        updateInformation();
    }

    public void clickedOnPlayer(){
        mode.set(Mode.NORMAL);
        enableBattleView();

        battleViewEvents.onPlayerClick();

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
        leftVBox.updatePlayer();
        bottomHBox.updateBottom();
        topVBox.updateTop();
        leftVBox.updatePlayer();
    }

    public void selectEnemyView(){
        topVBox.setDisable(true);
        leftVBox.setDisable(true);
        bottomHBox.setDisable(true);
    }

    public void selectPlayerView(){
        topVBox.setDisable(true);
        rightVBox.setDisable(true);
        bottomHBox.setDisable(true);
    }

    public void enableBattleView(){
        topVBox.setDisable(false);
        leftVBox.setDisable(false);
        rightVBox.setDisable(false);
        bottomHBox.setDisable(false);
    }

    public void updatePlayer(){
        leftVBox.updatePlayer();
    }

    public void updateBottom() {
        bottomHBox.updateBottom();
    }
}