package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Wild strike Karte.
 *
 * @author OF Daniel Willig
 */
public class WildStrikeCard extends AttackCard {

    /**
     * Constructor Wild strike card.
     */
    public WildStrikeCard() {
        super("Wild Strike", "Deal 12 damage.", 1, 12, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
