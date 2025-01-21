package de.bundeswehr.auf.slaythespire.model.card.silent.attack.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Grand Finale Karte.
 *
 * @author L Frank Rieger
 */
public class GrandFinaleCard extends AttackCard {

    public GrandFinaleCard() {
        super("Grand Finale", "Can only be played, if there are no cards in your draw pile. Deal 50 damage to ALL enemies.", 0, 50, CardRarity.RARE, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public Playable isPlayable(GameContext gameContext) {
        if (gameContext.getBattleDeck().getDrawPile().isEmpty()) {
            return super.isPlayable(gameContext);
        }
        return new Playable("Grand Finale not playable, too much cards.", "There can be no cards in your draw pile.");
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        List<Enemy> allEnemies = gameContext.getEnemies();
        for (Enemy enemy : allEnemies) {
            player.dealDamage(gameContext, getDamage(gameContext), enemy, this);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}