package de.bundeswehr.auf.slaythespire.gui.components;

import de.bundeswehr.auf.slaythespire.gui.View;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TimerText extends Label implements View {

    private final Future<?> future;

    public TimerText() {
        future = GameSettings.executorService.scheduleAtFixedRate(() -> Platform.runLater(this::update), 1L, 1L, TimeUnit.SECONDS);
        setTextFill(Paint.valueOf("#e9cd77"));
        setFont(Font.font("Kreon", FontWeight.BOLD, 20));
        setPrefWidth(80);
    }

    @Override
    public void discard() {
        future.cancel(true);
    }

    private void update() {
        setText(String.format("%02d", GameSettings.getTimerHours()) + ":" +
                String.format("%02d", GameSettings.getTimerMinutes()) + ":" +
                String.format("%02d", GameSettings.getTimerSeconds()));
    }

}
