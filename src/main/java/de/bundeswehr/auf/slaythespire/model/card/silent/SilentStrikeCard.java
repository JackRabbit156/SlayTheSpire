package de.bundeswehr.auf.slaythespire.model.card.silent;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Silent strike card.
 *
 * @author OF Daniel Willig
 */
public class SilentStrikeCard extends AttackCard {
    /**
     * Constructor Silent strike card.
     */
    public SilentStrikeCard() {
        super("Strike", "Deal 6 damage.", 1, 6, CardRarity.BASIC, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}