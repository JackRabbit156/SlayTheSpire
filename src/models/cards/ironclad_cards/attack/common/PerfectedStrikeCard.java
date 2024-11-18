package models.cards.ironclad_cards.attack.common;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.battle.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.Card;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.cards.general_cards.StrikeCard;
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
        System.out.print("Choose an enemy to target: ");
        int targetIndex = ConsoleAssistent.scannerAutoAim(gameContext.getEnemies().size());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        PerfectedStrikeCard perfectedStrikeCard = new PerfectedStrikeCard();
        PommelStrikeCard pommelStrikeCard = new PommelStrikeCard();
        StrikeCard strikeCard = new StrikeCard();
        TwinStrikeCard twinStrikeCard = new TwinStrikeCard();
        WildStrikeCard wildStrikeCard = new WildStrikeCard();
        //SwiftStrikeCard swiftStrikeCard = new SwiftStrikeCard();
        int bonusDmg = 0;

        List<Card> deck = player.getDeck();

        for (Card card : deck) {
            if (card.equals(perfectedStrikeCard) || card.equals(pommelStrikeCard) || card.equals(strikeCard) || card.equals(twinStrikeCard) || card.equals(wildStrikeCard)) {
                bonusDmg += 2;
            }
        }

        Enemy enemy = gameContext.getEnemies().get(targetIndex);
        enemy.takeDamage(dealDamage() + bonusDmg);

    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
