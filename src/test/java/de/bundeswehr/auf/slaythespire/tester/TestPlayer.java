package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.ClashCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.HeadbuttCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common.WarcryCard;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.IroncladStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common.BashCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.SilentDefendCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.SilentStrikeCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.attack.common.NeutralizeCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.skill.common.SurvivorCard;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.player.structure.PlayerType;
import de.bundeswehr.auf.slaythespire.model.relic.BurningBloodRelic;
import de.bundeswehr.auf.slaythespire.model.relic.RingOfTheSnakeRelic;
import de.bundeswehr.auf.slaythespire.model.relic.structure.Relic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class TestPlayer extends Player {

    private static class CheaterCard extends AttackCard {

        public CheaterCard() {
            super("Cheater Card", "Deals 40 damage. For free.", 0, 40, CardRarity.SPECIAL, CardGrave.DISCARD);
            setImagePath("/images/card/BossCard.jpg");
        }

    }

    public TestPlayer(Stage primaryStage) {
        super("Tester", 1000, 1000, PlayerType.IRONCLAD, primaryStage);
        setImagePath("/images/player/IroncladPlayer.png");
        setAltImagePath("/images/player/IroncladPlayerAlt1.png");

//        super("TesterPlayer", 1000, 1000, PlayerType.SILENT, primaryStage);
//        setImagePath("/images/player/SilentPlayer.png");
//        setAltImagePath("/images/player/SilentPlayerAlt1.png");

        initRelic();
        initDeck();
        GameSettings.startTimer();
    }

    @Override
    protected void initDeck() {
//        starterDeckIronclad();
//        starterDeckSilent();
        cheaterDeck();
//        customDeck();
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

    // * Methods *
    @Override
    protected void initRelic() {
        starterRelicIronclad();
//        starterRelicSilent();
    }

    private void starterRelicIronclad() {
        Relic startRelic = new BurningBloodRelic();
        setRelic(startRelic);
    }

    private void starterRelicSilent() {
        Relic startRelic = new RingOfTheSnakeRelic();
        setRelic(startRelic);
    }

    private void cheaterDeck() {
        List<Card> deck = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            deck.add(new CheaterCard());
        }

        for (int i = 0; i < 4; i++) {
            deck.add(new CheaterCard());
        }

        deck.add(new BashCard());

        setDeck(deck);
    }

    private void starterDeckIronclad() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new IroncladStrikeCard());
        }

        for (int i = 0; i < 4; i++) {
            deck.add(new IroncladDefendCard());
        }

        deck.add(new BashCard());

        setDeck(deck);
    }

    private void starterDeckSilent() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new SilentStrikeCard());
        }

        for (int i = 0; i < 5; i++) {
            deck.add(new SilentDefendCard());
        }

        deck.add(new SurvivorCard());

        deck.add(new NeutralizeCard());

        setDeck(deck);
    }

}