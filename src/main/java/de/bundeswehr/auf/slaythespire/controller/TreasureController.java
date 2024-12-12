package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.TreasureView;
import de.bundeswehr.auf.slaythespire.gui.events.TreasureViewEvents;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.ConsoleAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.PotionCard;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

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
    private final DeckFactory deckFactory;
    private int gold;
    private final Player player;
    private PotionCard potionCard;
    private double potionsChance;
    private final List<Card> selectedCards;
    private final TreasureView treasureView;

    /**
     * Konstruktor für die Klasse TreasureController.
     * Initialisiert den Schatz-Vorgang basierend auf dem Spieler und den Spieleinstellungen.
     *
     * @param player Der Spieler, für den der Schatz erstellt wird.
     */
    public TreasureController(Player player) {
        this.player = player;
        // 35 - 90
        gold = rnd.nextInt(90 + 1 - 35) + 35;
        initItemChanceAndAmount();

        deckFactory = new DeckFactory(player, amount);
        generatePotionByChance();

        selectedCards = initTreasureDeck();

        treasureView = new TreasureView(selectedCards, gold, potionCard, player.getImagePath(), this);
        treasureView.initTreasureViewEvents(this);
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
        ConsoleAssistant.log("TreasureView closed");
        GuiHelper.Scenes.startMapScene(player);
    }

    /**
     * Event-Handler für Klicks auf eine Karte im Schatz.
     *
     * @param card  Die angeklickte Karte.
     * @param index Der Index der angeklickten Karte.
     */
    @Override
    public void onCardClick(Card card, int index) {
        addCardToDeck(selectedCards.get(index));
    }

    /**
     * Event-Handler für Klicks auf das Gold im Schatz.
     *
     * @param gold Die Menge an Gold, die dem Spieler gutgeschrieben wird.
     */
    @Override
    public void onGoldClick(int gold) {
        player.increaseGold(gold);
    }

    /**
     * Event-Handler für Klicks auf einen Trank im Schatz.
     *
     * @param potion Der angeklickte Trank.
     */
    @Override
    public void onPotionClick(PotionCard potion) {
        if (player.getPotionCards().size() < 3) {
            ConsoleAssistant.log("Got a potion: " + potion.getName());
            player.addPotionCard(potion);
        }
        else {
            ConsoleAssistant.log(Color.YELLOW, "Maximum number of potions reached");
            treasureView.showDialog("You have reached the maximum number of Potion.");
        }
    }

    /**
     * Fügt eine Karte dem Deck des Spielers hinzu.
     *
     * @param card Die hinzuzufügende Karte.
     */
    private void addCardToDeck(Card card) {
        ConsoleAssistant.log("Got a card: " + card.getName());
        player.addCardToDeck(card);
        ConsoleAssistant.log("Got gold: " + gold);
        player.increaseGold(gold);
    }

    /**
     * Generiert eine Trankkarte basierend auf einer Zufallswahrscheinlichkeit.
     */
    private void generatePotionByChance() {
        if (rnd.nextDouble() < potionsChance) {
            potionCard = deckFactory.generatePotion();
        }
    }

    /**
     * Initialisiert die Chancen für Items und die Anzahl an möglichen Karten basierend auf dem Schwierigkeitsgrad.
     */
    private void initItemChanceAndAmount() {
        gold = GameSettings.getDifficultyLevel().getGold(gold);
        amount = GameSettings.getDifficultyLevel().getAmount();
        potionsChance = GameSettings.getDifficultyLevel().getPotionChance();
    }

    /**
     * Initialisiert das Schatz-Deck mit Karten.
     *
     * @return Die Liste der initialisierten Karten.
     */
    private List<Card> initTreasureDeck() {
        return deckFactory.init();
    }

}
