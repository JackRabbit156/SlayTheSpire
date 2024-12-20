package de.bundeswehr.auf.slaythespire.model.card;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

public class CheaterCard extends AttackCard {

    public CheaterCard() {
        super("Cheater Card", "Deals 40 damage. For free.", 0, 40, CardRarity.SPECIAL, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
