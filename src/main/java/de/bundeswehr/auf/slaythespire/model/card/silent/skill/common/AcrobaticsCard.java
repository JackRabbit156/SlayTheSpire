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
 * Die Acrobatics Karte.
 *
 * @author L Frank Rieger
 */
public class AcrobaticsCard extends SkillCard implements CardEventListener {

    private GameContext gameContext;

    public AcrobaticsCard() {
        super("Acrobatics", "Draw 3 cards. Discard 1 card.", 1, CardRarity.COMMON, CardGrave.DISCARD);
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
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(3);

        this.gameContext = gameContext;
        battleDeck.chooseCardFromHand(this);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void played() {
        // do nothing, wait for user interaction
    }

}