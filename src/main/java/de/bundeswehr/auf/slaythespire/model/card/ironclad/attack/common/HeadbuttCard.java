package de.bundeswehr.auf.slaythespire.model.card.ironclad.attack.common;

import de.bundeswehr.auf.slaythespire.gui.events.CardEvent;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.battle.Playable;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Headbutt Karte.
 *
 * @author OF Daniel Willig
 */
public class HeadbuttCard extends AttackCard implements CardEvent {

    private GameContext gameContext;

    /**
     * Constructor HeadbuttCard.
     */
    public HeadbuttCard() {
        super("Headbutt", "Deal 9 damage. Put a card from your discard pile on top of your draw pile.", 1, 9, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public int dealDamage() {
        return getDamage();
    }

    @Override
    public Playable isPlayable(GameContext gameContext) {
        if (gameContext.getBattleDeck().getDiscardPile().isEmpty()) {
            return new Playable("Headbutt not playable, no cards in discard pile", "There must be at least one card in the discard pile.");
        }
        return super.isPlayable(gameContext);
    }

    @Override
    public void onCardClick(Card card) {
        Enemy enemy = gameContext.getSelectedEnemy();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        Player player = gameContext.getPlayer();
        battleDeck.removeCardFromDiscardPile(card);
        battleDeck.addToDeck(card);
        enemy.takeDamage(dealDamage());
        player.decreaseCurrentEnergy(getCost());
        super.played();
    }

    @Override
    public void play(GameContext gameContext) {
        this.gameContext = gameContext;
        gameContext.getBattleDeck().chooseCardFromDiscardPile(this);
    }

    @Override
    public void played() {
        // do nothing, wait for user interaction
    }

}
