package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.ClashCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.HeadbuttCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common.WarcryCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.player.IroncladPlayer;
import de.bundeswehr.auf.slaythespire.model.player.SilentPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.ironclad.common.BurningBloodRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;
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
        testPlayer.setImagePath("/images/player/IroncladPlayer.png");
        testPlayer.setAltImagePath("/images/player/IroncladPlayerAlt1.png");
        testPlayer.initRelic();
        testPlayer.cheaterDeck();
        return testPlayer;
    }

    public static TestPlayer custom(Stage primaryStage) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.IRONCLAD, primaryStage);
        testPlayer.setImagePath("/images/player/IroncladPlayer.png");
        testPlayer.setAltImagePath("/images/player/IroncladPlayerAlt1.png");
        testPlayer.initRelic();
        testPlayer.customDeck();
        return testPlayer;
    }

    public static TestPlayer ironclad(Stage primaryStage) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.IRONCLAD, primaryStage);
        testPlayer.setImagePath("/images/player/IroncladPlayer.png");
        testPlayer.setAltImagePath("/images/player/IroncladPlayerAlt1.png");
        testPlayer.initWithDelegate(new IroncladPlayer(primaryStage));
        return testPlayer;
    }

    public static TestPlayer silent(Stage primaryStage) {
        TestPlayer testPlayer = new TestPlayer(PlayerType.SILENT, primaryStage);
        testPlayer.setImagePath("/images/player/SilentPlayer.png");
        testPlayer.setAltImagePath("/images/player/SilentPlayerAlt1.png");
        testPlayer.initWithDelegate(new SilentPlayer(primaryStage));
        return testPlayer;
    }

    private TestPlayer(PlayerType playerType, Stage primaryStage) {
        super("Tester", 1000, 1000, playerType, primaryStage);
        ModelInitializer.initModel();
        GameSettings.startTimer();
    }

    @Override
    public void initDeck() {
    }

    @Override
    public void initRelic() {
        Relic startRelic = new BurningBloodRelic();
        setRelic(startRelic);
    }

    private void cheaterDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            deck.add(new CheaterCard());
        }
        setDeck(deck);
    }

    private void customDeck() {
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
        setDeck(deck);
    }

    private void initWithDelegate(Player delegate) {
        delegate.initRelic();
        setRelic(delegate.getRelic());
        delegate.initDeck();
        setDeck(delegate.getDeck());
    }

}