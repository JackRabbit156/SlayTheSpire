package models.enemy.act_one.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.act_one.elites.gremlin_nob_elite_cards.RushEnemyCard;
import models.enemy_card.act_one.elites.gremlin_nob_elite_cards.SkullBashEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @author Keil, Vladislav
 * @author OF Daniel Willig
 */
public class GremlinNobElite extends Enemy {
    public GremlinNobElite() {
        super("Gremlin Nob", 82, 86);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new RushEnemyCard());
        deck.add(new SkullBashEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
