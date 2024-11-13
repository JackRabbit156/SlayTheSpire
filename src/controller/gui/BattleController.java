package controller.gui;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.BattleDeck;
import models.GameContext;

import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardType;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import view.gui.BattleView;
import view.gui.layouts.layout_events.BattleViewEvents;

import java.util.List;

public class BattleController implements BattleViewEvents {
    private BattleView battleView;

    private Player player;
    private List<Enemy> enemies;

    private BattleDeck battleDeck;
    private GameContext gameContext;

    private Card selectedCard;

    public BattleController(Player player, List<Enemy> enemies, BattleView battleView) {
        this.player = player;
        this.enemies = enemies;
        this.battleView = battleView;

        this.battleDeck = new BattleDeck(player.getDeck());
        this.gameContext = new GameContext(player, enemies, battleDeck);

        battleView.initBattleViewEvents(this);


        initController();

        startGame();
    }

    private void startGame() {
        playerBOT();
        refreshHand();
        //removeHandAfterEndOfTurn();
    }

    private void removeHandAfterEndOfTurn() {
        int size = battleDeck.getHand().size();
        for(int i = 0; i < size; i++) {
            battleDeck.discardCardFromHand(battleDeck.getHand().get(0));
        }

        System.out.println(battleDeck.getHand().size() + " size");
        refreshHand();
    }
    private void playerTurn(Card card, int index) {
        //card.play(gameContext); // Hier ist das Problem

        if(card.isTargetRequired()){
            battleView.selectEnemyView();
            System.out.println("We are here!");
            selectedCard = card;
        }
    }

    private void playerBOT() {
        battleDeck.fillHand(battleDeck.getStartHandSize());
        player.resetEnergy();
        player.resetBlock();
        //triggerCard(CardTrigger.PLAYER_BOT);
    }

    private void refreshHand(){
        battleView.setHand(battleDeck.getHand());
    }

    /**
     * Sollte auf einen Button von BattleView gedrückt werden, wird dieser Klick weiter auf einen Handler in BattleViewController geleitet.
     */
    private void initController() {
        // Register listener for the "Okay" button
        battleView.setOkayButtonHandler(this::handleOkayButton);
    }

    private void handleOkayButton(ActionEvent event) {
        // Handle the logic for when the "Okay" button is clicked
        System.out.println("Okay button clicked!");

        // Example: Modify player or enemy.png state
        // Update BattleView based on changes
        battleView.updateEnemyStatus();
    }


    @Override
    public void onCardClick(Card card, int index) {
        System.out.println("Player pressed CardClickEvent: " + card + " index " + index);
        playerTurn(card, index);
    }

    @Override
    public void onEnemyClick(Enemy enemy) {
        if(selectedCard == null)
            return;

        playCardIfPossible(enemy);

        battleView.updateEnemyStatus();
        battleView.enableBattleView();

        refreshHand();

        selectedCard = null;

        startingMap();
    }

    private void startingMap(){
        MapController controller = new MapController(player, true);

        Stage primaryStage = player.getPrimaryStage();
        Scene scene = new Scene(controller.getMapView(), 1920, 1080);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Slay the Spire - JavaFX");
        primaryStage.show();
    }

    private void playCardIfPossible(Enemy enemy) {
        if(selectedCard.getCost() > player.getCurrentEnergy()){
            System.out.println("\nNot enough Energy!");
            return;
        }

        if (selectedCard.getName().equals("Clash")) {
            List<Card> hand = gameContext.getBattleDeck().getHand();
            for (Card card : hand) {
                if (!card.getCardType().equals(CardType.ATTACK)) {
                    System.out.println("\nOnly playable if only Attack-Cards in Hand");
                    return;
                }
            }
        }

        // Play the card (and add the enemy)
        gameContext.setSelectedEnemy(enemy);
        selectedCard.play(gameContext);

        if (selectedCard.getCardGrave() == CardGrave.EXHAUST) {
            battleDeck.exhaustCardFromHand(selectedCard);
        }
        else if (selectedCard.getCardGrave() == CardGrave.DISCARD) {
            battleDeck.discardCardFromHand(selectedCard);
        }
        else {
            battleDeck.removeCardFromHand(selectedCard);
        }
    }
}
