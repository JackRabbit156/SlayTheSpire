package models;

import helper.ConsoleAssistent;

public class GameCounter extends Thread {

    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    private boolean running = false;

    public boolean isTimerRunning() {
        return running;
    }

    public void stopTimer() {
        running = false;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public String toString() {
        return "Played for: " + hours + "h " + minutes + "m " + seconds+"s";
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            ConsoleAssistent.sleep(1000);
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
}
