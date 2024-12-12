package de.bundeswehr.auf.slaythespire.model.settings;

/**
 * Diese Klasse repräsentiert einen Spielzähler, der die Spielzeit in
 * Stunden, Minuten und Sekunden verfolgt. Sie implementiert die Runnable-Schnittstelle,
 * um den Zähler in einem eigenen Thread auszuführen.
 *
 * @author Warawa Alexander
 */
public class GameCounter extends Thread {

    private int hours = 0;
    private int minutes = 0;
    private boolean running = false;
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

    public boolean isTimerRunning() {
        return running;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
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
    }

    public void stopTimer() {
        running = false;
    }

    @Override
    public String toString() {
        return "Played for: " + hours + "h " + minutes + "m " + seconds + "s";
    }

}
