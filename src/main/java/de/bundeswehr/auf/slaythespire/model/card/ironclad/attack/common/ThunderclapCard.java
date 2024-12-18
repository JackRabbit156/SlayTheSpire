package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Thunderclap Karte.
 *
 * @author OF Daniel Willig
 */
public class ThunderclapCard extends AttackCard {

    /**
     * Constructor Thunderclap card.
     */
    public ThunderclapCard() {
        super("Thunderclap", "Deal 4 damage.", 1, 4, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
