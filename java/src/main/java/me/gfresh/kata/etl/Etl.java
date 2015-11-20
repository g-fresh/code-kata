package me.gfresh.kata.etl;

import java.util.*;
import java.util.stream.*;

public final class Etl {

	public Map<String, Integer> transform(Map<Integer, List<String>> coding) {
		return coding.entrySet().stream().collect(
				Collector.of(HashMap::new, Etl::reverseMapping, Etl::merge));
	}

	private static void reverseMapping(Map<String, Integer> map, Map.Entry<Integer, List<String>> entry) {
		Integer score = entry.getKey();
		Iterable<String> characters = entry.getValue();
		characters.forEach(character -> map.put(character.toLowerCase(), score));
	}

	private static <T extends Map<String, Integer>> T merge(T left, T right) {
		left.putAll(right);
		return left;
	}
}
