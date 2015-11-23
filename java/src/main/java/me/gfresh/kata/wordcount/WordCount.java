package me.gfresh.kata.wordcount;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public final class WordCount {

	public Map<String, Integer> phrase(String text) {
		checkArgument(text != null);
		return wordsOf(text).collect(groupingBy(String::toLowerCase, counting));
	}

	private static void checkArgument(boolean expression) {
		if (! expression) throw new IllegalArgumentException();
	}

	private static Stream<String> wordsOf(String text) {
		return Arrays.stream(text.split(nonWord)).filter(nonEmpty);
	}

	private static final String nonWord = "[\\W]+";
	private static final Predicate<String> nonEmpty = word -> ! word.isEmpty();
	private static final Collector<Object, ?, Integer> counting = reducing(0, e -> 1, Integer::sum);
}