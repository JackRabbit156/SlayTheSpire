package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.TreasureView;
import de.bundeswehr.auf.slaythespire.gui.events.TreasureViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.PotionFactory;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.model.relic.RelicFactory;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

/**
 * Die Klasse TreasureController steuert den Schatz-Vorgang im Spiel und verwaltet die Anzeige der Schatz-Ansicht.
 * Sie initialisiert die möglichen Karten und deren Eigenschaften basierend auf den Spieleinstellungen
 * und dem Spielstatus, wie z.B. dem Schwierigkeitsgrad.
 *
 * @author Vladislav Keil
 */
public class TreasureController implements Controller, TreasureViewEvents {

    private static final Random rnd = new Random();

    private int amount;
    private int gold;
    private final Player player;
    private Potion potion;
    private Relic relic;
    private final RelicFactory relicFactory;
    private double potionsChance;
    private double relicsChance;
    private final TreasureView treasureView;

    /**
     * Konstruktor für die Klasse TreasureController.
     * Initialisiert den Schatz-Vorgang basierend auf dem Spieler und den Spieleinstellungen.
     *
     * @param player Der Spieler, für den der Schatz erstellt wird.
     */
    public TreasureController(Player player) {
        this.player = player;
        initItemChanceAndAmount();
        List<Card> selectedCards = initTreasureDeck(player);
        generatePotionByChance();
        relicFactory = new RelicFactory(player);
        generateRelicByChance();

        treasureView = new TreasureView(selectedCards, gold, potion, relic, player, this);
    }

    @Override
    public void discard() {
        treasureView.discard();
    }

    /**
     * Gibt die Schatz-Ansicht zurück.
     *
     * @return Die TreasureView-Instanz.
     */
    public TreasureView getTreasureView() {
        return treasureView;
    }

    /**
     * Event-Handler für den Zurück-Klick im Schatz.
     * Kehrt zur Kartenansicht zurück.
     */
    @Override
    public void onBackClicked() {
        LoggingAssistant.log("TreasureView closed");
        GuiHelper.Scenes.startMapScene(player);
    }

    /**
     * Event-Handler für Klicks auf eine Karte im Schatz.
     *
     * @param card  Die angeklickte Karte.
     */
    @Override
    public void onCardClick(Card card) {
        addCardToDeck(card);
    }

    @Override
    public void onFullScreenClicked() {
        Stage primaryStage = player.getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    /**
     * Event-Handler für Klicks auf das Gold im Schatz.
     *
     * @param gold Die Menge an Gold, die dem Spieler gutgeschrieben wird.
     */
    @Override
    public void onGoldClick(int gold) {
        LoggingAssistant.log("Got gold: " + gold, Color.GREEN);
        player.gainGold(gold);
    }

    /**
     * Event-Handler für Klicks auf einen Trank im Schatz.
     *
     * @param potion Der angeklickte Trank.
     */
    @Override
    public void onPotionClick(Potion potion) {
        if (player.getPotions().size() < 3) {
            LoggingAssistant.log("Got a potion: " + potion.getName(), Color.GREEN);
            player.addPotion(potion);
        }
        else {
            LoggingAssistant.log("Maximum amount of potions", Color.YELLOW);
            treasureView.showDialog("You have reached the maximum amount of Potions.");
        }
    }

    /**
     * Event-Handler für Klicks auf ein Relikt im Schatz.
     *
     * @param relic Der angeklickte Relikt.
     */
    @Override
    public void onRelicClick(Relic relic) {
        LoggingAssistant.log("Got a relic: " + relic.getName(), Color.GREEN);
        player.addRelic(relic);
    }

    /**
     * Fügt eine Karte dem Deck des Spielers hinzu.
     *
     * @param card Die hinzuzufügende Karte.
     */
    private void addCardToDeck(Card card) {
        LoggingAssistant.log("Got a card: " + card.getName(), Color.GREEN);
        player.addCardToDeck(card);
    }

    /**
     * Generiert eine Trankkarte basierend auf einer Zufallswahrscheinlichkeit.
     */
    private void generatePotionByChance() {
        if (rnd.nextDouble() < potionsChance) {
            potion = PotionFactory.generatePotion();
        }
    }

    /**
     * Generiert ein Relikt basierend auf einer Zufallswahrscheinlichkeit.
     */
    private void generateRelicByChance() {
        if (rnd.nextDouble() < relicsChance) {
            relic = relicFactory.generateRelicForTreasure();
        }
    }

    /**
     * Initialisiert die Chancen für Items und die Anzahl an möglichen Karten basierend auf dem Schwierigkeitsgrad.
     */
    private void initItemChanceAndAmount() {
        // Ausgangswert: 35 - 90
        gold = GameSettings.getDifficultyLevel().modifyGold(rnd.nextInt(90 + 1 - 35) + 35);
        amount = GameSettings.getDifficultyLevel().getCardAmount();
        potionsChance = GameSettings.getDifficultyLevel().getPotionChance();
        relicsChance = GameSettings.getDifficultyLevel().getPotionChance();
    }

    /**
     * Initialisiert das Schatz-Deck mit Karten.
     *
     * @return Die Liste der initialisierten Karten.
     * @param player Der Spieler
     */
    private List<Card> initTreasureDeck(Player player) {
        DeckFactory deckFactory = new DeckFactory(player, amount);
        return deckFactory.init(false);
    }

}
