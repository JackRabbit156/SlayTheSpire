package de.bundeswehr.auf.slaythespire.gui.layouts.top_bar;

import de.bundeswehr.auf.slaythespire.gui.View;
import de.bundeswehr.auf.slaythespire.gui.components.StrokedText;
import de.bundeswehr.auf.slaythespire.model.settings.GameSettings;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TimerText extends StrokedText implements View {

    private final Future<?> future;

    public TimerText() {
        super(20, Paint.valueOf("#e9cd77"), Paint.valueOf("#2d2605"), 3);
        update();
        future = GameSettings.executorService.scheduleAtFixedRate(() -> Platform.runLater(this::update), 1L, 1L, TimeUnit.SECONDS);
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
