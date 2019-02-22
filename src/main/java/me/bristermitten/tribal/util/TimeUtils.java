package me.bristermitten.tribal.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static void inOneMinute(Runnable runnable) {
        inTime(runnable, 1, TimeUnit.MINUTES);
    }

    public static void inTime(Runnable runnable, int time, TimeUnit unit) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
        timer.schedule(timerTask, unit.toMillis(time));
    }
}
