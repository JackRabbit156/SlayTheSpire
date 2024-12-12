package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.common;

import de.bundeswehr.auf.slaythespire.gui.events.CardEvent;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;

/**
 * Die Warcry Karte.
 *
 * @author OF Daniel Willig
 */
public class WarcryCard extends SkillCard implements CardEvent {

    private GameContext gameContext;

    /**
     * Constructor Warcry card.
     */
    public WarcryCard() {
        super("Warcry", "Draw 1 card. Put a card from your hand onto the top of your draw pile. Exhaust.", 0, CardRarity.COMMON, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public Playable isPlayable(GameContext gameContext) {
        if (gameContext.getBattleDeck().getHand().size() < 2) {
            return new Playable("Warcry not playable, only card in hand", "There must be at least one other card in the hand.");
        }
        return super.isPlayable(gameContext);
    }

    @Override
    public void onCardClick(Card card) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(1);
        battleDeck.removeCardFromHand(card);
        battleDeck.addToDeck(card);
        super.played();
    }

    @Override
    public void play(GameContext gameContext) {
        this.gameContext = gameContext;
        gameContext.getBattleDeck().chooseCardFromHand(this);
    }

    @Override
    public void played() {
        // do nothing, wait for user interaction
    }

}
