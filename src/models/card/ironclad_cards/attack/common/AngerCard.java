package models.card.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.battle.BattleDeck;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

/**
 * Anger Karte.
 * @author OF Daniel Willig
 */
public class AngerCard extends AttackCard {
    /**
     * Constructor AngerCard
     */
    public AngerCard() {
        super("Anger", "Deal 6 damage. Add a copy of this card into your discard pile.", 0, 6, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> discardPile = battleDeck.getDiscardPile();

        discardPile.add(new AngerCard());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
