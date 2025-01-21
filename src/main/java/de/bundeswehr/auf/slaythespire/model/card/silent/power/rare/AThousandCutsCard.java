package de.bundeswehr.auf.slaythespire.model.card.silent.power.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.PowerCard;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die A Thousand Cuts Karte.
 *
 * @author L Frank Rieger
 */
public class AThousandCutsCard extends PowerCard {

    public AThousandCutsCard() {
        super("A Thousand Cuts", "Whenever you play a card, deal 1 damage to ALL enemies.", 2, CardRarity.RARE, CardGrave.DISCARD, CardTrigger.PLAY_CARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            player.dealDamage(gameContext, 1, enemy, this);
        }
    }

}