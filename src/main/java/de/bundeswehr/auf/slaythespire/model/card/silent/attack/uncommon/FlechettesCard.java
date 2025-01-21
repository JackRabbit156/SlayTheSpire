package de.bundeswehr.auf.slaythespire.model.card.silent.attack.uncommon;

import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.*;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Flechettes Karte.
 *
 * @author L Frank Rieger
 */
public class FlechettesCard extends AttackCard {

    public FlechettesCard() {
        super("Flechettes", "Deal 4 damage for each Skill in your hand.", 1, 4, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void play(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        BattleDeck battleDeck = gameContext.getBattleDeck();
        int skillCards = 0;
        for (Card card : battleDeck.getHand()) {
            if (card instanceof SkillCard) {
                skillCards++;
            }
        }
        if (skillCards > 0) {
            Enemy enemy = gameContext.getSelectedEnemy();
            player.dealDamage(gameContext, skillCards * getDamage(gameContext), enemy, this);
        }

        player.decreaseCurrentEnergy(getCost());
    }

}