package models.enemy.act_one;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.act_one.mad_gremlin_enemy_cards.ScratchEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert den Gegner "MadGremlin" im ersten Akt des Spiels.
 * Der Mad Gremlin hat spezifische Eigenschaften und Fähigkeiten,
 * die im Kampf verwendet werden können.
 *
 * @author Warawa Alexander
 * @author OF Daniel Willig
 */
public class MadGremlinEnemy extends Enemy {
    public MadGremlinEnemy() {
        super("Mad Gremlin", 20, 24);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new ScratchEnemyCard());

        setEnemyDeck(deck);
    }


    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }

}