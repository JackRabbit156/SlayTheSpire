package models.potion;

import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;

public class BlockPotion extends PotionCard {

    public BlockPotion() {
        super("Block Potion", "Gain 12 Block.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseBlock(12);
    }
}
