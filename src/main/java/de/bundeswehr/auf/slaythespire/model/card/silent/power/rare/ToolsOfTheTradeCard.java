package de.bundeswehr.auf.slaythespire.model.card.silent.power.rare;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Tools of the Trade Karte.
 *
 * @author L Frank Rieger
 */
public class ToolsOfTheTradeCard extends PowerCard implements CardEventListener {

    private GameContext gameContext;

    public ToolsOfTheTradeCard() {
        super("Tools of the Trade", "At the start of your turn, draw 1 card and discard 1 card.", 1, CardRarity.RARE, CardGrave.NONE, CardTrigger.PLAYER_BEGIN_OF_TURN);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onCardClick(Card card) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.discardCardFromHand(card);
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        this.gameContext = gameContext;
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);

        battleDeck.chooseCardFromHand(this);
    }

}