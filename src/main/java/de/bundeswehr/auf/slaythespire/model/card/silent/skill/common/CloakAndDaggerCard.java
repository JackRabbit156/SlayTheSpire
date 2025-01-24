package de.bundeswehr.auf.slaythespire.model.card.silent.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.attack.ShivCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Cloak And Dagger Karte.
 *
 * @author L Frank Rieger
 */
public class CloakAndDaggerCard extends SkillCard {

    public CloakAndDaggerCard() {
        super("Cloak And Dagger", "Gain 6 Block. Add 1 Shiv to your hand.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainBlock(6);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addToHand(new ShivCard());

        player.decreaseCurrentEnergy(getCost());
    }

}