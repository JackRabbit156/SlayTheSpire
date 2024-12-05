package models.card.silent;

/**
 * Das Silent card enum.
 *
 * @author OF Daniel Willig
 */
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
