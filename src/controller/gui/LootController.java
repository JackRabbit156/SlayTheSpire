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

public class LootController implements LootViewEvents {
    private Random randi = new Random();

    private Player player;
    private List<Card> selectedCards;
    private PotionCard potionCard;
    private DeckFactory deckFactory;
    private LootView lootView;
    private int gold = 0;


    /**
     * Anzahl an möglichen Karten wird initialisiert, je nach Schwierigkeit.
     * Für eine neuinitialisierung muss ein neuer LootViewCohtroller geschaffen werden.
     * @param player wird für die Loot-Deckerstellung benötigt.
     * @param fieldType Gold Faktor für den Loot
     */
    public LootController(Player player, FieldEnum fieldType) {
        this.player = player;
        int amount = 5;
        double potionsChance = 0.80;
        initGoldLoot(fieldType);

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
        this.deckFactory = new DeckFactory(player, amount);
        this.selectedCards = initialLootDeck();

        generatePotionByChance(potionsChance);

        if (this.potionCard != null) {
            this.lootView = new LootView(player, this.selectedCards, this.gold, this.potionCard, this);
        } else {
            this.lootView = new LootView(player, this.selectedCards, this.gold, this);
        }
        this.lootView.initLootViewEvents(this);
    }

    private void generatePotionByChance(double potionsChance) {
        if (randi.nextDouble() < potionsChance) {
            this.potionCard = deckFactory.generatePotion();
        }
    }

    @Override
    public void onCardClick(Card card, int index) {
        addCardToDeck(this.selectedCards.get(index - 1));
        onBackClicked();
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
        this.player.addCardToDeck(card);
        this.player.increaseGold(this.gold);
    }
}
