package models.player;


import helper.PathAssistent;
import models.card.card_structure.Card;
import models.card.ironclad_cards.IroncladDefendCard;
import models.card.ironclad_cards.IroncladStrikeCard;
import models.card.silent_cards.SilentDefendCard;
import models.card.silent_cards.SilentStrikeCard;
import models.card.silent_cards.attack.common.NeutralizeCard;
import models.card.silent_cards.skill.common.SurvivorCard;
import models.player.player_structure.Player;
import models.player.player_structure.PlayerType;
import models.relic.RingOfTheSnake;
import models.relic.relic_structure.Relic;

import java.util.ArrayList;
import java.util.List;

public class SilentPlayer extends Player {
    // * Constructor *
    public SilentPlayer() {
        super("Silent", 70, 3, PlayerType.SILENT, "⚖");
        setImagePath(new PathAssistent().toPath(this));
        setGold(99);
        initRelic();
        initDeck();
    }

    // * Methods *
    @Override
    protected void initRelic() {
        Relic startRelic = new RingOfTheSnake();
        setRelic(startRelic);
    }

    @Override
    protected void initDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            deck.add(new SilentStrikeCard());

        for (int i = 0; i < 5; i++)
            deck.add(new SilentDefendCard());

        deck.add(new SurvivorCard());

        deck.add(new NeutralizeCard());

        setDeck(deck);
    }
}
