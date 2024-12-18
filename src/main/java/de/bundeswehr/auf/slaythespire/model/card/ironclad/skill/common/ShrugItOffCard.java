package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Shrug it off Karte.
 *
 * @author OF Daniel Willig
 */
public class ShrugItOffCard extends SkillCard{

    /**
     * Constructor Shrug it off card.
     */
    public ShrugItOffCard() {
            super("Shrug It Off", "Gain 8 Block. Draw 1 card.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            player.increaseBlock(8);

            BattleDeck battleDeck = gameContext.getBattleDeck();
            battleDeck.drawCard(1);

            player.decreaseCurrentEnergy(getCost());
        }

}
