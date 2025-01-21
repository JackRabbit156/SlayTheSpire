package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.buff.StrengthBuff;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.enemy_card.InsultEnemyCard;
import de.bundeswehr.auf.slaythespire.model.enemy_card.structure.AttackEnemyCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Spot Weakness Karte.
 *
 * @author L Frank Rieger
 */
public class SpotWeaknessCard extends SkillCard {

    public SpotWeaknessCard() {
        super("Spot Weakness", "If the enemy intends to attack, gain 3 Strength.", 2, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        // TODO sollte vom Spieler ausgewählt werden
        gameContext.setRandomEnemy();
        Enemy enemy = gameContext.getSelectedEnemy();
        Player player = gameContext.getPlayer();
        if (enemy.getIntent() instanceof AttackEnemyCard) {
            player.addEffect(new StrengthBuff(), 3);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}
