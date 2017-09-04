package me.gfresh.kata.meetup;

import org.joda.time.*;

public final class Meetup {

    private int month;
    private int year;

    public Meetup(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public DateTime day(int dayOfWeek, MeetupSchedule schedule) {
        return schedule.calculate(month, year, dayOfWeek);
    }
}
