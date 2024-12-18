package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Carnage Karte.
 *
 * @author OF Daniel Willig
 */
public class CarnageCard extends AttackCard {

    /**
     * Constructor Carnage card.
     */
    public CarnageCard() {
        super("Carnage", "Deal 20 damage.", 2, 20, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
