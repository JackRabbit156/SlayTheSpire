package models.enemy.act_two;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.spheric_guardian_enemy_cards.ActivateEnemyCard;
import models.enemy_card.act_two.spheric_guardian_enemy_cards.HardenEnemyCard;
import models.enemy_card.act_two.spheric_guardian_enemy_cards.SlamSEnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * Diese Klasse repräsentiert den Gegner "Spheric Guardian" im zweiten Akt des Spiels.
 * Spheric Guardian hat spezifische Angriffsfähigkeiten, die bei jedem
 * Angriff zufällig ausgewählt werden.
 *
 * @author Warawa Alexander
 */
public class SphericGuardianEnemy extends Enemy {
    public SphericGuardianEnemy() {
        super("Spheric Guardian", 20, 20);
        setImagePath(new PathAssistent().toPath(this));
        setBlock(40);
    }

    @Override
    public void attack(GameContext gameContext) {
        getEnemyDeck().get(getEnemyCardToBePlayed()).playEnemy(gameContext, this);
    }
}
