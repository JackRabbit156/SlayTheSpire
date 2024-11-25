package models.enemy.act_four.elites;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_four.elites.spire_shield_elite_cards.SmashEnemyCard;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;
import models.player.player_structure.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keil, Vladislav
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
