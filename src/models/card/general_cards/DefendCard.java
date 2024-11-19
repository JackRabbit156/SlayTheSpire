package models.card.general_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.*;
import models.player.player_structure.Player;

public class DefendCard extends SkillCard {
    public DefendCard() {
        super("Defend", "Gain 5 Block.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseBlock(5);



        player.decreaseCurrentEnergy(getCost());
    }
}