package de.bundeswehr.auf.slaythespire.model.card.silent.skill.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Adrenaline Karte.
 *
 * @author L Frank Rieger
 */
public class AdrenalineCard extends SkillCard {

    public AdrenalineCard() {
        super("Adrenaline", "Gain 1 Energy. Draw 2 cards. Exhaust.", 0, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainEnergy(1);

        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.drawCard(2);

        player.decreaseCurrentEnergy(getCost());
    }

}