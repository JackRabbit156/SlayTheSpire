package de.bundeswehr.auf.slaythespire.model.card.ironclad;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

public class IroncladStrikeCard extends AttackCard {

    public IroncladStrikeCard() {
        super("Strike", "Deal 6 damage.", 1, 6, CardRarity.BASIC, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}