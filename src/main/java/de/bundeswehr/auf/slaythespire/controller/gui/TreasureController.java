package de.bundeswehr.auf.slaythespire.controller.gui;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.ConsoleAssistent;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.models.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.game_settings.GameSettings;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.models.potion.potion_structure.PotionCard;
import de.bundeswehr.auf.slaythespire.gui.TreasureView;
import de.bundeswehr.auf.slaythespire.gui.layouts.layout_events.TreasureViewEvents;

import java.util.List;
import java.util.Random;

/**
 * Die Klasse TreasureController steuert den Schatz-Vorgang im Spiel und verwaltet die Anzeige der Schatz-Ansicht.
 * Sie initialisiert die möglichen Karten und deren Eigenschaften basierend auf den Spieleinstellungen
 * und dem Spielstatus, wie z.B. dem Schwierigkeitsgrad.
 *
 * @author Vladislav Keil
 */
public class TreasureController implements TreasureViewEvents {

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
        this.gold = rnd.nextInt(90 + 1 - 35) + 35;
        initItemChanceAndAmount();

        this.deckFactory = new DeckFactory(player, amount);
        generatePotionByChance();

        this.selectedCards = initTreasureDeck();

        this.treasureView = new TreasureView(this.selectedCards, this.gold, this.potionCard, player.getImagePath(), this);
        this.treasureView.initTreasureViewEvents(this);
    }

    /**
     * Gibt die Schatz-Ansicht zurück.
     *
     * @return Die TreasureView-Instanz.
     */
    public TreasureView getTreasureView() {
        return this.treasureView;
    }

    /**
     * Event-Handler für den Zurück-Klick im Schatz.
     * Kehrt zur Kartenansicht zurück.
     */
    @Override
    public void onBackClicked() {
        ConsoleAssistent.print(Color.YELLOW, "TreasureView Leaved!");
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
        addCardToDeck(this.selectedCards.get(index));
    }

    /**
     * Event-Handler für Klicks auf das Gold im Schatz.
     *
     * @param gold Die Menge an Gold, die dem Spieler gutgeschrieben wird.
     */
    @Override
    public void onGoldClick(int gold) {
        this.player.increaseGold(gold);
    }

    /**
     * Event-Handler für Klicks auf einen Trank im Schatz.
     *
     * @param potion Der angeklickte Trank.
     */
    @Override
    public void onPotionClick(PotionCard potion) {
        if (this.player.getPotionCards().size() < 3) {
            ConsoleAssistent.print(Color.YELLOW, "Got a Potion: " + potion.getName());
            this.player.addPotionCard(potion);
        }
        else {
            this.treasureView.showDialog("You have reached the maximum of Potion.");
        }
    }

    /**
     * Fügt eine Karte dem Deck des Spielers hinzu.
     *
     * @param card Die hinzuzufügende Karte.
     */
    private void addCardToDeck(Card card) {
        ConsoleAssistent.print(Color.YELLOW, "Got a Card: " + card.getName());
        ConsoleAssistent.print(Color.YELLOW, "Got Gold: " + this.gold);

        this.player.addCardToDeck(card);
        this.player.increaseGold(this.gold);
    }

    /**
     * Generiert eine Trankkarte basierend auf einer Zufallswahrscheinlichkeit.
     */
    private void generatePotionByChance() {
        double rand = rnd.nextDouble();
        if (rand < this.potionsChance) {
            this.potionCard = deckFactory.generatePotion();
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
        return this.deckFactory.init();
    }
}
