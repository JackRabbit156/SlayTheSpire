package de.bundeswehr.auf.slaythespire.model.potion.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.DeckFactory;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.effect.buff.RitualBuff;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.SkillPotion;

/**
 * Die Entropic Brew potion.
 *
 * @author L Frank Rieger
 */
public class EntropicBrewPotion extends SkillPotion {

    public EntropicBrewPotion() {
        super("Entropic Brew", "Fill all your empty potion slots with random potions.", CardRarity.RARE);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        DeckFactory deckFactory = new DeckFactory(player, 0);
        for (int i = player.getPotions().size(); i < 3; i++) {
            player.addPotion(deckFactory.generatePotion());
        }
    }

}
