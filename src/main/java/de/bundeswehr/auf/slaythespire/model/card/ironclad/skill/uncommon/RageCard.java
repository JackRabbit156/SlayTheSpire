package de.bundeswehr.auf.slaythespire.model.card.ironclad.skill.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Rage Karte.
 *
 * @author L Frank Rieger
 */
public class RageCard extends SkillCard implements TriggeredCard {

    /**
     * Constructor Entrench card.
     */
    public RageCard() {
        super("Rage", "Whenever you play an Attack this turn, gain 3 Block.", 0, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onTrigger(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.increaseBlock(3);
    }

    @Override
    public CardTrigger getTrigger() {
        return CardTrigger.PLAY_ATTACK;
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();

        battleDeck.addTriggeredCard(this);

        player.decreaseCurrentEnergy(getCost());
    }

}
