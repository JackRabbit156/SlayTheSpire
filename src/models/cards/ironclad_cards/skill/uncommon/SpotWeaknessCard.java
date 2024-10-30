package models.cards.ironclad_cards.skill.uncommon;

import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

public class SpotWeaknessCard extends SkillCard{


        public SpotWeaknessCard() {
            super("Spot Weakness", "If the enemy intends to attack, gain 3 Strength.", 1, CardRarity.UNCOMMON, CardGrave.DISCARD);
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();

            //TODO Enemy Intend
            //TODO Strength

            player.decreaseCurrentEnergy(getCost());
        }

        @Override
        public String toString() {
            return "Spot Weakness(3 Strength - etc.)";
        }

}
