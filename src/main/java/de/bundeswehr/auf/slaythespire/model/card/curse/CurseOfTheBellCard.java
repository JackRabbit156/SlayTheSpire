package de.bundeswehr.auf.slaythespire.model.card.curse;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.AttackContext;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardTrigger;
import de.bundeswehr.auf.slaythespire.model.card.structure.TriggeredCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.UnplayableCard;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class CurseOfTheBellCard extends UnplayableCard {

    public CurseOfTheBellCard() {
        super("Curse of the Bell", "Unplayable. Cannot be removed from your deck.", CardGrave.NONE);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onDraw(GameContext gameContext) {}

}
