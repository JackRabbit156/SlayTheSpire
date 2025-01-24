package de.bundeswehr.auf.slaythespire.model.card.curse;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

public class RegretCard extends UnplayableCard implements TriggeredCard {

    private final CardTrigger cardTrigger;

    public RegretCard() {
        super("Regret", "Unplayable. At the end of your turn, lose 1 HP for each card in your hand.", CardGrave.NONE);
        setImagePath(new PathAssistent().toPath(this));
        this.cardTrigger = CardTrigger.PLAYER_END_OF_TURN;
    }

    @Override
    public CardTrigger getTrigger() {
        return cardTrigger;
    }

    @Override
    public void onDraw(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addTriggeredCard(this);
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        List<Card> hand = battleDeck.getHand();
        gameContext.setAttackContext(new AttackContext(null, player, hand.size(), this));
        player.looseHp(gameContext);
    }

}
