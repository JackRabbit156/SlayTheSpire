package de.bundeswehr.auf.slaythespire.model.card.silent.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;

/**
 * Die Slice Karte.
 *
 * @author L Frank Rieger
 */
public class SliceCard extends AttackCard {

    public SliceCard() {
        super("Slice", "Deal 6 damage.", 0, 6, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

}