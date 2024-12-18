package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Sever soul Karte.
 *
 * @author OF Daniel Willig
 */
public class SeverSoulCard extends AttackCard {

    /**
     * Constructor Sever soul card.
     */
    public SeverSoulCard() {
        super("Sever Soul", "Deal 16 damage.", 2, 16, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
