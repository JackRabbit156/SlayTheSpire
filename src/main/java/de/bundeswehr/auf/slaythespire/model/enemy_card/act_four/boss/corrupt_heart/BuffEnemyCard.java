package de.bundeswehr.auf.slaythespire.model.enemy_card.act_four.boss.corrupt_heart;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.VulnerableDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.player.debuff.BeatOfDeathDebuffPlayer;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.EnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Blood shots enemy card.
 *
 * @author OF Daniel Willig
 */
public class BuffEnemyCard extends EnemyCard {

    private int counter;

    /**
     * Constructor Blood shots enemy card.
     */
    public BuffEnemyCard() {
        super("Buff", "Gain 2 Strength and additional buffs:\n" +
                "1st Buff: Strength 2\n" +
                "2nd Buff: Beat of Death 1\n" +
                "3rd Buff: Strength 2\n" +
                "4th Buff: Strength 10\n" +
                "5th Buff onward: Strength 50", "");
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void playEnemy(GameContext gameContext, Enemy enemy) {
        enemy.addEffect(new StrengthBuff(), 2);
        switch (++counter) {
            case 1:
            case 3:
                enemy.addEffect(new StrengthBuff(), 2);
                break;
            case 2:
                Player player = gameContext.getPlayer();
                player.addEffect(new BeatOfDeathDebuffPlayer(), 1);
                break;
            case 4:
                enemy.addEffect(new StrengthBuff(), 10);
                break;
            default:
                enemy.addEffect(new StrengthBuff(), 50);
        }
    }

}
