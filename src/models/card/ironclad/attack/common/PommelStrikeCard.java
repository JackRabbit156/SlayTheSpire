package models.card.ironclad.attack.common;

import helper.PathAssistent;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Die Pommel strike Karte.
 *
 * @author OF Daniel Willig
 */
public class PommelStrikeCard extends AttackCard {
    /**
     * Constructor Pommel strike card.
     */
    public PommelStrikeCard() {
        super("Pommel Strike", "Deal 9 damage. Draw 1 card.", 1, 9, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
