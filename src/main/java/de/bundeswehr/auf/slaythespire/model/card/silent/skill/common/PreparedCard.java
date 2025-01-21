package de.bundeswehr.auf.slaythespire.model.card.silent.skill.common;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Prepared Karte.
 *
 * @author L Frank Rieger
 */
public class PreparedCard extends SkillCard implements CardEventListener {

    private GameContext gameContext;

    public PreparedCard() {
        super("Prepared", "Draw 1 card. Discard 1 card.", 0, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onCardClick(Card card) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.discardCardFromHand(card);

        super.played();
    }

    @Override
    public void play(GameContext gameContext) {
        this.gameContext = gameContext;
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);

        battleDeck.chooseCardFromHand(this);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void played() {
        // do nothing, wait for user interaction
    }

}