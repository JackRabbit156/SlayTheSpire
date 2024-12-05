package models.enemy.act_four.elite;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_four.elite.spire_shield.SmashEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class SpireShieldElite extends Enemy {
    public SpireShieldElite() {
        super("Spire Shield",160, 160);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new SmashEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
