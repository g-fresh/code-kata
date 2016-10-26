package me.gfresh.kata.meetup;

import static java.util.stream.Stream.iterate;
import static me.gfresh.kata.meetup.DSL.*;

import java.util.function.*;
import java.util.stream.*;

import org.joda.time.*;

@FunctionalInterface
interface MeetupSchedule {

	DateTime calculate(int month, int year, int dayOfWeek);

	public static MeetupSchedule FIRST  = (month, year, day) -> {
		return takeNth(1, dayOfWeek(day, after, firstOf(month, year)));
	};

	public static MeetupSchedule SECOND = (month, year, day) -> {
		return takeNth(2, dayOfWeek(day, after, firstOf(month, year)));
	};

	public static MeetupSchedule THIRD  = (month, year, day) -> {
		return takeNth(3, dayOfWeek(day, after, firstOf(month, year)));
	};

	public static MeetupSchedule FOURTH = (month, year, day) -> {
		return takeNth(4, dayOfWeek(day, after, firstOf(month, year)));
	};

	public static MeetupSchedule LAST   = (month, year, day) -> {
		return takeNth(1, dayOfWeek(day, before, lastOf(month, year)));
	};

	public static MeetupSchedule TEENTH = (month, year, day) -> {
		return takeNth(1, teenth(day, after, firstOf(month, year)));
	};
}

final class DSL {

	static <T> T takeNth(int n, Stream<T> stream) {
		return stream.skip(n - 1).findFirst().get();
	}

	static Stream<DateTime> dayOfWeek(int day, UnaryOperator<DateTime> next, DateTime instant) {
		return iterate(instant, next).filter(dayOfWeek(day));
	}

	static Stream<DateTime> teenth(int day, UnaryOperator<DateTime> next, DateTime instant) {
		return iterate(instant, next).filter(dayOfWeek(day).and(isTeenth));
	}

	static UnaryOperator<DateTime> after  = d -> d.plusDays(1);

	static UnaryOperator<DateTime> before = d -> d.minusDays(1);

	static DateTime firstOf(int month, int year) {
		return new DateTime(year, month, 1, 0, 0);
	}

	static DateTime lastOf(int month, int year) {
		return firstOf(month, year).dayOfMonth().withMaximumValue();
	}

	private static Predicate<DateTime> dayOfWeek(int dayOfWeek) {
		return day -> day.dayOfWeek().get() == dayOfWeek;
	}

	private static Predicate<DateTime> isTeenth = day -> day.dayOfMonth().get() >= 13 && day.dayOfMonth().get() <= 19;
}