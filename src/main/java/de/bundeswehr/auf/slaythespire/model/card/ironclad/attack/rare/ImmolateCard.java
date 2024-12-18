package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Immolate Karte.
 *
 * @author OF Daniel Willig
 */
public class ImmolateCard extends AttackCard {

    /**
     * Constructor Immolate card.
     */
    public ImmolateCard() {
        super("Immolate", "Deal 21 damage to ALL enemies.", 2, 21, CardRarity.RARE, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy allEnemy : allEnemies) {
            allEnemy.takeDamage(dealDamage(gameContext));
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}
