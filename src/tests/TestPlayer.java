package tests;

import javafx.stage.Stage;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.ironclad.IroncladDefendCard;
import models.card.ironclad.IroncladStrikeCard;
import models.card.ironclad.attack.common.BashCard;
import models.card.silent.SilentDefendCard;
import models.card.silent.SilentStrikeCard;
import models.card.silent.attack.common.NeutralizeCard;
import models.card.silent.skill.common.SurvivorCard;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relic.BurningBloodRelic;
import models.relic.RingOfTheSnake;
import models.relic.relic_structure.Relic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 */
public class TestPlayer extends Player {

    private static class BossCard extends AttackCard {

        public BossCard() {
            super("Boss Card", "CheaterCard", 0, 40, CardRarity.RARE, CardGrave.DISCARD);
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
//        super("TesterPlayer", 1000, 1000, PlayerType.SILENT, primaryStage);
//        setImagePath("/images/player/SilentPlayer.png");
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
            deck.add(new BossCard());
        }

        for (int i = 0; i < 4; i++) {
            deck.add(new BossCard());
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