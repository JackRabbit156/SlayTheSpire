package models.enemy.act_one.boss;

import models.battle.GameContext;
import helper.PathAssistent;
import models.enemy.Enemy;
import models.enemy_card.act_one.boss.the_guardian.ChargingUpEnemyCard;
import models.enemy_card.act_one.boss.the_guardian.FierceBashEnemyCard;
import models.enemy_card.act_one.boss.the_guardian.WhirlwindEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class TheGuardianBoss extends Enemy {
    // https://slay-the-spire.fandom.com/wiki/The_Guardian
    public TheGuardianBoss() {
        super("The Guardian", 240, 240);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new ChargingUpEnemyCard());
        deck.add(new FierceBashEnemyCard());
        deck.add(new WhirlwindEnemyCard());

        setEnemyDeck(deck);
    }


    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
