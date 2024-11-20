package models.card.ironclad_cards.attack.common;

import helper.PathAssistent;
import models.battle.GameContext;
import models.card.card_structure.AttackCard;
import models.card.card_structure.Card;
import models.card.card_structure.CardGrave;
import models.card.card_structure.CardRarity;
import models.card.ironclad_cards.IroncladStrikeCard;
import models.enemy.Enemy;
import models.player.player_structure.Player;

import java.util.List;

/**
 * The type Perfected strike card.
 *
 * @author OF Daniel Willig
 */
public class PerfectedStrikeCard extends AttackCard {
    /**
     * Constructor Perfected strike card.
     */
    public PerfectedStrikeCard() {
        super("Perfected Strike", "Deal 6 damage. Deals 2 additional damage for ALL your cards containing \"Strike\".", 2, 6, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();

        PerfectedStrikeCard perfectedStrikeCard = new PerfectedStrikeCard();
        PommelStrikeCard pommelStrikeCard = new PommelStrikeCard();
        IroncladStrikeCard ironcladStrikeCard = new IroncladStrikeCard();
        TwinStrikeCard twinStrikeCard = new TwinStrikeCard();
        WildStrikeCard wildStrikeCard = new WildStrikeCard();
        //SwiftStrikeCard swiftStrikeCard = new SwiftStrikeCard();
        int bonusDmg = 0;

        List<Card> deck = player.getDeck();

        for (Card card : deck) {
            if (card.equals(perfectedStrikeCard) || card.equals(pommelStrikeCard) || card.equals(ironcladStrikeCard) || card.equals(twinStrikeCard) || card.equals(wildStrikeCard)) {
                bonusDmg += 2;
            }
        }

        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage() + bonusDmg);

        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
