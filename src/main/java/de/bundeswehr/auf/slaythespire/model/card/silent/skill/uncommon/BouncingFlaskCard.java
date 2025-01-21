package de.bundeswehr.auf.slaythespire.model.card.silent.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;
import java.util.Random;

/**
 * Die Bouncing Flask Karte.
 *
 * @author L Frank Rieger
 */
public class BouncingFlaskCard extends SkillCard {

    private static final Random rnd = new Random();

    public BouncingFlaskCard() {
        super("Bouncing Flask", "Apply 3 Poison to a random enemy 3 times.", 2, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        Enemy enemy = allEnemies.get(rnd.nextInt(allEnemies.size()));
        for (int i = 0; i < 3; i++) {
            enemy.addEffect(new PoisonDebuffEnemy(), 3);
        }

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}