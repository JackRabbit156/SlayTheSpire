package controller.gui;

import helper.Color;
import helper.ConsoleAssistent;
import helper.GuiHelper;
import models.card.DeckFactory;
import models.card.card_structure.Card;
import models.card.card_structure.CardType;
import models.game_settings.GameSettings;
import models.map_elements.field_types.FieldEnum;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;
import view.gui.LootView;
import view.gui.layouts.layout_events.LootViewEvents;

import java.util.List;
import java.util.Random;

/**
 * Die Klasse LootController steuert den Loot-Vorgang im Spiel und verwaltet die Anzeige der Loot-Ansicht.
 * Sie initialisiert die möglichen Karten und deren Eigenschaften basierend auf den Spieleinstellungen
 * und dem Spielstatus, wie z.B. dem Schwierigkeitsgrad und dem Feldtyp.
 *
 * @author Keil, Vladislav
 */
public class LootController implements LootViewEvents {

    private Random randi = new Random();
    private Player player;
    private List<Card> selectedCards;
    private PotionCard potionCard;
    private DeckFactory deckFactory;
    private LootView lootView;
    private FieldEnum fieldType;

    private int gold = 0;
    private int amount = 5;
    private double potionsChance = 0.8;

    /**
     * Konstruktor für die Klasse LootController.
     * Initialisiert die mögliche Anzahl an Karten und deren Eigenschaften basierend auf dem
     * Schwierigkeitsgrad und dem Typ des Feldes.
     *
     * @param player Der Spieler, für den das Loot erstellt wird.
     * @param fieldType Der Typ des Feldes, das den Goldfaktor für den Loot beeinflusst.
     */
    public LootController(Player player, FieldEnum fieldType) {
        this.player = player;
        this.fieldType = fieldType;

        initGoldLoot(fieldType);
        initItemChanceAndAmount();

        this.deckFactory = new DeckFactory(player, amount);
        generatePotionByChance();

        this.selectedCards = initialLootDeck();

        this.lootView = new LootView(this.selectedCards, this.gold, this.potionCard, this);
        this.lootView.initTreasureViewEvents(this);
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
                amount = 3;
                potionsChance = 0.50;
                break;
            case IMPOSSIBLE:
            case HARD:
                potionsChance = 0.10;
                amount = 1;
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

    @Override
    public void onCardClick(Card card, int index) {
        addCardToDeck(this.selectedCards.get(index));
    }

    @Override
    public void onGoldClick(int gold) {
        this.player.increaseGold(gold);
    }

    @Override
    public void onPotionClick(PotionCard potion) {
        if (this.player.getPotionCards().size() < 3) {
            ConsoleAssistent.print(Color.YELLOW, "Got an Potion: " + potion.getName());
            this.player.addPotionCard(potion);
        } else {
            this.lootView.showDialog("You have reached the maximum of Potion.");
        }
    }

    @Override
    public void onBackClicked() {
        ConsoleAssistent.print(Color.YELLOW, "LootView Leaved!");
        if (this.fieldType == FieldEnum.BOSSFIELD) {
            GuiHelper.Scenes.startStatisticScene(player);
            return;
        }
        GuiHelper.Scenes.startMapScene(player);
    }

    /**
     * Gibt die Loot-Ansicht zurück.
     *
     * @return Die LootView-Instanz.
     */
    public LootView getLootView() {
        return this.lootView;
    }

    /**
     * Initialisiert das Loot-Deck mit Karten.
     *
     * @return Die Liste der initialisierten Karten.
     */
    private List<Card> initialLootDeck() {
        return this.deckFactory.init();
    }

    /**
     * Initialisiert die Goldmenge basierend auf dem Feldtyp.
     *
     * @param fieldType Der Typ des Feldes, das den Goldfaktor bestimmt.
     */
    private void initGoldLoot(FieldEnum fieldType) {
        switch (fieldType) {
            case BOSSFIELD:
                this.gold = randi.nextInt(105 + 1 - 95) + 95;
                break;
            case ELITEFIELD:
                this.gold = randi.nextInt(35 + 1 - 25) + 25;
                break;
            case ENEMYFIELD:
                this.gold = randi.nextInt(20 + 1 - 10) + 10;
                break;
        }
    }

    /**
     * Fügt eine Karte dem Deck des Spielers hinzu.
     *
     * @param card Die hinzuzufügende Karte.
     */
    private void addCardToDeck(Card card) {
        ConsoleAssistent.print(Color.YELLOW, "Got an Card: " + card.getName());
        ConsoleAssistent.print(Color.YELLOW, "Got Gold: " + this.gold);

        this.player.addCardToDeck(card);
        this.player.increaseGold(this.gold);
    }
}
