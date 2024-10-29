package models.cards;

import models.GameContext;
import models.cards.card_structure.CardRarity;
import models.cards.card_structure.SkillCard;
import models.player.player_structure.Player;

public class SurvivorCard extends SkillCard {
    public SurvivorCard() {
        super("Survivor", "Gain 8 Block. Discard 1 card.", 1, CardRarity.COMMON);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.setBlock(8);

        //TODO Discard 1 card

        player.loseEnergy(getCost());
    }

    @Override
    public void doSkill() {

    }
}