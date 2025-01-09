package de.bundeswehr.auf.slaythespire.model.enemy_card.act_two.elite.book_of_stabbing;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;

import java.util.Random;

/**
 * Die Multi stab enemy card.
 *
 * @author OF Daniel Willig
 */
public class MultiStabEnemyCard extends EnemyCard {

    private static final Random rnd = new Random();

    private int randomAttack = rnd.nextInt(3);

    /**
     * Constructor Multi stab enemy card.
     */
    public MultiStabEnemyCard() {
        super("Multi Stab", "Deals " + GameSettings.getDifficultyLevel().getDamage(6) + " x N damage.", GameSettings.getDifficultyLevel().getDamage(6) + " x N");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();

        for (int i = 0; i < randomAttack; i++) {
            player.decreaseCurrentHealth(GameSettings.getDifficultyLevel().getDamage(6), false, gameContext);
        }
    }

}
