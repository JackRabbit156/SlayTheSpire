package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Offering Karte.
 *
 * @author OF Daniel Willig
 */
public class OfferingCard extends SkillCard{


    /**
     * Constructor Offering card.
     */
    public OfferingCard() {
            super("Offering", "Lose 6 HP. Gain 2 Energy. Draw 3 cards. Exhaust.", 0, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            BattleDeck battleDeck = gameContext.getBattleDeck();

            player.decreaseCurrentHealth(6, true);
            player.increaseCurrentEnergy(2);
            battleDeck.drawCard(3);

            player.decreaseCurrentEnergy(getCost());
        }
}
