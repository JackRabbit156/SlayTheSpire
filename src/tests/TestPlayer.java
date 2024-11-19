package tests;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.card.card_structure.*;
import models.card.ironclad_cards.attack.common.BashCard;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relic.BurningBloodRelic;
import models.relic.relic_structure.Relic;

import java.util.ArrayList;
import java.util.List;

public class TestPlayer extends Player {
    public TestPlayer() {
        super("TesterPlayer", 1000, 1000, PlayerType.IRONCLAD, "⚒");
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
            setImagePath("/images/card/ironclad_cards/attack/rare/ReaperCard.png");
            targetIsRequired();
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