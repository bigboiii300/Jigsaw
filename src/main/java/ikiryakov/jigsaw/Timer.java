package ikiryakov.jigsaw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {
    private int hour;
    private int minute;
    private int second;
    String current_time;

    Timer(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * @return Время в формате HH:mm:ss
     */
    public String getTime() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        current_time =  hour + ":" + minute + ":" + second;
        Date time = formatter.parse(current_time);
        return formatter.format(time);
    }

    /**
     * Расчет времени.
     */
    public void calculateTime() {
        ++second;
        if (second == 60) {
            ++minute;
            second = 0;
        }
        if (minute == 60) {
            ++hour;
            minute = 0;
        }
    }
}
