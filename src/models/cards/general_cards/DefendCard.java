package models.cards.general_cards;

import models.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

public class DefendCard extends SkillCard {
    public DefendCard() {
        super("Defend", "Gain 5 Block.", 1, CardRarity.COMMON, CardGrave.DISCARD);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseBlock(5);

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public String toString() {
        return "Defend(5 Block)";
    }
}