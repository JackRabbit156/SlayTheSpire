package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Bludgeon Karte.
 *
 * @author OF Daniel Willig
 */
public class BludgeonCard extends AttackCard {

    /**
     * Constructor Bludgeon card.
     */
    public BludgeonCard() {
        super("Bludgeon", "Deal 32 damage.", 3, 32, CardRarity.RARE, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
