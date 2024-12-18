package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Clothesline Karte.
 * @author OF Daniel Willig
 */
public class ClotheslineCard extends AttackCard {

    /**
     * Constructor ClotheslineCard
     */
    public ClotheslineCard() {
        super("Clothesline", "Deal 12 damage.", 2, 12, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
