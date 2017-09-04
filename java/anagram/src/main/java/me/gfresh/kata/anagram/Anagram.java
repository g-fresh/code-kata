package me.gfresh.kata.anagram;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public final class Anagram {

    private final String word;

    public Anagram(String word) {
        checkArgument(word != null);
        this.word = word;
    }

    private static void checkArgument(boolean expression) {
        if (! expression) throw new IllegalArgumentException();
    }

    public List<String> match(Iterable<String> candidates) {
        checkArgument(candidates != null);
        return streamOf(candidates).filter(anagramOf(word)).collect(toCollection(ArrayList::new));
    }

    private static Stream<String> streamOf(Iterable<String> candidates) {
        return StreamSupport.stream(candidates.spliterator(), false);
    }

    private static Predicate<String> anagramOf(String word) {
        String lowerCase = word.toLowerCase();
        Map<Character, Long> histogram = histogram(lowerCase);
        return compareBy(lowerCase, histogram);
    }

    private static Map<Character, Long> histogram(String word) {
        return charactersOf(word).collect(groupingBy(identity(), counting()));
    }

    private static Stream<Character> charactersOf(String word) {
        return word.chars().mapToObj(c -> (char) c);
    }

    private static Predicate<String> compareBy(String word, Map<Character, Long> histogram) {
        return candidate -> {
            String c = candidate.toLowerCase();
            return ! c.equals(word) && histogram.equals(histogram(c));
        };
    }
}
