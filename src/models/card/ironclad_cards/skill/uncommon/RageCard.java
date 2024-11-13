package models.card.ironclad_cards.skill.uncommon;

import helper.PathAssistent;
import models.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.SkillCard;
import models.player.player_structure.Player;

/**
 * Die Rage Karte.
 *
 * @author OF Daniel Willig
 */
public class RageCard extends SkillCard{


    /**
     * Constructor Rage card.
     */
    public RageCard() {
            super("Rage", "Whenever you play an Attack this turn, gain 3 Block.", 0, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();

            //TODO Buff (Rage)

            player.decreaseCurrentEnergy(getCost());
        }
}
