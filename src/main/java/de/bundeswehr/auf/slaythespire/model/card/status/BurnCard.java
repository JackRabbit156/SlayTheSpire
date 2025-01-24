package de.bundeswehr.auf.slaythespire.model.card.status;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.TriggeredCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.UnplayableCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class BurnCard extends UnplayableCard implements TriggeredCard {

    private final CardTrigger cardTrigger;

    public BurnCard() {
        super("Burn", "Unplayable. At the end of your turn, take 2 damage.", CardGrave.DISCARD);
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
        gameContext.setAttackContext(new AttackContext(null, player, 2, this));
        player.takeDamage(gameContext);
    }

}
