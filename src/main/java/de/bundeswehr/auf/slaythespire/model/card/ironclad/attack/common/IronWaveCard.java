package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Iron wave Karte.
 *
 * @author OF Daniel Willig
 */
public class IronWaveCard extends AttackCard {

    /**
     * Constructor Iron wave card.
     */
    public IronWaveCard() {
        super("Iron Wave", "Gain 5 Block. Deal 5 damage.", 1, 5, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(dealDamage(gameContext), gameContext);

        Player player = gameContext.getPlayer();
        player.increaseBlock(5);
        player.decreaseCurrentEnergy(getCost());
    }

}
