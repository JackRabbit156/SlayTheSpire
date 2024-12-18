package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

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

    /**
     * Deal damage int.
     *
     * @param gameContext the game context
     * @return the int
     */
    @Override
    public int dealDamage(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        return player.getBlock();
    }

}
