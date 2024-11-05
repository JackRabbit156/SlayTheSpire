package models.map_elements.field_types;

import models.player.player_structure.Player;

import java.util.Random;

/**
 * Ein Unknown Field hat eine gewisse Wahrscheinlichkeit ein anderes Feld zu sein.
 *
 * @author OF Daniel Willig
 */
public class UnknownField extends Field {
    /**
     * Zufall-Zahl.
     */
    Random rand = new Random();
    /**
     * Die anderen "Felder" / Räume.
     */
    private final Field[] fields;
    /**
     * Die Wahrscheinlichkeit die anderen Räume zu bekommen.
     */
    int[] percentages = {67, 20, 10, 3};
    /**
     * damit die Zahl 1 keine magic number in der Berechnung ist, hier einmal definiert.
     * kann man besser machen aber hey.
     */
    int percentStart = 1;

    /**
     * Constructor Unknown field.
     *
     * @param eventField ein Event
     * @param enemyField Ein Kampf
     * @param eliteField Ein Elite Kampf
     * @param shopField  Ein Shop
     */
    public UnknownField(EventField eventField, EnemyField enemyField, EliteField eliteField, ShopField shopField) {
        super("❓");
        this.fields = new Field[]{eventField, enemyField, eliteField, shopField};
    }

    @Override
    public void doFieldThing(Player player) {
        int percentEnd = 0;
        for (int percentage : percentages) {
            percentEnd += percentage;
        }

        int randomField = (rand.nextInt(percentEnd) + 1);
        for (int i = 0; i < percentages.length; i++) {
            if (percentStart <= randomField && randomField <= percentages[i]) {
                fields[i].doFieldThing(player);
                break;
            }
            randomField -= percentages[i];
        }
    }
}
