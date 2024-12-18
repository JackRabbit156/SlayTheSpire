package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Pummel Karte.
 *
 * @author OF Daniel Willig
 */
public class PummelCard extends AttackCard {

    /**
     * Constructor Pummel card.
     */
    public PummelCard() {
        super("Pummel", "Deal 2 damage 4 times. Exhaust.", 1, 2, CardRarity.UNCOMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        for (int i = 0; i < 4; i++) {
            enemy.takeDamage(dealDamage(gameContext));
        }
        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}
