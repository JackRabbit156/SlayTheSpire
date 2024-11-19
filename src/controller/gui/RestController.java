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
public class RestController implements RestViewEvents {
    private Player player;
    private RestView restView;

    public RestController(Player player) {
        this.player = player;
        this.startRest();
    }

    /**
     * Startet die Rast.
     */
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

    /**
     * Übergabe der RestView
     * @return RestView
     */
    public RestView getRestView() {
        return restView;
    }
}

