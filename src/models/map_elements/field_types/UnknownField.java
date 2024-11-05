package models.map_elements.field_types;

import models.player.player_structure.Player;

import java.util.Random;

public class UnknownField extends Field {
    Random rand = new Random();
    private final Field[] fields;
    int[] percentages = {67, 20, 10, 3};
    int percentStart = 1;

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
            if (percentStart <= randomField && randomField >= percentages[i]) {
                fields[i].doFieldThing(player);
            }
            randomField -= percentages[i];
        }
    }
}
