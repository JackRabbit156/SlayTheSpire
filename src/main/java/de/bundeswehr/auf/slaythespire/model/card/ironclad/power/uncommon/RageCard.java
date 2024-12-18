package de.bundeswehr.auf.slaythespire.model.card.ironclad.power.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Rage Karte.
 *
 * @author L Frank Rieger
 */
public class RageCard extends PowerCard {

    /**
     * Constructor Entrench card.
     */
    public RageCard() {
        super("Rage", "Whenever you play an Attack this turn, gain 3 Block.", 2, CardRarity.UNCOMMON, CardGrave.DISCARD, CardTrigger.PLAY_ATTACK);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseBlock(3);
    }

}
