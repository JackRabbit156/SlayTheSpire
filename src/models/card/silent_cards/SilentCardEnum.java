package models.card.silent_cards;

public enum SilentCardEnum {
    STRIKE_CARD("SilentStrikeCard"),
    DEFEND_CARD("SilentDefendCard"),
    NEUTRALIZE_CARD("NeutralizeCard"),
    SURVIVOR_CARD("SurvivorCard");


    private final String name;

    SilentCardEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
