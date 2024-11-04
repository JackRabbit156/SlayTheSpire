package models.potion;

import models.GameContext;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.player.player_structure.Player;

public class BloodPotion extends Card {

    public BloodPotion() {
        super("Blood Potion", "Heal for 20% of your Max HP.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentHealth((int) (player.getMaxHealth() * 0.2));
    }
}
