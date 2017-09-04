package me.gfresh.kata.wordcount;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

import java.util.*;

import org.junit.*;

public final class WordCountTest {

    @Test
    public void returns_empty_map_on_empty_string() {
        assertThat(wordCount("")).isEmpty();
    }

    @Test
    public void returns_empty_map_on_only_non_word_input() {
        assertThat(wordCount("  \t\n?..$ ,...  --> = ?")).isEmpty();
    }

    @Test
    public void returns_empty_map_on_string_with_only_punctuation() {
        assertThat(wordCount("...")).isEmpty();
    }

    @Test
    public void counts_words_and_numbers() {
        assertThat(wordCount("one 2 one 2 3")).hasSize(3).includes(entry("one", 2), entry("2", 2), entry("3", 1));
    }

    @Test
    public void works_case_insensitive() {
        assertThat(wordCount("HA Ha ha")).hasSize(1).includes(entry("ha", 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_exception_on_null_argument() {
        wordCount(null);
    }

    private static Map<String, Integer> wordCount(String text) {
        return new WordCount().phrase(text);
    }
}
