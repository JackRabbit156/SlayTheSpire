package de.bundeswehr.auf.slaythespire.model.card.silent.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Crippling Cloud Karte.
 *
 * @author L Frank Rieger
 */
public class CripplingCloudCard extends SkillCard {

    public CripplingCloudCard() {
        super("Crippling Cloud", "Apply 4 Poison and 2 Weak to ALL enemies. Exhaust.", 2, CardRarity.UNCOMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.addEffect(new PoisonDebuffEnemy(), 4);
            enemy.addEffect(new WeakDebuff(), 2);
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}