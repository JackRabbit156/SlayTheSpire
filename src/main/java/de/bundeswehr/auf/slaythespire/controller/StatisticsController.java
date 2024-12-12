package de.bundeswehr.auf.slaythespire.controller;

import de.bundeswehr.auf.slaythespire.gui.StatisticsView;
import de.bundeswehr.auf.slaythespire.model.player.structure.Player;

public class StatisticsController implements Controller {

    private StatisticsView statisticsView;

    public StatisticsController(Player player) {
        this.statisticsView = new StatisticsView(player);
    }

    public StatisticsView getView() {
        return statisticsView;
    }

}
