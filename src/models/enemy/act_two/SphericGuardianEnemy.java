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
        Random randi = new Random();
        int randomAttack = randi.nextInt(3);

        switch (randomAttack){
            case 0: new SlamSEnemyCard().play(gameContext); break;
            case 1: new ActivateEnemyCard().play(gameContext, this); break;
            case 2: new HardenEnemyCard().play(gameContext, this); break;
        }
    }
}
