package de.bundeswehr.auf.slaythespire.model.player;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.ModelInitializer;
import de.bundeswehr.auf.slaythespire.model.card.CheaterCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterEnergyCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterHealCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.ClotheslineCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.HeavyBladeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.ThunderclapCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.WildStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare.ImmolateCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon.HemokinesisCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon.RecklessChargeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon.UppercutCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.power.uncommon.RuptureCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common.ShrugItOffCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.uncommon.EntrenchCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.uncommon.GhostlyArmorCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.CheaterRelic;
import de.bundeswehr.auf.slaythespire.model.relic.ironclad.common.BurningBloodRelic;
import de.bundeswehr.auf.slaythespire.model.relic.silent.common.RingOfTheSnakeRelic;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class TestPlayer extends Player {

    public static TestPlayer cheater(Stage primaryStage) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.IRONCLAD, primaryStage);
        testPlayer.setImagePath(new PathAssistent().toPath(testPlayer));
        testPlayer.setGameOverImagePath(new PathAssistent().toAltPath(testPlayer, 1));
        testPlayer.setEnergyIconPath(new PathAssistent().toAltPath(testPlayer, 2));
        testPlayer.initRelic();
        testPlayer.initDeck();
        return testPlayer;
    }

    public static TestPlayer custom(Stage primaryStage, List<Card> deck) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.IRONCLAD, primaryStage);
        testPlayer.setImagePath(new PathAssistent().toPath(testPlayer));
        testPlayer.setGameOverImagePath(new PathAssistent().toAltPath(testPlayer, 1));
        testPlayer.setEnergyIconPath(new PathAssistent().toAltPath(testPlayer, 2));
        testPlayer.initRelic();
        testPlayer.setDeck(deck);
        return testPlayer;
    }

    public static TestPlayer defensive(Stage primaryStage) {
        return custom(primaryStage, defensiveDeck());
    }

    public static TestPlayer effects(Stage primaryStage) {
        return custom(primaryStage, effectsDeck());
    }

    public static TestPlayer ironclad(Stage primaryStage) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.IRONCLAD, primaryStage);
        testPlayer.initWithDelegate(new IroncladPlayer(primaryStage));
        return testPlayer;
    }

    public static TestPlayer silent(Stage primaryStage) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.SILENT, primaryStage);
        testPlayer.initWithDelegate(new SilentPlayer(primaryStage));
        return testPlayer;
    }

    public static TestPlayer status(Stage primaryStage) {
        return custom(primaryStage, statusDeck());
    }

    private static List<Card> defensiveDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new IroncladDefendCard());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new GhostlyArmorCard());
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new ShrugItOffCard());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new EntrenchCard());
        }
        return deck;
    }

    private static List<Card> effectsDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new RuptureCard());
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new HemokinesisCard());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new HeavyBladeCard());
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new UppercutCard());
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new ThunderclapCard());
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new ClotheslineCard());
        }
        return deck;
    }

    private static List<Card> statusDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new RecklessChargeCard());
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new ImmolateCard());
        }
        for (int i = 0; i < 5; i++) {
            deck.add(new WildStrikeCard());
        }
        return deck;
    }

    private TestPlayer(PlayerType playerType, Stage primaryStage) {
        super("Tester", 1000, 10, playerType, primaryStage);
        ModelInitializer.initModel();
        GameSettings.startTimer();
    }

    @Override
    protected void initDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            deck.add(new CheaterCard());
        }
        for (int i = 0; i < 10; i++) {
            deck.add(new CheaterDefendCard());
        }
        for (int i = 0; i < 10; i++) {
            deck.add(new CheaterEnergyCard());
        }
        for (int i = 0; i < 10; i++) {
            deck.add(new CheaterHealCard());
        }
        setDeck(deck);
    }

    @Override
    protected void initRelic() {
        addRelic(new CheaterRelic());
        addRelic(new BurningBloodRelic());
        addRelic(new RingOfTheSnakeRelic());
    }

    private void initWithDelegate(Player delegate) {
        setImagePath(delegate.getImagePath());
        setGameOverImagePath(delegate.getGameOverImagePath());
        setEnergyIconPath(delegate.getEnergyIconPath());
        // Use starter relic of delegate
        setRelics(delegate.getRelics());
        // Use starter deck of delegate
        setDeck(delegate.getDeck());
    }

}