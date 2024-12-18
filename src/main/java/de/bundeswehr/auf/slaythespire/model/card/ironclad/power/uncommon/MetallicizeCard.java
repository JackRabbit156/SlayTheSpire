package de.bundeswehr.auf.slaythespire.model.card.ironclad.power.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Metallicize Karte.
 *
 * @author OF Daniel Willig
 */
public class MetallicizeCard extends PowerCard {

    /**
     * Constructor Metallicize card.
     */
    public MetallicizeCard() {
        super("Metallicize", "At the end of your turn, gain 3 Block.", 1, CardRarity.UNCOMMON, CardGrave.NONE, CardTrigger.PLAYER_EOT);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseBlock(3);
    }

}
