package de.bundeswehr.auf.slaythespire.models.card.ironclad.attack.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.models.battle.GameContext;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.AttackCard;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardGrave;
import de.bundeswehr.auf.slaythespire.models.card.card_structure.CardRarity;
import de.bundeswehr.auf.slaythespire.models.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.models.player.player_structure.Player;

/**
 * Die Feed Karte.
 *
 * @author OF Daniel Willig
 */
public class FeedCard extends AttackCard {
    /**
     * Constructor Feed card.
     */
    public FeedCard() {
        super("Feed", "Deal 10 damage. If Fatal, raise your Max HP by 3. Exhaust.", 1, 10, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());

        if (!enemy.isAlive()) {
            player.increaseMaxHealth(3);
        }
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }
}
