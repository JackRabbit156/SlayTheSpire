package de.bundeswehr.auf.slaythespire.model.card.status;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.UnplayableCard;

public class DazedCard extends UnplayableCard {

    public DazedCard() {
        super("Dazed", "Unplayable. Ethereal.", CardGrave.ETHEREAL);
        setImagePath(new PathAssistent().toPath(this));
    }

}
