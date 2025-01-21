package de.bundeswehr.auf.slaythespire.model.card.silent.skill.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Alchemize Karte.
 *
 * @author L Frank Rieger
 */
public class AlchemizeCard extends SkillCard {

    public AlchemizeCard() {
        super("Alchemize", "Obtain a random potion. Exhaust.", 0, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        if (player.getPotions().size() < 3) {
            DeckFactory deckFactory = new DeckFactory(player, 0);
            player.addPotion(deckFactory.generatePotion());
        }

        player.decreaseCurrentEnergy(getCost());
    }

}