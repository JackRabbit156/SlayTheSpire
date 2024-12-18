package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardType;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.potion.structure.Potion;

/**
 * Die Fire potion.
 *
 * @author OF Daniel Willig
 */
public class FirePotion extends Potion {

    /**
     * Constructor Fire potion.
     */
    public FirePotion() {
        super("Fire Potion", "Deal 20 Damage to target enemy.",  CardRarity.COMMON,  CardType.ATTACK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(20);
    }
}
