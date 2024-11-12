package models.cards.ironclad_cards.skill.uncommon;

import helper.PathAssistent;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

/**
 * Die Spot weakness Karte.
 *
 * @author OF Daniel Willig
 */
public class SpotWeaknessCard extends SkillCard{


    /**
     * Constructor Spot weakness card.
     */
    public SpotWeaknessCard() {
            super("Spot Weakness", "If the enemy intends to attack, gain 3 Strength.", 1, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();

            //TODO Enemy Intend
            //TODO Strength

            player.decreaseCurrentEnergy(getCost());
        }
}
