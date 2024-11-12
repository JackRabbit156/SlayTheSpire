package models.cards.general_cards;

import helper.PathAssistent;
import models.GameContext;
import models.cards.card_structure.*;
import models.player.player_structure.Player;

public class DefendCard extends SkillCard {
    public DefendCard() {
        super("Defend", "Gain 5 Block.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        player.increaseBlock(5);



        player.decreaseCurrentEnergy(getCost());
    }
}