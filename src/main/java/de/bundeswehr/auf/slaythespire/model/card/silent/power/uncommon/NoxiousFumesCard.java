package de.bundeswehr.auf.slaythespire.model.card.silent.power.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;

import java.util.List;

/**
 * Die Noxious Fumes Karte.
 *
 * @author L Frank Rieger
 */
public class NoxiousFumesCard extends PowerCard {

    public NoxiousFumesCard() {
        super("Noxious Fumes", "At the start of your turn, apply 2 Poison to ALL enemies.", 1, CardRarity.UNCOMMON, CardGrave.DISCARD, CardTrigger.PLAYER_BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            enemy.addEffect(new PoisonDebuffEnemy(), 2);
        }
    }

}