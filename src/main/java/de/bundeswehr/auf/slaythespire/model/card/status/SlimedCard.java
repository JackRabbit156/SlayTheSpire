package de.bundeswehr.auf.slaythespire.model.card.status;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.StatusCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.UnplayableCard;

public class SlimedCard extends StatusCard {

    public SlimedCard() {
        super("Slimed", "Exhaust.", 1, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {

    }

}
