package de.bundeswehr.auf.slaythespire.model.card.silent.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Backflip Karte.
 *
 * @author L Frank Rieger
 */
public class BackflipCard extends SkillCard {

    public BackflipCard() {
        super("Backflip", "Gain 5 Block. Draw 2 cards.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainBlock(5);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(2);

        player.decreaseCurrentEnergy(getCost());
    }

}