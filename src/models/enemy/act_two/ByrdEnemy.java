package models.enemy.act_two;

import helper.PathAssistent;
import models.battle.GameContext;
import models.enemy.Enemy;
import models.enemy_card.act_two.byrd_enemy_cards.HeadbuttEnemyCard;
import models.enemy_card.act_two.byrd_enemy_cards.PeckEnemyCard;
import models.enemy_card.act_two.byrd_enemy_cards.SwoopEnemyCard;
import models.player.player_structure.Player;

import java.util.Random;

/**
 * Diese Klasse repräsentiert den Gegner "Byrd" im zweiten Akt des Spiels.
 * Byrd hat spezifische Angriffsfähigkeiten, die bei jedem
 * Angriff zufällig ausgewählt werden.
 *
 * @author Warawa Alexander
 */
public class ByrdEnemy extends Enemy {
    public ByrdEnemy() {
        super("Byrd", 25, 31);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void attack(GameContext gameContext) {
        Random randi = new Random();
        int randomAttack = randi.nextInt(3);

        switch (randomAttack){
            case 0: new PeckEnemyCard().play(gameContext); break;
            case 1: new SwoopEnemyCard().play(gameContext); break;
            case 2: new HeadbuttEnemyCard().play(gameContext); break;
        }
    }

}