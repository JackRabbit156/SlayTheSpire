package tests;

import javafx.stage.Stage;
import models.card.card_structure.*;
import models.card.ironclad.attack.common.BashCard;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relic.BurningBloodRelic;
import models.relic.relic_structure.Relic;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Keil, Vladislav
 */
public class TestPlayer extends Player {
    public TestPlayer(Stage primaryStage) {
        super("TesterPlayer", 1000, 1000, PlayerType.SILENT, primaryStage);
        setImagePath("/images/player/IroncladPlayer.png");
        initRelic();
        initDeck();
    }

    // * Methods *
    @Override
    protected void initRelic() {
        Relic startRelic = new BurningBloodRelic();
        setRelic(startRelic);
    }


    @Override
    protected void initDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            deck.add(new BossCard());

        for (int i = 0; i < 4; i++)
            deck.add(new BossCard());

        deck.add(new BashCard());

        setDeck(deck);
    }

    private class BossCard extends AttackCard {
        public BossCard() {
            super("Boss Card", "CheaterCard", 0, 40,CardRarity.RARE, CardGrave.DISCARD);
            setImagePath("/images/card/BossCard.jpg");
        }

        @Override
        public void play(GameContext gameContext) {
            Enemy enemy = gameContext.getSelectedEnemy();
            enemy.takeDamage(dealDamage());

            Player player = gameContext.getPlayer();
            player.decreaseCurrentEnergy(getCost());
        }

        @Override
        public int dealDamage() {
            return getDamage();
        }
    }
}