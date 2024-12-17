package de.bundeswehr.auf.slaythespire.model.card.silent;

import de.bundeswehr.auf.slaythespire.model.card.silent.attack.common.NeutralizeCard;
import de.bundeswehr.auf.slaythespire.model.card.silent.skill.common.SurvivorCard;
import de.bundeswehr.auf.slaythespire.model.card.structure.Card;

/**
 * Das Silent card enum.
 *
 * @author OF Daniel Willig
 */
public enum SilentCards {

    STRIKE_CARD(SilentStrikeCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public SilentStrikeCard create() {
            return new SilentStrikeCard();
        }
    },
    DEFEND_CARD(SilentDefendCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public SilentDefendCard create() {
            return new SilentDefendCard();
        }
    },
    NEUTRALIZE_CARD(NeutralizeCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public NeutralizeCard create() {
            return new NeutralizeCard();
        }
    },
    SURVIVOR_CARD(SurvivorCard.class.getSimpleName()) {
        @Override
        @SuppressWarnings("unchecked")
        public SurvivorCard create() {
            return new SurvivorCard();
        }
    };

    private final String name;

    SilentCards(String name) {
        this.name = name;
    }

    public abstract <C extends Card> C create();

    @Override
    public String toString() {
        return name;
    }

}
