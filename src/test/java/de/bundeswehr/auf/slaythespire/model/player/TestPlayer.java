package de.bundeswehr.auf.slaythespire.model.player;

import de.bundeswehr.auf.slaythespire.model.ModelInitializer;
import de.bundeswehr.auf.slaythespire.model.card.CheaterCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterEnergyCard;
import de.bundeswehr.auf.slaythespire.model.card.CheaterHealCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.ClashCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.HeadbuttCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common.ShrugItOffCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common.WarcryCard;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class TestPlayer extends Player {

    public static TestPlayer cheater(Stage primaryStage) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.IRONCLAD, primaryStage);
        testPlayer.setImagePath("/images/player/TestPlayer.png");
        testPlayer.setAltImagePath("/images/player/TestPlayerAlt1.png");
        testPlayer.initRelic();
        testPlayer.initDeck();
        return testPlayer;
    }

    public static TestPlayer custom(Stage primaryStage) {
        return custom(primaryStage, customDeck());
    }

    public static TestPlayer custom(Stage primaryStage, List<Card> deck) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.IRONCLAD, primaryStage);
        testPlayer.setImagePath("/images/player/TestPlayer.png");
        testPlayer.setAltImagePath("/images/player/TestPlayerAlt1.png");
        testPlayer.initRelic();
        testPlayer.setDeck(deck);
        return testPlayer;
    }

    public static TestPlayer defensive(Stage primaryStage) {
        return custom(primaryStage, defensiveDeck());
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

    private static List<Card> customDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new IroncladStrikeCard());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new HeadbuttCard());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new WarcryCard());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new ClashCard());
        }
        return deck;
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
        try {
            setImagePath(delegate.getImagePath());
            setAltImagePath(delegate.getAltImagePath());
            // Use starter relic of delegate
            Method initRelic = delegate.getClass().getDeclaredMethod("initRelic");
            initRelic.setAccessible(true);
            initRelic.invoke(delegate);
            setRelics(delegate.getRelics());
            // Use starter deck of delegate
            Method initDeck = delegate.getClass().getDeclaredMethod("initDeck");
            initDeck.setAccessible(true);
            initDeck.invoke(delegate);
            setDeck(delegate.getDeck());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}