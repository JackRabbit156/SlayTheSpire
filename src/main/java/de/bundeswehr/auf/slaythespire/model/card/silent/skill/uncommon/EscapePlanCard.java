package de.bundeswehr.auf.slaythespire.model.card.silent.skill.uncommon;

import de.bundeswehr.auf.slaythespire.controller.listener.BattleDeckListener;
import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.debuff.WeakDebuff;
import de.bundeswehr.auf.slaythespire.model.effect.enemy.debuff.PoisonDebuffEnemy;
import de.bundeswehr.auf.slaythespire.model.enemy.structure.Enemy;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

import java.util.List;

/**
 * Die Escape Plan Karte.
 *
 * @author L Frank Rieger
 */
public class EscapePlanCard extends SkillCard implements BattleDeckListener {

    private GameContext gameContext;

    public EscapePlanCard() {
        super("Escape Plan", "Draw 1 card. If you draw a Skill, gain 3 Block.", 0, CardRarity.UNCOMMON, CardGrave.DISCARD);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void chooseCard(List<Card> cards, CardEventListener eventListener) {}

    @Override
    public void onCardClick(Card card) {}

    @Override
    public void onCardDrawn(Card card) {
        if (card instanceof SkillCard) {
            Player player = gameContext.getPlayer();
            player.gainBlock(3);
        }
    }

    @Override
    public void onCardFill() {}

    @Override
    public void play(GameContext gameContext) {
        this.gameContext = gameContext;
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.addBattleDeckListener(this);
        battleDeck.drawCard(1);
        battleDeck.removeBattleDeckListener(this);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

}