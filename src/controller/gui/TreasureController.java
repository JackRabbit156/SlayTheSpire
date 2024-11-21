package controller.gui;

import helper.Color;
import helper.ConsoleAssistent;
import helper.GuiHelper;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.game_settings.GameSettings;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
import view.gui.TreasureView;
import view.gui.layouts.layout_events.TreasureViewEvents;

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
    private Random randi = new Random();
    private Player player;
    private List<Card> selectedCards;
    private PotionCard potionCard;
    private DeckFactory deckFactory;
    private TreasureView treasureView;

    private int gold = 0;
    private int amount = 5;
    private double potionsChance = 0.8;

    /**
     * Konstruktor für die Klasse TreasureController.
     * Initialisiert den Schatz-Vorgang basierend auf dem Spieler und den Spieleinstellungen.
     *
     * @param player Der Spieler, für den der Schatz erstellt wird.
     */
    public TreasureController(Player player) {
        this.player = player;
        this.gold = randi.nextInt(80 + 1 - 25) + 35;
        initItemChanceAndAmount();

        this.deckFactory = new DeckFactory(player, amount);
        generatePotionByChance();

        this.selectedCards = initTreasureDeck();

        this.treasureView = new TreasureView(this.selectedCards, this.gold, this.potionCard, this);
        this.treasureView.initTreasureViewEvents(this);
    }

    /**
     * Initialisiert die Chancen für Items und die Anzahl an möglichen Karten basierend auf dem Schwierigkeitsgrad.
     */
    private void initItemChanceAndAmount() {
        switch (GameSettings.getDifficultyLevel()) {
            case SUPEREASY:
            case EASY:
                this.gold = (int) (this.gold * 1.5);
                break;
            case NORMAL:
                this.amount = 3;
                this.potionsChance = 0.50;
                break;
            case IMPOSSIBLE:
            case HARD:
                this.potionsChance = 0.10;
                this.amount = 1;
                this.gold = (int) (this.gold * 0.5);
                break;
        }
    }

    /**
     * Generiert eine Trankkarte basierend auf einer Zufallswahrscheinlichkeit.
     */
    private void generatePotionByChance() {
        double rand = randi.nextDouble();
        if (rand < this.potionsChance) {
            this.potionCard = deckFactory.generatePotion();
        }
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
        } else {
            this.treasureView.showDialog("You have reached the maximum of Potion.");
        }
    }

    /**
     * Event-Handler für den Zurück-Klick im Schatz.
     * Kehrt zur Kartenansicht zurück.
     */
    @Override
    public void onBackClicked() {
        ConsoleAssistent.print(Color.YELLOW, "TreasureView Leaved!");
        GuiHelper.Scenes.startMapScene(player, true);
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
     * Initialisiert das Schatz-Deck mit Karten.
     *
     * @return Die Liste der initialisierten Karten.
     */
    private List<Card> initTreasureDeck() {
        return this.deckFactory.init();
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
}
