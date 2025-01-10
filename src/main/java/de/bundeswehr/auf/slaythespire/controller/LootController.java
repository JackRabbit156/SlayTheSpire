package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.GuiHelper;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import de.bundeswehr.auf.slaythespire.model.map.field.FieldEnum;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;
import de.bundeswehr.auf.slaythespire.gui.LootView;
import de.bundeswehr.auf.slaythespire.gui.events.LootViewEvents;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

/**
 * Die Klasse LootController steuert den Loot-Vorgang im Spiel und verwaltet die Anzeige der Loot-Ansicht.
 * Sie initialisiert die möglichen Karten und deren Eigenschaften basierend auf den Spieleinstellungen
 * und dem Spielstatus, wie z.B. dem Schwierigkeitsgrad und dem Feldtyp.
 *
 * @author Keil, Vladislav
 */
public class LootController implements Controller, LootViewEvents {

    private static final Random rnd = new Random();

    private int amount;
    private final DeckFactory deckFactory;
    private final FieldEnum fieldType;
    private int gold;
    private final LootView lootView;
    private final Player player;
    private Potion potion;
    private double potionsChance;

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

        deckFactory = new DeckFactory(player, amount);
        generatePotionByChance();

        List<Card> selectedCards = initialLootDeck();

        lootView = new LootView(selectedCards, gold, potion, player, this);
    }

    @Override
    public void discard() {
        lootView.discard();
    }

    /**
     * Gibt die Loot-Ansicht zurück.
     *
     * @return Die LootView-Instanz.
     */
    public LootView getLootView() {
        return lootView;
    }

    @Override
    public void onBackClicked() {
        LoggingAssistant.log("LootView closed");
        if (fieldType == FieldEnum.BOSS_FIELD) {
            GuiHelper.Scenes.startStatisticScene(player);
            return;
        }
        GuiHelper.Scenes.startMapScene(player);
    }

    @Override
    public void onCardClick(Card card) {
        addCardToDeck(card);
    }

    @Override
    public void onFullScreenClick() {
        Stage primaryStage = player.getPrimaryStage();

        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    @Override
    public void onGoldClick(int gold) {
        LoggingAssistant.log("Got gold: " + gold);
        player.increaseGold(gold);
    }

    @Override
    public void onPotionClick(Potion potion) {
        if (player.getPotions().size() < 3) {
            LoggingAssistant.log("Got a potion: " + potion.getName());
            player.addPotion(potion);
        }
        else {
            LoggingAssistant.log("Maximum number of potions reached", Color.YELLOW);
            lootView.showDialog("You have reached the maximum number of Potion.");
        }
    }

    /**
     * Fügt eine Karte dem Deck des Spielers hinzu.
     *
     * @param card Die hinzuzufügende Karte.
     */
    private void addCardToDeck(Card card) {
        LoggingAssistant.log("Got a card: " + card.getName());
        player.addCardToDeck(card);
    }

    /**
     * Generiert eine Trankkarte basierend auf einer Zufallswahrscheinlichkeit.
     */
    private void generatePotionByChance() {
        if (rnd.nextDouble() < potionsChance) {
            potion = deckFactory.generatePotion();
        }
    }

    /**
     * Initialisiert die Goldmenge basierend auf dem Feldtyp.
     *
     * @param fieldType Der Typ des Feldes, das den Goldfaktor bestimmt.
     */
    private void initGoldLoot(FieldEnum fieldType) {
        switch (fieldType) {
            case BOSS_FIELD:
                // 95 - 105
                gold = rnd.nextInt(105 + 1 - 95) + 95;
                break;
            case ELITE_FIELD:
                // 25 - 35
                gold = rnd.nextInt(35 + 1 - 25) + 25;
                break;
            case ENEMY_FIELD:
                // 10 - 20
                gold = rnd.nextInt(20 + 1 - 10) + 10;
                break;
        }
    }

    /**
     * Initialisiert die Chancen für Items und die Anzahl an möglichen Karten basierend auf dem Schwierigkeitsgrad.
     */
    private void initItemChanceAndAmount() {
        gold = GameSettings.getDifficultyLevel().modifyGold(gold);
        amount = GameSettings.getDifficultyLevel().getCardAmount();
        potionsChance = GameSettings.getDifficultyLevel().getPotionChance();
    }

    /**
     * Initialisiert das Loot-Deck mit Karten.
     *
     * @return Die Liste der initialisierten Karten.
     */
    private List<Card> initialLootDeck() {
        return deckFactory.init(false);
    }

}
