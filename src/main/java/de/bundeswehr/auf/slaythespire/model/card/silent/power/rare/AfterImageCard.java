package de.bundeswehr.auf.slaythespire.model.card.silent.power.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die After Image Karte.
 *
 * @author L Frank Rieger
 */
public class AfterImageCard extends PowerCard {

    public AfterImageCard() {
        super("After Image", "Whenever you play a card, gain 1 Block.", 2, CardRarity.RARE, CardGrave.NONE, CardTrigger.PLAY_CARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainBlock(1);
    }

}