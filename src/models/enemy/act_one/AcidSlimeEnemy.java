package models.enemy.act_one;

import models.battle.GameContext;
import helper.PathAssistent;
import models.enemy.Enemy;
import models.enemy_card.act_one.acid_slime.CorrosiveSpitEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert den Gegner "Acid Slime" im ersten Akt des Spiels.
 * Der Acid Slime hat spezifische Eigenschaften und Fähigkeiten,
 * die im Kampf verwendet werden können.
 *
 * @author Warawa Alexander
 * @author OF Daniel Willig
 */
public class AcidSlimeEnemy extends Enemy {
    public AcidSlimeEnemy() {
        super("Acid Slime (L)", 65, 69);
        setImagePath(new PathAssistent().toPath(this));
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new CorrosiveSpitEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}