package de.bundeswehr.auf.slaythespire.model.card.silent.skill.rare;

import de.bundeswehr.auf.slaythespire.gui.events.CardEventListener;
import de.bundeswehr.auf.slaythespire.helper.PathAssistent;
import de.bundeswehr.auf.slaythespire.model.battle.BattleDeck;
import de.bundeswehr.auf.slaythespire.model.battle.GameContext;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardGrave;
import de.bundeswehr.auf.slaythespire.model.card.structure.CardRarity;
import de.bundeswehr.auf.slaythespire.model.card.structure.SkillCard;
import de.bundeswehr.auf.slaythespire.model.effect.player.buff.NightmareBuffPlayer;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

/**
 * Die Nightmare Karte.
 *
 * @author L Frank Rieger
 */
public class NightmareCard extends SkillCard implements CardEventListener {

    private GameContext gameContext;

    public NightmareCard() {
        super("Nightmare", "Choose a card. Next turn, add 3 copies of that card into your hand. Exhaust.", 3, CardRarity.RARE, CardGrave.EXHAUST);
        setImagePath(new PathAssistent().toPath(this));
    }

    @Override
    public void onCardClick(Card card) {
        Player player = gameContext.getPlayer();
        player.addEffect(new NightmareBuffPlayer(card), 3);

        super.played();
    }

    @Override
    public void play(GameContext gameContext) {
        this.gameContext = gameContext;
        BattleDeck battleDeck = gameContext.getBattleDeck();
        battleDeck.chooseCardFromHand(this);

        Player player = gameContext.getPlayer();
        player.decreaseCurrentEnergy(getCost());
    }

    @Override
    public void played() {
        // do nothing, wait for user interaction
    }
}