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
    private boolean healed = false;

    public RestController(Player player) {
        this.player = player;
        this.startRest();
    }

    /**
     * Startet die Rast.
     */
    public void startRest() {
        this.healed = false;
        this.restView = new RestView(this);
        this.restView.initRestViewEvents(this);
    }

    @Override
    public void onHealClicked() {
        if (!this.healed) {
            int increasedHp = (int) (player.getMaxHealth() * 0.30);
            player.increaseCurrentHealth(increasedHp);
            ConsoleAssistent.println(Color.YELLOW, "Healed!!");
            this.healed = true;
        }
        onBackClicked();
    }

    @Override
    public void onBackClicked() {
        ConsoleAssistent.println(Color.YELLOW, "Clicked on Back!");
        GuiHelper.Scenes.startMapScene(player, true);
    }

    /**
     * Übergabe der RestView
     * @return RestView
     */
    public RestView getRestView() {
        return restView;
    }
}

