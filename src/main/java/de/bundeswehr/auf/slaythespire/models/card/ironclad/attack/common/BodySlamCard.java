package de.bundeswehr.auf.slaythespire.models.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.AttackCard;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Body slam Karte.
 * @author OF Daniel Willig
 */
public class BodySlamCard extends AttackCard {
    /**
     * Constructor BodySlamCard.
     */
    public BodySlamCard() {
        super("Body Slam", "Deal damage equal to your Block.", 1, 0, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage(gameContext));

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }


    @Override
    public int dealDamage() {
        return 0;
    }

    /**
     * Deal damage int.
     *
     * @param gameContext the game context
     * @return the int
     */
    public int dealDamage(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        return player.getBlock();
    }
}
