package de.bundeswehr.auf.slaythespire.tester;

import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.potion.structure.AttackPotion;

public class CheaterPotion extends AttackPotion {

    public CheaterPotion() {
        super("Cheater Potion", "Deals 200 damage.", 200, CardRarity.SPECIAL);
        setImagePath("/images/icon.png");
    }

    @Override
    public void play(GameContext gameContext) {
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.takeDamage(getDamage());
    }

}
