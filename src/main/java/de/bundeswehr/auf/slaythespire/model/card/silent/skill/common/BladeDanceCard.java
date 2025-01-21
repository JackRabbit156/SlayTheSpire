package de.bundeswehr.auf.slaythespire.model.card.silent.skill.common;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.silent.attack.ShivCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Blade Dance Karte.
 *
 * @author L Frank Rieger
 */
public class BladeDanceCard extends SkillCard {

    public BladeDanceCard() {
        super("Blade Dance", "Add 3 Shivs to your hand.", 1, CardRarity.COMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addToHand(new ShivCard());
        battleDeck.addToHand(new ShivCard());
        battleDeck.addToHand(new ShivCard());

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}