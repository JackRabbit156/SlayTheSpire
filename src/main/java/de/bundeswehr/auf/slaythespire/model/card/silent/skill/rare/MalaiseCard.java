package de.bundeswehr.auf.slaythespire.model.card.silent.skill.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.CorpseExplosionDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Malaise Karte.
 *
 * @author L Frank Rieger
 */
public class MalaiseCard extends SkillCard {

    public MalaiseCard() {
        super("Malaise", "Enemy loses X Strength. Apply X Weak. Exhaust.", 0, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        Enemy enemy = gameContext.getSelectedEnemy();
        enemy.addEffect(new StrengthBuff(), - player.getCurrentEnergy());
        enemy.addEffect(new WeakDebuff(), player.getCurrentEnergy());

        player.decreaseCurrentEnergy(player.getCurrentEnergy());
    }

}