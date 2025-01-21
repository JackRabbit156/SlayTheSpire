package de.bundeswehr.auf.slaythespire.model.card.silent.attack;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.AttackCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class ShivCard extends AttackCard {

    public ShivCard() {
        super("Shiv", "Deal 4 damage. Exhaust.", 0, 4, CardRarity.SPECIAL, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

}
