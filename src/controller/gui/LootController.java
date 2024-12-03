package controller.gui;

import helper.Color;
import helper.ConsoleAssistent;
import helper.GuiHelper;
import models.card.DeckFactory;
import models.card.card_structure.Card;
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

    private static final Random rnd = new Random();

    private int amount;
    private final DeckFactory deckFactory;
    private final FieldEnum fieldType;
    private int gold;
    private final LootView lootView;
    private final Player player;
    private PotionCard potionCard;
    private double potionsChance;
    private final List<Card> selectedCards;

    /**
     * Konstruktor für die Klasse LootController.
     * Initialisiert die mögliche Anzahl an Karten und deren Eigenschaften basierend auf dem
     * Schwierigkeitsgrad und dem Typ des Feldes.
     *
     * @param player    Der Spieler, für den das Loot erstellt wird.
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

        this.lootView = new LootView(this.selectedCards, this.gold, player.getImagePath(), this.potionCard, this);

        this.lootView.initTreasureViewEvents(this);
    }

    /**
     * Gibt die Loot-Ansicht zurück.
     *
     * @return Die LootView-Instanz.
     */
    public LootView getLootView() {
        return this.lootView;
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
        }
        else {
            this.lootView.showDialog("You have reached the maximum of Potion.");
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
     * Initialisiert die Goldmenge basierend auf dem Feldtyp.
     *
     * @param fieldType Der Typ des Feldes, das den Goldfaktor bestimmt.
     */
    private void initGoldLoot(FieldEnum fieldType) {
        switch (fieldType) {
            case BOSSFIELD:
                // 95 - 105
                this.gold = rnd.nextInt(105 + 1 - 95) + 95;
                break;
            case ELITEFIELD:
                // 25 - 35
                this.gold = rnd.nextInt(35 + 1 - 25) + 25;
                break;
            case ENEMYFIELD:
                // 10 - 20
                this.gold = rnd.nextInt(20 + 1 - 10) + 10;
                break;
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
     * Initialisiert das Loot-Deck mit Karten.
     *
     * @return Die Liste der initialisierten Karten.
     */
    private List<Card> initialLootDeck() {
        return this.deckFactory.init();
    }
}
