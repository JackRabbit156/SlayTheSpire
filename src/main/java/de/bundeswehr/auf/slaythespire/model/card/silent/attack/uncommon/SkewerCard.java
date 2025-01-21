package de.bundeswehr.auf.slaythespire.model.card.silent.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Skewer Karte.
 *
 * @author L Frank Rieger
 */
public class SkewerCard extends AttackCard {

    public SkewerCard() {
        super("Skewer", "Deal 7 damage X times.", 0, 7, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        Enemy enemy = gameContext.getSelectedEnemy();
        for (int i = 0; i < player.getCurrentEnergy(); i++) {
            player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
        }

        player.decreaseCurrentEnergy(player.getCurrentEnergy());
    }

}