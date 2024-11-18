package models.cards.ironclad_cards.skill.uncommon;

import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

/**
 * Die Ghostly armor Karte.
 *
 * @author OF Daniel Willig
 */
public class GhostlyArmorCard extends SkillCard{


    /**
     * Constructor Ghostly armor card.
     */
    public GhostlyArmorCard() {
            super("Ghostly Armor", "Exhaust. Gain 10 Block.", 2, CardRarity.UNCOMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();

            player.increaseBlock(10);

            player.decreaseCurrentEnergy(getCost());
        }
}
