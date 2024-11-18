package models.potion;

import models.battle.GameContext;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.player.player_structure.Player;
import models.potion.potion_structure.PotionCard;

public class BloodPotion extends PotionCard {

    public BloodPotion() {
        super("Blood Potion", "Heal for 20% of your Max HP.", 0, CardRarity.POTION, CardGrave.POTION);
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseCurrentHealth((int) (player.getMaxHealth() * 0.2));
    }
}
