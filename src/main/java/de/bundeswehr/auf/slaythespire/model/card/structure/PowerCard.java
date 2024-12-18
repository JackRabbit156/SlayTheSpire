package de.bundeswehr.auf.slaythespire.model.card.structure;

import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Power card.
 *
 * @author OF Daniel Willig
 */
public abstract class PowerCard extends Card implements TriggeredCard {

    /**
     * Wann die Power getriggert wird.
     */
    private final CardTrigger cardTrigger;

    /**
     * Constructor PowerCard.
     *
     * @param name        der Name
     * @param description die Beschreibung
     * @param cost        die Energiekosten
     * @param rarity      die Seltenheit
     * @param cardGrave   wo die Karte hingeht nachdem die gespielt worden ist
     * @param cardTrigger was der Karten-Trigger ist
     */
    public PowerCard(String name, String description, int cost, CardRarity rarity, CardGrave cardGrave, CardTrigger cardTrigger) {
        super(name, description, cost, rarity, cardGrave);
        this.cardTrigger = cardTrigger;
    }

    @Override
    public CardTrigger getTrigger() {
        return cardTrigger;
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();

        battleDeck.addTriggeredCard(this);

        player.decreaseCurrentEnergy(getCost());
    }

}