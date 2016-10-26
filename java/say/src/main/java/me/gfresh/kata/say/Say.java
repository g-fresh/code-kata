package me.gfresh.kata.say;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

import java.util.*;
import java.util.stream.Collectors;

public class Say {

	public static String inEnglish(int number) {
		return number < 1000 ? saySmall(number) : sayLarge(number);
	}

	private static String saySmall(int number) {
		return String.valueOf(number);
	}

	private static List<Optional<String>> foo(int number, int divisor) {
		List<Optional<String>> words = new ArrayList<>();
		int n = number / divisor;
		int r = number % divisor;
		if (n > 0) {
			words.addAll(foo(r, divisor / 10));
			words.add(scale(divisor));
		}
		return words;
	}

	static List<Integer> split(int number) {
		List<Integer> l = new ArrayList<>();
		int remainder;
		do {
			remainder = number % 1000;
			number = number / 1000;
			l.add(0, remainder);
		} while (number > 0);
		return l;
	}

	static String sayLarge(int number) {
		List<Optional<String>> words = new ArrayList<>();
		for (int divisor = 1000000000; divisor > 0; divisor /= 1000) {
			int n = number / divisor;
			int r = number % divisor;
			if (n > 0) {
				words.add(Optional.of(saySmall(n)));
				words.add(scale(divisor));
			}
			number = r;
		}
		return join(words);
	}

	private static String join(List<Optional<String>> words) {
		return words.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.joining(" "));
	}

	private static Optional<String> scale(int number) {
		return Optional.ofNullable((number > 1) ? dictionary.get(number) : null);
	}

	private static final Map<Integer, String> dictionary = ImmutableMap.<Integer, String>builder()
		.put(0, "zero")		.put(10, "ten")			.put(30, "thirty")		.put(100, "hundred")
		.put(1, "one")		.put(11, "eleven")		.put(40, "forty")		.put(1000, "thousand")
		.put(2, "two")		.put(13, "thirteen")	.put(50, "fifty")		.put(1000000, "million")
		.put(3, "three")	.put(14, "fourteen")	.put(60, "sixty")		.put(1000000000, "billion")
		.put(4, "four")		.put(15, "fifteen")		.put(70, "seventy")
		.put(5, "five")		.put(16, "sixteen")		.put(80, "eighty")
		.put(6, "six")		.put(17, "seventeen")	.put(90, "ninety")
		.put(7, "seven")	.put(18, "eighteen")
		.put(8, "eight")	.put(19, "nineteen")
		.put(9, "nine")		.put(20, "twenty")
	.build();
}
