package models.enemy.act_one;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.Card;
import models.enemy.Enemy;
import models.enemy_card.act_one.cultist_enemy_cards.DarkStrikeEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert den Gegner "Cultist" im ersten Akt des Spiels.
 * Der Cultist hat spezifische Eigenschaften und Fähigkeiten,
 * die im Kampf verwendet werden können.
 *
 * @author Warawa Alexander
 */
public class CultistEnemy extends Enemy {
    public CultistEnemy() {
        super("Cultist", 48, 54);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new DarkStrikeEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this); //TODO get(0) muss von calcIntent gesetzt werden. Also die 0
    }
}
