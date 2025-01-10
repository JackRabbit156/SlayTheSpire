package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.spheric_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

/**
 * Die Harden enemy card.
 *
 * @author OF Daniel Willig
 */
public class HardenEnemyCard extends EnemyCard {

    /**
     * Constructor Harden enemy card.
     */
    public HardenEnemyCard() {
        super("Corrosive Spit",
                "Deals " + GameSettings.getDifficultyLevel().modifyDamage(10) + " damage, gains " +
                        GameSettings.getDifficultyLevel().modifyDamage(15) + " block.",
                GameSettings.getDifficultyLevel().modifyDamage(10) + "/" +
                        GameSettings.getDifficultyLevel().modifyDamage(15));
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        enemy.dealDamage(gameContext, GameSettings.getDifficultyLevel().modifyDamage(10), player, this);
        enemy.gainBlock(GameSettings.getDifficultyLevel().modifyDamage(15));
    }

}
