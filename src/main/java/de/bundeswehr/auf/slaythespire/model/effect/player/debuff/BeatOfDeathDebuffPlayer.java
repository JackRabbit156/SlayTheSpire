package de.bundeswehr.auf.slaythespire.model.effect.player.debuff;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.Entity;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.effect.structure.Debuff;
import de.bundeswehr.auf.slaythespire.model.effect.structure.EffectTrigger;
import de.bundeswehr.auf.slaythespire.model.effect.structure.StackingBehaviour;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class BeatOfDeathDebuffPlayer extends Debuff {

    public BeatOfDeathDebuffPlayer() {
        super("Beat Of Death", "Whenever you play a card, take X damage.", EffectTrigger.CARD_DEATH, StackingBehaviour.INTENSITY);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void apply(GameContext gameContext, Entity target) {
        Player player = gameContext.getPlayer();
        gameContext.setAttackContext(new AttackContext(null, player, target.getEffectCounter(this), this));
        player.takeDamage(gameContext);
    }

}
