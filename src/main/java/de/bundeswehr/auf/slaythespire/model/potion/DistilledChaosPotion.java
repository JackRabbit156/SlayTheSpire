package de.bundeswehr.auf.slaythespire.model.potion;

import de.bundeswehr.auf.slaythespire.controller.listener.CardDeathListener;
import de.bundeswehr.auf.slaythespire.helper.Color;
import de.bundeswehr.auf.slaythespire.helper.LoggingAssistant;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.potion.structure.SkillPotion;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Die Distilled chaos potion.
 *
 * @author OF Daniel Willig
 */
public class DistilledChaosPotion extends SkillPotion implements CardDeathListener {

    private final Queue<Card> cards = new ArrayDeque<>();
    private GameContext gameContext;

    /**
     * Constructor Distilled chaos potion.
     */
    public DistilledChaosPotion() {
        super("Distilled Chaos", "Play the top 3 cards of your draw pile.", CardRarity.UNCOMMON);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onCardCanceled(Card card) {
        LoggingAssistant.log(card.getName() + " canceled", Color.PURPLE_BRIGHT);
        gameContext.getPlayer().decreaseCurrentEnergy(card.getCost());
        onCardDeath(card);
    }

    @Override
    public void onCardDeath(Card card) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        if (card.getCardGrave().equals(CardGrave.EXHAUST)) {
            battleDeck.exhaustCardFromDeck(card);
        }
        else if (card.getCardGrave().equals(CardGrave.DISCARD)) {
            battleDeck.discardCardFromDeck(card);
        }
        else {
            battleDeck.removeCardFromDeck(card);
        }
        if (card == cards.poll() && !endOfCombat()) {
            play(cards.peek());
        }
    }

    @Override
    public void play(GameContext gameContext) {
        this.gameContext = gameContext;
        BattleDeck battleDeck = gameContext.getBattleDeck();
        cards.addAll(battleDeck.getTopFromDeck(3));
    }

    @Override
    public void played() {
        super.played();
        play(cards.peek());
    }

    private boolean endOfCombat() {
        for (Enemy enemy : gameContext.getEnemies()) {
            if (enemy.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private void play(Card card) {
        if (card != null) {
            Playable cardPlayable = card.isPlayable(gameContext);
            if (cardPlayable.isPlayable()) {
                gameContext.getPlayer().increaseCurrentEnergy(card.getCost());
                card.register(this);
                card.play(gameContext);
                card.played();
                gameContext.getBattleDeck().onCardClick(card);
            }
            else {
                LoggingAssistant.log(cardPlayable.getLogMessage(), Color.YELLOW);
                onCardDeath(card);
            }
        }
    }

}
