package de.bundeswehr.auf.slaythespire.model.card.status;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.UnplayableCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class VoidCard extends UnplayableCard {

    public VoidCard() {
        super("Void", "Unplayable. When this card is drawn, lose 1 Energy. Ethereal.", CardGrave.ETHEREAL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onDraw(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(1);
    }

}
