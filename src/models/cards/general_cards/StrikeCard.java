package models.cards.general_cards;

import helper.ConsoleAssistent;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

public class StrikeCard extends AttackCard {
    public StrikeCard() {
        super("Strike", "Deal 6 damage.", 1, 6, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath("/images/cards/ironclad/Strike.png");
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {

        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}