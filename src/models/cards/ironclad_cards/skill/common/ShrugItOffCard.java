package models.cards.ironclad_cards.skill.common;

import models.BattleDeck;
import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

public class ShrugItOffCard extends SkillCard{


        public ShrugItOffCard() {
            super("Shrug It Off", "Gain 8 Block. Draw 1 card.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        }

        @Override
        public void play(GameContext gameContext) {
            Player player = gameContext.getPlayer();
            BattleDeck battleDeck = gameContext.getBattleDeck();

            player.increaseBlock(8);
            battleDeck.drawCard(1);

            player.loseEnergy(getCost());
        }

        @Override
        public String toString() {
            return "Shrug It Off(8 Block - Draw 1 Card)";
        }

}
