package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Feed Karte.
 *
 * @author OF Daniel Willig
 */
public class FeedCard extends AttackCard {

    /**
     * Constructor Feed card.
     */
    public FeedCard() {
        super("Feed", "Deal 10 damage. If Fatal, raise your Max HP by 3. Exhaust.", 1, 10, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        super.play(gameContext);

        Enemy enemy = gameContext.getSelectedEnemy();
        if (!enemy.isAlive()) {
            Player player = gameContext.getPlayer();
            player.increaseMaxHealth(3);
        }
    }

}
