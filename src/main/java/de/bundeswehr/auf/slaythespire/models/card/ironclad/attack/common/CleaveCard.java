package de.bundeswehr.auf.slaythespire.models.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.AttackCard;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

import java.util.List;

/**
 * Cleave Karte.
 * @author OF Daniel Willig
 */
public class CleaveCard extends AttackCard {
    /**
     * Constructor CleaveCard.
     */
    public CleaveCard() {
        super("Cleave", "Deal 8 damage to ALL enemies.", 1, 8, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy singleEnemy : allEnemies) {
            singleEnemy.takeDamage(dealDamage());
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
