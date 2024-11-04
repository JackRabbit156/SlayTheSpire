package models.potion;

import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.player.player_structure.Player;

public class BlockPotion extends Card {

    public BlockPotion() {
        super("Block Potion", "Gain 12 Block.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseBlock(12);
    }
}
