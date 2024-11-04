package tests;

import models.map_elements.field_types.BossField;
import models.player.Ironclad;
import view.StatisticsView;

public class BossFieldTester {
    public static void main(String[] args) {
        BossField bossField = new BossField();
        StatisticsView statisticsView = new StatisticsView();
        Ironclad player = new Ironclad();

        // Es muss gekämpft werden, um die ausgabe zu erhalten.
        // FALL 1: ERWARTE : Hier kommt die StatisticsView mit Loot View
//        bossField.doFieldThing(player);
        // Ausgabe alles 0
        statisticsView.display(player);

    }
}
