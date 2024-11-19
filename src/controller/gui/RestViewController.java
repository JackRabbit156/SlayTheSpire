package controller.gui;

import helper.Color;
import helper.ConsoleAssistent;
import helper.GuiHelper;
import models.player.player_structure.Player;
import view.cli.RestSiteView;
import view.gui.RestView;
import view.gui.layouts.layout_events.RestViewEvents;

/**
 * @author Vladislav Keil
 */
public class RestViewController implements RestViewEvents {
    private RestSiteView rest;
    private Player player;
    private RestView restView;

    public RestViewController(Player player) {
        this.player = player;
        this.rest = new RestSiteView();
    }

    public void startRest() {
        this.restView = new RestView(this);
        this.restView.initRestViewEvents(this);
    }

    @Override
    public void onHealClicked() {
        int increasedHp = (int) (player.getMaxHealth() * 0.30);
        player.increaseCurrentHealth(increasedHp);
        ConsoleAssistent.println(Color.YELLOW, "Es wurde geheilt.");
        onBackClicked();
    }

    @Override
    public void onBackClicked() {
        ConsoleAssistent.println(Color.YELLOW, "Back wurde im RestViewController angeklickt");
        GuiHelper.Scenes.startMapScene(player.getPrimaryStage(), player, true);
    }

    public RestView getRestView() {
        return restView;
    }
}

