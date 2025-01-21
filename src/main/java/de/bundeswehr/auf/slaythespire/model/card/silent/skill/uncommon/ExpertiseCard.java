package de.bundeswehr.auf.slaythespire.model.card.silent.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Expertise Karte.
 *
 * @author L Frank Rieger
 */
public class ExpertiseCard extends SkillCard {

    public ExpertiseCard() {
        super("Expertise", "Draw cards until you have 6 in your hand.", 1, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.fillHand(6);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}