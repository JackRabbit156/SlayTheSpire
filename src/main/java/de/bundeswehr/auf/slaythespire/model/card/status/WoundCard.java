package de.bundeswehr.auf.slaythespire.model.card.status;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.UnplayableCard;

public class WoundCard extends UnplayableCard {

    public WoundCard() {
        super("Wound", "Unplayable.", CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}
