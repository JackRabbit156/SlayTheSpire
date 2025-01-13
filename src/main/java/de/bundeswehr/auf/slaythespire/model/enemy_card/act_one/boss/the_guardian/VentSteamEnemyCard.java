package de.bundeswehr.auf.slaythespire.model.enemy_card.act_one.boss.the_guardian;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.BlockEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Charging up enemy card.
 *
 * @author OF Daniel Willig
 */
public class VentSteamEnemyCard extends EnemyCard {

    /**
     * Constructor Charging up enemy card.
     */
    public VentSteamEnemyCard() {
        super("Vent Steam", "Inflicts 2 Weak and 2 Vulnerable (in that order).", "2");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        Player player = gameContext.getPlayer();
        player.addEffect(new WeakDebuff(), 2);
        player.addEffect(new VulnerableDebuff(), 2);
    }

}
