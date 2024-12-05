package models.enemy.act_two;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.spheric_guardian.ActivateEnemyCard;
import models.enemy_card.act_two.spheric_guardian.HardenEnemyCard;
import models.enemy_card.act_two.spheric_guardian.SlamSEnemyCard;
import models.enemy_card.enemy_card_structure.EnemyCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert den Gegner "Spheric Guardian" im zweiten Akt des Spiels.
 * Spheric Guardian hat spezifische Angriffsfähigkeiten, die bei jedem
 * Angriff zufällig ausgewählt werden.
 *
 * @author Warawa Alexander
 * @author OF Daniel Willig
 */
public class SphericGuardianEnemy extends Enemy {
    public SphericGuardianEnemy() {
        super("Spheric Guardian", 20, 20);
        setImagePath(new PathAssistent().toPath(this));
        setBlock(40);
        initEnemyDeck();
    }

    private void initEnemyDeck() {
        List<EnemyCard> deck = new ArrayList<>();

        deck.add(new SlamSEnemyCard());
        deck.add(new ActivateEnemyCard());
        deck.add(new HardenEnemyCard());

        setEnemyDeck(deck);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
