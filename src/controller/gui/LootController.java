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

public class LootController implements LootViewEvents {
    private Random randi = new Random();

    private Player player;
    private List<Card> selectedCards;
    private PotionCard potionCard;
    private DeckFactory deckFactory;
    private LootView lootView;

    private int gold = 0;
    private int amount = 5;
    private double potionsChance = 0.8;


    /**
     * Anzahl an möglichen Karten wird initialisiert, je nach Schwierigkeit.
     * Für eine neuinitialisierung muss ein neuer LootViewCohtroller geschaffen werden.
     * @param player wird für die Loot-Deckerstellung benötigt.
     * @param fieldType Gold Faktor für den Loot
     */
    public LootController(Player player, FieldEnum fieldType) {
        this.player = player;

        initGoldLoot(fieldType);
        initItemChanceAndAmount();

        this.deckFactory = new DeckFactory(player, amount);
        generatePotionByChance();

        this.selectedCards = initialLootDeck();

        this.lootView = new LootView(this.selectedCards, this.gold, this.potionCard, this);
        this.lootView.initLootViewEvents(this);
    }

    public LootController(Player player, CardType cardType) {
        this.player = player;

        this.deckFactory = new DeckFactory(player, amount, cardType);

        this.selectedCards = initialLootDeck();

        this.lootView = new LootView(this.selectedCards, this);
        this.lootView.initLootViewEvents(this);
    }


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
        GuiHelper.Scenes.startMapScene(player, true);
    }

    /**
     * Übergabe der LootView
     * @return LootView
     */
    public LootView getLootView() {
        return this.lootView;
    }

    private List<Card> initialLootDeck() {
        return this.deckFactory.init();
    }

    private void initGoldLoot(FieldEnum fieldType) {
        // Initialisierung
        switch (fieldType) {
            case BOSSFIELD: {
                // Boss Encounter: 95-105 Gold
                this.gold = randi.nextInt(105 + 1 - 95) + 95;
                break;
            }
            case ELITEFIELD: {
                // Elite Encounter: 25-35 Gold
                this.gold = randi.nextInt(35 + 1 - 25) + 25;
                break;
            }
            case ENEMYFIELD: {
                // Normal Encounter: 10-20 Gold
                this.gold = randi.nextInt(20 + 1 - 10) + 10;
                break;
            }
        }
    }

    private void addCardToDeck(Card card) {
        ConsoleAssistent.print(Color.YELLOW, "Got an Card: " + card.getName());
        ConsoleAssistent.print(Color.YELLOW, "Got Gold: " + this.gold);

        this.player.addCardToDeck(card);
        this.player.increaseGold(this.gold);
    }
}
