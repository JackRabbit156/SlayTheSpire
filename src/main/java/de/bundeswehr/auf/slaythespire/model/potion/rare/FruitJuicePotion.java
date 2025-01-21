package de.bundeswehr.auf.slaythespire.model.potion.rare;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;
import de.bundeswehr.auf.slaythespire.model.potion.structure.SkillPotion;

/**
 * Die Fruit Juice potion.
 *
 * @author L Frank Rieger
 */
public class FruitJuicePotion extends SkillPotion {

    public FruitJuicePotion() {
        super("Fruit Juice", "Gain 5 Max HP.", CardRarity.RARE);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.gainMaxHp(5);
    }

}
