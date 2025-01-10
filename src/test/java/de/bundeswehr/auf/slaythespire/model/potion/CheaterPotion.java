package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.AttackPotion;

public class CheaterPotion extends AttackPotion {

    public CheaterPotion() {
        super("Cheater Potion", "Kills the enemy.", 0, CardRarity.SPECIAL);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        Enemy enemy = gameContext.getSelectedEnemy();
        player.dealDamage(gameContext, enemy.getCurrentHealth(), enemy, this);
    }

}
