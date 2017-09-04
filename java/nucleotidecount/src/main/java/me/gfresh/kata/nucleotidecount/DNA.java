package me.gfresh.kata.nucleotidecount;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public final class DNA {

    private static final Set<Character> nucleotideTypes = new HashSet<>(Arrays.asList('A', 'C', 'G', 'T'));

    private final String strand;

    public DNA(String strand) {
        if (strand == null) throw new IllegalArgumentException();
        this.strand = strand;
    }

    public int count(Character nucleotide) {
        validation.apply(nucleotide);
        return nucleotideCounts().get(nucleotide);
    }

    private static Function<Character, Character> validation = nucleotide -> {
        if (nucleotideTypes.contains(nucleotide)) return nucleotide;
        else throw new IllegalArgumentException();
    };

    public Map<Character, Integer> nucleotideCounts() {
        Stream<Character> nucleotides = asStream(strand);
        return countNucleotides(nucleotides);
    }

    private static Stream<Character> asStream(String strand) {
        return strand.chars().mapToObj((c -> (char) c));
    }

    private static Map<Character, Integer> countNucleotides(Stream<Character> nucleotides) {
        Map<Character, Integer> counts = zero();
        counts.putAll(nucleotides.map(validation).collect(groupingBy(identity(), counting)));
        return counts;
    }

    private static Map<Character, Integer> zero() {
        Map<Character, Integer> counts = new HashMap<>();
        nucleotideTypes.forEach(type -> counts.put(type, 0));
        return counts;
    }

    private static final Collector<Object, ?, Integer> counting = reducing(0, e -> 1, Integer::sum);
}
