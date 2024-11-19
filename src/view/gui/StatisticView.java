package view.gui;

import helper.GuiHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import models.game_settings.GameSettings;
import models.player.player_structure.Player;
import view.gui.layouts.layout_events.StatisticViewEvents;

public class StatisticView extends BorderPane {
    private StatisticViewEvents statisticViewEvents;
    private HBox centerHBox;
    private Player player;

    public StatisticView(Player player) {
        this.player = player;
    }
    /**
     * Initialisiert die View.
     */
    public void display() {
        setBackground(new Background(GuiHelper.background("/images/backgrounds/greenBg.jpg")));
        initCenter();
    }

    private void initCenter(){
        centerHBox = new HBox();
        Label centerLeft = new Label();
        Label centerRight = new Label();
        centerLeft.setWrapText(true);
        centerRight.setWrapText(true);

        centerLeft.setText("Act:\n" + "Distributed Damage:\n" + "Received Damage:\n" + "Received Gold:\n" + "Energy spent:\n");
        centerLeft.setAlignment(Pos.TOP_RIGHT);
        centerHBox.setSpacing(5);
        centerHBox.getChildren().addAll(centerLeft, centerRight);
        centerRight.setText(
                player.getCurrentAct() + "\n" +
                GameSettings.getDistributedDamageStats() + "\n" +
                GameSettings.getReceivedDamageStats() + "\n" +
                GameSettings.getReceivedGoldStats() + "\n" +
                GameSettings.getEnergySpentStats() + "\n"
        );
        centerRight.setAlignment(Pos.TOP_LEFT);

        setCenter(centerHBox);
    }
}
