package de.bundeswehr.auf.slaythespire.model.settings;

import java.util.concurrent.*;

/**
 * Diese Klasse repräsentiert einen Spielzähler, der die Spielzeit in
 * Stunden, Minuten und Sekunden verfolgt. Sie implementiert die Runnable-Schnittstelle,
 * um den Zähler in einem eigenen Thread auszuführen.
 *
 * @author Warawa Alexander
 */
public class GameCounter implements Runnable {

    private Future<?> future;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }
        if (minutes == 60) {
            minutes = 0;
            hours++;
        }
    }

    public void startTimer() {
        seconds = 0;
        minutes = 0;
        hours = 0;
        future = GameSettings.executorService.scheduleAtFixedRate(this, 1L, 1L, TimeUnit.SECONDS);
    }

    public void stopTimer() {
        if (future != null) {
            future.cancel(true);
        }
    }

    @Override
    public String toString() {
        return "Played for: " + hours + "h " + minutes + "m " + seconds + "s";
    }

}
