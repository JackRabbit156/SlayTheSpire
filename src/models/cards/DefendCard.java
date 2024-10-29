package models.cards;

import models.GameContext;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

public class DefendCard extends SkillCard {
    public DefendCard() {
        super("Defend", "Gain 5 Block.", 1, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.setBlock(5);

        player.loseEnergy(getCost());
    }

    @Override
    public String toString() {
        return "Defend(5 Block)";
    }

    @Override
    public void doSkill() {

    }
}