package de.bundeswehr.auf.slaythespire.model.card.silent.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Neutralize Karte.
 *
 * @author OF Daniel Willig
 */
public class NeutralizeCard extends AttackCard {

    /**
     * Constructor Neutralize card.
     */
    public NeutralizeCard() {
        super("Neutralize", "Deal 3 damage.", 0, 3, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}