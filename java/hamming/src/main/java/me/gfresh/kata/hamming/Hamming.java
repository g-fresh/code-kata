package me.gfresh.kata.hamming;

import java.util.*;
import java.util.stream.*;

public final class Hamming {

    public static int compute(String strandA, String strandB) {
        Stream<NucleotidePair> nucleotidePairs = sideBySide(strandA, strandB);
        return countDifferences(nucleotidePairs);
    }

    private static Stream<NucleotidePair> sideBySide(String strandA, String strandB) {
        checkOfEqualLength(strandA, strandB);
        Iterator<Character> as = asStream(strandA).iterator();
        Stream<Character>   bs = asStream(strandB);
        return bs.filter(c -> as.hasNext()).map(b -> new NucleotidePair(as.next(), b));
    }

    private static void checkOfEqualLength(String strandA, String strandB) {
        if (strandA.length() != strandB.length())
            throw new IllegalArgumentException("Strands have to be of equal length.");
    }

    private static Stream<Character> asStream(String strand) {
        return strand.chars().mapToObj(c -> (char) c);
    }

    private static int countDifferences(Stream<NucleotidePair> nucleotidePairs) {
        return (int) nucleotidePairs.filter(pair -> ! pair.nucleotidesMatch()).count();
    }

    private static class NucleotidePair {

        public final Character a;
        public final Character b;

        private NucleotidePair(Character a, Character b) {
            this.a = a;
            this.b = b;
        }

        boolean nucleotidesMatch() {
            return a.equals(b);
        }
    }
}
