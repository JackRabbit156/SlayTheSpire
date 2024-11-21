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
     * Anzahl an möglichen Karten wird initialisiert, je nach Schwierigkeit.
     * Für eine neuinitialisierung muss ein neuer TreasureController geschaffen werden.
     * @param player wird für die Loot-Deckerstellung benötigt.
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
            this.treasureView.showDialog("You have reached the maximum of Potion.");
        }
    }

    @Override
    public void onBackClicked() {
        ConsoleAssistent.print(Color.YELLOW, "TreasureView Leaved!");
        GuiHelper.Scenes.startMapScene(player, true);
    }

    /**
     * Übergabe der TreasureView
     * @return TreasureView
     */
    public TreasureView getTreasureView() {
        return this.treasureView;
    }

    private List<Card> initTreasureDeck() {
        return this.deckFactory.init();
    }

    private void addCardToDeck(Card card) {
        ConsoleAssistent.print(Color.YELLOW, "Got an Card: " + card.getName());
        ConsoleAssistent.print(Color.YELLOW, "Got Gold: " + this.gold);

        this.player.addCardToDeck(card);
        this.player.increaseGold(this.gold);
    }
}
