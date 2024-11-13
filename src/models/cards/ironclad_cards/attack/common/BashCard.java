package models.cards.ironclad_cards.attack.common;

import helper.ConsoleAssistent;
import helper.PathAssistent;
import models.GameContext;
import models.cards.card_structure.AttackCard;
import models.cards.card_structure.CardGrave;
import models.cards.card_structure.CardRarity;
import models.enemy.Enemy;
import models.player.player_structure.Player;

/**
 * Bash Karte.
 * @author OF Daniel Willig
 */
public class BashCard extends AttackCard {
    /**
     * Constructor BashCard
     */
    public BashCard() {
        super("Bash", "Deal 2 Damage. Apply 2 Vulnerable.", 2, 8, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
        targetIsRequired();
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
        //TODO Apply Debuff 2 Vulnerable
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}