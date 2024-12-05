package de.bundeswehr.auf.slaythespire.tester;

import javafx.stage.Stage;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.AttackCard;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.Card;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.card.ironclad.IroncladDefendCard;
import de.bundeswehr.auf.slaythespire.models.card.ironclad.IroncladStrikeCard;
import de.bundeswehr.auf.slaythespire.models.card.ironclad.attack.common.BashCard;
import de.bundeswehr.auf.slaythespire.models.card.silent.SilentDefendCard;
import de.bundeswehr.auf.slaythespire.models.card.silent.SilentStrikeCard;
import de.bundeswehr.auf.slaythespire.models.card.silent.attack.common.NeutralizeCard;
import de.bundeswehr.auf.slaythespire.models.card.silent.skill.common.SurvivorCard;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.PlayerType;
import de.bundeswehr.auf.slaythespire.models.relic.BurningBloodRelic;
import de.bundeswehr.auf.slaythespire.models.relic.RingOfTheSnake;
import de.bundeswehr.auf.slaythespire.models.relic.relic_structure.Relic;

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

        @Override
        public int dealDamage() {
            return getDamage();
        }

        @Override
        public void play(GameContext gameContext) {
            Enemy enemy = gameContext.getSelectedEnemy();
            enemy.takeDamage(dealDamage());

            Player player = gameContext.getPlayer();
            player.decreaseCurrentEnergy(getCost());
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
    }

    @Override
    protected void initDeck() {
//        starterDeckIronclad();
//        starterDeckSilent();
        cheaterDeck();
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
        Relic startRelic = new RingOfTheSnake();
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