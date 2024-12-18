package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Pommel strike Karte.
 *
 * @author OF Daniel Willig
 */
public class PommelStrikeCard extends AttackCard {

    /**
     * Constructor Pommel strike card.
     */
    public PommelStrikeCard() {
        super("Pommel Strike", "Deal 9 damage. Draw 1 card.", 1, 9, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);

        super.play(gameContext);
    }

}
