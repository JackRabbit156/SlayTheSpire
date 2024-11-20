package models.card.silent_cards;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.card_structure.SkillCard;
import models.player.player_structure.Player;

public class SilentDefendCard extends SkillCard {
    public SilentDefendCard() {
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