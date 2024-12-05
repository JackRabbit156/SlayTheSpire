package de.bundeswehr.auf.slaythespire.models.card.ironclad.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.AttackCard;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

import java.util.List;

/**
 * Die Whirlwind Karte.
 *
 * @author OF Daniel Willig
 */
public class WhirlwindCard extends AttackCard {
    /**
     * Constructor Whirlwind card.
     */
    public WhirlwindCard() {
        super("Whirlwind", "Deal 8 damage to ALL enemies X times.", 1, 5, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> enemies = gameContext.getEnemies();
        Player player = gameContext.getPlayer();

        for (int i = 0; i < player.getCurrentEnergy(); i++) {
            for (Enemy enemy : enemies) {
                enemy.takeDamage(dealDamage());
            }
        }

        player.decreaseCurrentEnergy(player.getCurrentEnergy());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
