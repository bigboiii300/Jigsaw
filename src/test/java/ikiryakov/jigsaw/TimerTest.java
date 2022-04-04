package ikiryakov.jigsaw;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {

    @Test
    void getTime() throws ParseException {
        Timer timerTest = new Timer(0,0,0);
        timerTest.calculateTime();
        System.out.println(timerTest.getTime());

    }

    @Test
    void calculateTime() throws ParseException {
        Timer timerTest = new Timer(0,0,0);
        for (int i = 0; i < 10000; i++) {
            timerTest.calculateTime();
        }
        System.out.println(timerTest.getTime());
        assertEquals("02:46:40",timerTest.getTime());
    }
}